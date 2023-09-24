package kr.co.kccbrew.schdlMng.controller;


import java.sql.Date;    
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
import kr.co.kccbrew.comm.util.DateFormat;
import kr.co.kccbrew.schdlMng.model.AsAssignVo;
import kr.co.kccbrew.schdlMng.model.AsResultVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.service.SchdlMngService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : schdlMngController
 * @Decription : 스케줄 관리를 위한 controller
 * 
 * @   수정일                    수정자                        수정내용
 * ============      ==============     ==============
 * 2023-09-01			           이세은			        최초생성
 * 2023-09-11                       이세은               휴일등록 메서드 작성
 * 2023-09-12                       이세은               휴일기간 중복방지 유효성검사 메서드작성
 * 2023-09-17                       이세은               검색 기능 수정
 * 2023-09-23                       이세은               잔여 휴일 일수 수정
 * 
 * @author LEESEEUN
 * @version 1.0
 */

@Controller
@Slf4j
public class schdlMngController {

	@Autowired
	private SchdlMngService schdlMngService;
	@Autowired
	private UserService userService;
	@Autowired
	private DateFormat dateFormat;

	/**********************************************************휴가신청**********************************************************/

	/*휴가신청 페이지*/
	@GetMapping("/holiday/add")
	public String holidayAddPage(Model model, Authentication authentication) {

		UserVo userVo = new UserVo();

		/*ID에 따른 데이터 조회*/
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		userVo.setUserId(userId);

		/* 잔여일수 */
		int usedHolidays = schdlMngService.getUsedHoliday(userVo);
		model.addAttribute("usedHolidays", usedHolidays);
		model.addAttribute("holidayVo", new HolidayVo());

		return "schdl/hldyIns";
	}


	/*휴가신청*/
	@PostMapping(value="/holiday/add", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String addHoliday(@Valid @ModelAttribute HolidayVo holidayVo, Errors errors, Authentication authentication)  {

		if (errors.hasFieldErrors("startDate")) {
			return "등록실패: 시작일을 입력해주세요.";
			
		} else if(errors.hasFieldErrors("endDate")) {
			return "등록실패: 종료일을 입력해주세요.";
			
		}else if(errors.hasFieldErrors("remainingDays")) {
			return "등록실패: 휴가일수는 15일을 초과할 수 없습니다.";
			
		} else if(errors.hasFieldErrors("period")) {
			return "등록실패: 주말·법정공휴일에는 휴가를 신청하실 수 없습니다.";
		}

		/*매개변수로 들어온 데이터 확인*/
		System.out.println("holiday: " + holidayVo);

		String message = null;

		Calendar calendar = Calendar.getInstance();
		java.util.Date utilDate = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		/*시큐리티로 ID확인*/
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		String userTypeCd = null;

		/*시큐리티로 사용자 역할(role) 확인*/
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			System.out.println("Role: " + role);

			/*점주인 경우*/
			if ("ROLE_MANAGER".equals(role)) {
				userTypeCd = "02";
				/*수리기사인 경우*/
			} else if ("ROLE_MECHA".equals(role)) {
				userTypeCd = "03";
			}
		}

		holidayVo.setUserId(userId);
		holidayVo.setAppDate(sqlDate);
		holidayVo.setGroupCodeDetailId(userTypeCd);

		System.out.println("보정한 holiday확인: " + holidayVo);

		long startTime = holidayVo.getStartDate().getTime();
		long endTime = holidayVo.getEndDate().getTime();

		if(isAsAssignDate(holidayVo.getStartDate(), holidayVo.getEndDate(), userId)) {
			message = "등록실패: 수리배정일과 동일한 날짜입니다.";
			System.out.println(message);
		} else if (isOverlapDate(holidayVo.getStartDate(), holidayVo.getEndDate(), userId)) {
			message = "등록실패: 이미 휴가신청된 날짜입니다";
			System.out.println(message);
		} else if(isBeforeToday(holidayVo.getStartDate().toString())) {
			message = "등록실패: 현재일보다 이전일 입니다.";
		} else if(startTime > endTime) {
			message = "등록실패: 종료일은 시작일보다 같거나 이후로 지정해야 합니다.";
		}
		else {
			/*휴가등록*/
			schdlMngService.addHoliday(holidayVo);
			message = "등록완료!";
			System.out.println(message);
		}
		return message;
	}




	/*휴가 중복 검사*/
	public boolean isOverlapDate(Date startDate, Date endDate, String userId) {

		List<HolidayVo> vacationDates = schdlMngService.getHoliday(userId);
		Date startDateToCheck = startDate;
		Date endDateToCheck = endDate;

		boolean isOverlap = false;

		/* 휴가일 중복 검사*/
		for (HolidayVo vacation : vacationDates) {
			Date existingStartDate = vacation.getStartDate();
			Date existingEndDate = vacation.getEndDate();

			if(vacation.getActualUse().equals("Y")) {
				if (startDateToCheck.compareTo(existingEndDate) <= 0 && endDateToCheck.compareTo(existingStartDate) >= 0) {
					isOverlap = true;
					break; 
				}
			}
		}

		/*겹치는 경우 또는 겹치지 않는 경우의 처리*/
		if (isOverlap) {
			return isOverlap;
		} else {
			return isOverlap;
		}

	}

	/*지난날 휴가신청 유효검사*/
	public static boolean isBeforeToday(String startDateStr) {
		try {

			java.util.Date today = new java.util.Date();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date startDate = (java.util.Date) dateFormat.parse(startDateStr);

			if (startDate.before(today)) {
				return true;
			}
			return false;
		} catch (ParseException e) {
			e.printStackTrace();
			return true;
		}
	}


	/*수리 배정일 중복검사*/
	public boolean isAsAssignDate(Date startDate, Date endDate, String userId) {

		/*수리배정일 리스트 조회*/
		List<Date> asAssignDates = schdlMngService.getAssignDates(userId);

		boolean isOverlap = false;

		/* 휴가일 중복 검사*/
		for (Date date : asAssignDates) {
			if (date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0) {
				isOverlap = true;
				break;
			}
		}
		return isOverlap;
	}

	/**********************************************************휴가조회**********************************************************/

	@GetMapping("/holiday")
	public String getHolidays(
			Model model,
			HttpSession session,
			Authentication authentication
			) {

		// 이번년도의 마지막 날
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(currentYear + 1, Calendar.JANUARY, 1);
		calendar.add(Calendar.DATE, -1);
		java.util.Date lastDayOfYear = calendar.getTime();

		// 이번 년도의 첫 일 생성
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		java.util.Date firstDayOfYear = calendar.getTime();

		// java.util.Date를 java.sql.Date로 변환
		Date sqlCurrentDate = new Date(lastDayOfYear.getTime());
		Date sqlFirstDayOfYear = new Date(firstDayOfYear.getTime());

		List<SchdlMngVo> holidays = null;
		int holidayCount = 0;
		int totalPage = 0;
		int sharePage = 0; // 첫 페이지 로드이므로 sharePage는 무조건 0

		UserVo userVo = new UserVo();


		/*시큐리티로 사용자 역할(role) 확인*/
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			System.out.println("Role: " + role);


			if ("ROLE_ADMIN".equals(role)) {
				/* 스케줄리스트 데이터 */
				holidays = schdlMngService.getHolidays(1, sqlFirstDayOfYear, sqlCurrentDate, userVo);
				holidayCount = schdlMngService.getHolidayCount(sqlFirstDayOfYear, sqlCurrentDate, userVo);
				break;
			} else {
				/*ID에 따른 데이터 조회*/
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				String userId = userDetails.getUsername();
				userVo.setUserId(userId);

				/* 스케줄리스트 데이터 */
				holidays = schdlMngService.getHolidays(1, sqlFirstDayOfYear, sqlCurrentDate, userVo);
				holidayCount = schdlMngService.getHolidayCount(sqlFirstDayOfYear, sqlCurrentDate, userVo);
			}
		}

		//   totalPage 구하기
		if (holidays != null && !holidays.isEmpty()) {
			totalPage = (int) Math.ceil((double) holidayCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
			totalPage = 1;
		}

		List<UserVo> locationList=userService.selectLocationCd();
		model.addAttribute("locationList", locationList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", 1);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalDataNumber", holidayCount);
		model.addAttribute("schedules", holidays);
		return "holiday";
	}

	/*관리자용 검색한 휴가 조회*/
	@PostMapping(value="/admin/holiday/search", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getSearchedHolidaysForAdmin(
			@RequestParam("currentPage") int currentPage,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@ModelAttribute UserVo userVo,
			Model model,
			HttpSession session,
			Authentication authentication
			) {
		System.out.println("schdlMngController.getSearchedHolidays");

		/*string -> sql.date 형변환*/
		Date startSqlDate = dateFormat.stringToSqlDate(startDate);
		Date endSqlDate = dateFormat.stringToSqlDate(endDate);

		/*파라미터 확인*/
		System.out.println("currentPage: " + currentPage + ", startDate: " + startDate + ", endDate: " + endDate);
		System.out.println("userVo: " + userVo);

		/* 스케줄리스트 데이터 */
		List<SchdlMngVo> holidays = schdlMngService.getHolidays(currentPage, startSqlDate, endSqlDate, userVo);
		int holidayCount = schdlMngService.getHolidayCount(startSqlDate, endSqlDate, userVo);

		/*DB 데이터 확인*/
		/*		System.out.println("holidays: " + holidays);
		System.out.println("holidayCount: " + holidayCount);*/

		int totalPage = 0;
		int sharePage = 0;

		//   totalPage 구하기
		if (holidays != null && !holidays.isEmpty()) {
			totalPage = (int) Math.ceil((double) holidayCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
		}

		// sharePage 구하기
		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		Map<String, Object> data = new HashMap<>();
		data.put("totalPage", totalPage);
		data.put("currentPage", currentPage);
		data.put("sharePage", sharePage);
		data.put("totalDataNumber", holidayCount);
		data.put("schedules", holidays);

		return data;
	}



	/*회원용 휴가 검색 조회*/
	@PostMapping(value="/holiday/search", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getSearchedHolidays(
			@RequestParam("currentPage") int currentPage,
			@RequestParam() String startDate,
			@RequestParam() String endDate,
			Model model,
			HttpSession session,
			Authentication authentication
			) {
		System.out.println("schdlMngController.getSearchedHolidays");

		/*string -> sql.date 형변환*/
		Date startSqlDate = dateFormat.stringToSqlDate(startDate);
		Date endSqlDate = dateFormat.stringToSqlDate(endDate);

		/*ID에 따른 데이터 조회*/
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		UserVo userVo = new UserVo();
		userVo.setUserId(userId);

		/*파라미터 확인*/
		System.out.println("currentPage: " + currentPage + ", startDate: " + startDate + ", endDate: " + endDate);

		/* 스케줄리스트 데이터 */
		List<SchdlMngVo> holidays = schdlMngService.getHolidays(currentPage, startSqlDate, endSqlDate, userVo);
		int holidayCount = schdlMngService.getHolidayCount(startSqlDate, endSqlDate, userVo);

		/*DB 데이터 확인*/
		System.out.println("holidays: " + holidays);
		System.out.println("holidayCount: " + holidayCount);

		int totalPage = 0;
		int sharePage = 0;

		//   totalPage 구하기
		if (holidays != null && !holidays.isEmpty()) {
			totalPage = (int) Math.ceil((double) holidayCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
			totalPage = 1;
		}

		// sharePage 구하기
		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		Map<String, Object> data = new HashMap<>();
		data.put("totalPage", totalPage);
		data.put("currentPage", currentPage);
		data.put("sharePage", sharePage);
		data.put("totalDataNumber", holidayCount);
		data.put("schedules", holidays);

		return data;
	}



	/*휴가신청내역 조회*/
	/*	@GetMapping("/holiday/registration")
	public String registeredHolidayPage(Model model, Authentication authentication) {

		ID에 따른 데이터 조회
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		남은 휴가일수 조회
		int usedHolidays = schdlMngService.getUsedHoliday(userId);

		model.addAttribute("usedHolidays", usedHolidays);

		return "schdl/hldyIns";
	}*/


	/********************************************************근태현황 조회********************************************************/

	/*관리자 월근태현황 조회*/
	@GetMapping("admin/schedule")
	public String getAttendanceStatus(Model model) {
		List<UserVo> locationList=userService.selectLocationCd();
		model.addAttribute("locationList", locationList);
		return "adminPageH2";
	}

	/*관리자 월근태현황 검색*/
	@PostMapping(value="admin/schedule", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<String, Object>>processSearchRequest(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, 
			@ModelAttribute UserVo userVo) {

		/*데이터확인*/
		System.out.println("startDate: " + startDate + ", endDate: " + endDate);

		String year = null;
		String month = null;

		String dateString = endDate; // 주어진 문자열
		String[] parts = dateString.split("-"); // '-'를 구분자로 문자열 분할

		if (parts.length >= 2) {
			year = parts[0]; // 년도 추출
			month = parts[1]; // 월 추출

			// 추출한 년도와 월 출력
			System.out.println("Year: " + year);
			System.out.println("Month: " + month);
		}

		if(userVo.getLocationCd() == null || userVo.getLocationCd().equals("")) {
			userVo.setLocationCd(userVo.getLocation());
		}
		System.out.println("userVo: " + userVo);

		List<String> idList = schdlMngService.getIdList(userVo);
		List<Map<String, Object>> allSchedules = schdlMngService.getAllSchedules(idList, year, month);


		return allSchedules;
	}

	/*특정 스케줄 상세조회*/
	@PostMapping("/schedule-Info")
	@ResponseBody
	public Object getScheduleInfo(@RequestParam("userId") String userId,
			@RequestParam("scheduleType") String scheduleType,
			@RequestParam("scheduleDate") String scheduleDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(scheduleDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 시간 정보를 아예 제거하여 새로운 Date 객체 생성
		Date sqlDate = new Date(date.getTime());


		/*데이터 확인*/
		System.out.println("userId: " + userId);
		System.out.println("scheduleType: " + scheduleType);
		System.out.println("sqlDate: " + sqlDate);

		switch(scheduleType) {
		case "holiday":
			HolidayVo holiday = schdlMngService.getHoliday(userId, sqlDate);
			return holiday;
		case "assign":
			AsAssignVo assign = schdlMngService.getAssign(userId, sqlDate);
			return assign;
		case "result":
			AsResultVo result = schdlMngService.getResult(userId, sqlDate);
			return result;
		}

		return null;

	}

/******************************************************캘린더 조회******************************************************/

	/*캘린더 조회*/
	@GetMapping("/schedule/calendar")
	public String getCalendar() {
		
		return "schdlTable";
	}

	/*회원 캘린더 월별 조회*/
	@PostMapping(value="/schedule/calendar", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<String, Object>> getUserCalendar(
																												@RequestParam(name = "year", defaultValue="2023") int  year, 
																												@RequestParam(name = "month", defaultValue="9") int month, 
																												Model model, 
																												Authentication authentication) {
		
		String stringYear = String.valueOf(year);
		String stringMonth = String.valueOf(month);
		
		/*매개변수 확인*/
		System.out.println("year: " + year);
		System.out.println("month: " + month);
		
		/*ID에 따른 데이터 조회*/
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		List<String> ids = new ArrayList<>();
		ids.add(userId);

		List<Map<String, Object>> schedules = schdlMngService.getAllSchedules(ids, stringYear, stringMonth);

		return schedules;
	}


	/******************************************************휴가취소******************************************************/


	/*휴가취소*/
	@GetMapping(value="/holiday/delete", produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String deleteHoliday(@RequestParam("scheduleId") Integer scheduleId, HttpSession session ) {

		/*파라미터 데이터 확인*/
		System.out.println("uri: /holiday/delete, holidaySeq: " + scheduleId);
		schdlMngService.cancelHoliday(scheduleId);
		String message = "휴가 취소되었습니다.";

		return message;
	}
}
