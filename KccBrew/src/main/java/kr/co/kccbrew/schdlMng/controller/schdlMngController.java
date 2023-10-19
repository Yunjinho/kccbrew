package kr.co.kccbrew.schdlMng.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
import kr.co.kccbrew.comm.util.DateFormat;
import kr.co.kccbrew.schdlMng.model.AsAssignVo;
import kr.co.kccbrew.schdlMng.model.AsResultVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.service.SchdlMngService;
import lombok.RequiredArgsConstructor;
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
 * 2023-10-06                       이세은               휴가 등록 시 유효성 검사 수정
 * 2023-10-17						  이세은					 월근태현황 날짜 조회 오류 수정
 * 
 * @author LEESEEUN
 * @version 1.0
 */

@Controller
@Slf4j
@RequiredArgsConstructor
public class schdlMngController {

	@Autowired
	private SchdlMngService schdlMngService;
	@Autowired
	private UserService userService;
	@Autowired
	private DateFormat dateFormat;

	private final IAsMngService asMngService;

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

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if ("ROLE_MANAGER".equals(role)) {
				List<AsMngVo> vo=asMngService.selectStrInfo(userVo.getUserId());
				model.addAttribute("strInfo", vo);
			}
		}
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

		List<HolidayVo> vacationDates = schdlMngService.getHolidays(userId);
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
	public boolean isAsAssignDate(
			Date startDate, 
			Date endDate, 
			String userId) {
		
		log.info("schdelMngController.isAsAssignDate");
		log.info("startDate: " + startDate);
		log.info("endDate: " + endDate);

		List<SchdlMngVo> asAssignDates = schdlMngService.getAssignDates(userId);

		boolean isOverlap = false;

		for (SchdlMngVo schdlMngVo : asAssignDates) {
			log.info("배정일: " + schdlMngVo.getVisitDate());
			if (schdlMngVo.getVisitDate().compareTo(startDate) >= 0 && schdlMngVo.getVisitDate().compareTo(endDate) <= 0) {
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

	/**
	 * (관리자)휴가검색 - 관리자가 검색한 조건에 따른 휴가리스트를 보여준다.
	 * 
	 * @param int currentPage - 현재 페이지 
	 * @param String startDate - 기간검색 중 시작일
	 * @param String endDate - 기간검색 중 종료일
	 * @param UserVo userVo - 사용자 관련 검색조건
	 * 
	 * @return Map<String, Object> getSearchedHolidaysForAdmin - 페이징과 검색결과에 따른 휴가리스트
	 * @throws 
	 */
	@PostMapping(value="/admin/holiday/search", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getSearchedHolidaysForAdmin(
			@RequestParam(name="currentPage", defaultValue="1") int currentPage,
			@RequestParam(name="startDate", required = false) String startDate,
			@RequestParam(name="endDate", required = false) String endDate,
			@ModelAttribute UserVo userVo
			) {
		System.out.println("schdlMngController.getSearchedHolidays");

		/*string -> sql.date 형변환*/
		Date startSqlDate = null;
		Date endSqlDate = null;

		if (startDate == null || startDate.equals("")) {
			startSqlDate = dateFormat.getFirstDayOfYear();
		} else {
			startSqlDate = dateFormat.stringToSqlDate(startDate);	
		}

		if(endDate == null || endDate.equals("")) {
			endSqlDate = dateFormat.getLastDayOfYear();
		} else {
			endSqlDate = dateFormat.stringToSqlDate(endDate);	
		}

		if(userVo.getLocationCd() == null || userVo.getLocationCd().equals("")) {
			userVo.setLocationCd(userVo.getLocation());
		}

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



	/**
	 * (회원)휴가검색 - 회원(점주, 수리기사)이 검색한 조건에 따른 휴가리스트를 보여준다.
	 * 
	 * @param int currentPage - 현재 페이지 
	 * @param String startDate - 기간검색 중 시작일
	 * @param String endDate - 기간검색 중 종료일
	 * @param Authentication authentication - 스프링시큐리티의 인가를 통해서 회원확인
	 * 
	 * @return Map<String, Object> getSearchedHolidays - 페이징과 검색결과에 따른 휴가리스트
	 * @throws 
	 */
	@PostMapping(value="/holiday/search", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getSearchedHolidays(
			@RequestParam(name="currentPage", defaultValue="1") int currentPage,
			@RequestParam(name="startDate", required = false) String startDate,
			@RequestParam(name="endDate", required = false) String endDate,
			Authentication authentication
			) {
		System.out.println("schdlMngController.getSearchedHolidays");

		/*string -> sql.date 형변환*/
		Date startSqlDate = null;
		Date endSqlDate = null;

		if (startDate == null || startDate.equals("")) {
			startSqlDate = dateFormat.getFirstDayOfYear();
		} else {
			startSqlDate = dateFormat.stringToSqlDate(startDate);	
		}

		if(endDate == null || endDate.equals("")) {
			endSqlDate = dateFormat.getLastDayOfYear();
		} else {
			endSqlDate = dateFormat.stringToSqlDate(endDate);	
		}

		/*ID에 따른 데이터 조회*/
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();

		UserVo userVo = new UserVo();
		userVo.setUserId(userId);

		if(userVo.getLocationCd() == null || userVo.getLocationCd().equals("")) {
			userVo.setLocationCd(userVo.getLocation());
		}

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
		return "adminSchdlList";
	}

	/*관리자 월근태현황 검색*/
	@PostMapping(value="admin/schedule", produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<Map<String, Object>>processSearchRequest(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, 
			@ModelAttribute UserVo userVo,
			Authentication authentication) {

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
		userVo.setUserTypeCd("03");

		System.out.println("userVo: " + userVo);

		String userTypeCd="";
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

		List<String> idList = schdlMngService.getIdList(userVo);
		List<Map<String, Object>> allSchedules = schdlMngService.getAllSchedules(idList, year, month,userTypeCd);


		return allSchedules;
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
		List<String> ids = new ArrayList<>();

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String role = "";
		String userTypeCd="";
		for ( GrantedAuthority authority : userDetails.getAuthorities()) {
			role = authority.getAuthority();
		}
		if(role.equals("ROLE_ADMIN")) {
			ids = null;
		} else {
			/*점주인 경우*/
			if ("ROLE_MANAGER".equals(role)) {
				userTypeCd = "02";
				/*수리기사인 경우*/
			} else if ("ROLE_MECHA".equals(role)) {
				userTypeCd = "03";
			}
			String userId = userDetails.getUsername();
			ids.add(userId);
		}

		List<Map<String, Object>> schedules = schdlMngService.getAllSchedules(ids, stringYear, stringMonth,userTypeCd);

		return schedules;
	}


	/*특정 스케줄 상세조회*/
	@PostMapping(value="/schedule-Info")
	@ResponseBody
	public Object getScheduleInfo(
			@RequestParam("userId") String userId,
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
			List<HolidayVo> holidays = schdlMngService.getHolidays(userId, sqlDate);
			return holidays;
		case "assign":
			List<AsAssignVo> assigns = schdlMngService.getAssigns(userId, sqlDate);
			System.out.println("assigns: " + assigns);
			return assigns;
		case "result":
			List<AsResultVo> results = schdlMngService.getResults(userId, sqlDate);
			return results;
		}

		return null;

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

	@PostMapping(value="/download-holiday-list")
	@ResponseBody
	public String downloadHolidayList(String flag,@RequestParam(defaultValue = "1")String page,@RequestParam(defaultValue = "")String location,@RequestParam(defaultValue = "")String locationCd,
			@RequestParam(defaultValue = "")String startDate,@RequestParam(defaultValue = "")String endDate,
			@RequestParam(defaultValue = "")String userType,@RequestParam(defaultValue = "")String userId,
			@RequestParam(defaultValue = "")String userName,@RequestParam(defaultValue = "")String storeId,
			@RequestParam(defaultValue = "")String storeName,@RequestParam(defaultValue = "")String selectedEndDate,
			@RequestParam(defaultValue = "")String selectedStartDate,Authentication authentication) {
		UserVo userVo=new UserVo();
		userVo.setStoreNm(storeName);
		userVo.setFlag(flag);
		userVo.setLocation(location);
		userVo.setLocationCd(locationCd);
		userVo.setUserType(userType);
		userVo.setUserNm(userName);
		if(storeId!=null && !storeId.equals("")) {
			userVo.setStoreId(Integer.parseInt(storeId));
		}
		userVo.setStoreNm(storeName);

		/*시큐리티로 사용자 역할(role) 확인*/
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String userRole = authority.getAuthority();
			/*string -> sql.date 형변환*/
			Date startSqlDate = null;
			Date endSqlDate = null;

			if ((startDate == null || startDate.equals("")) && (selectedStartDate == null || selectedStartDate.equals(""))) {
				startSqlDate = dateFormat.getFirstDayOfYear();
			} else if(selectedStartDate == null || selectedStartDate.equals("")){
				startSqlDate = dateFormat.stringToSqlDate(startDate);	
			}else {
				startSqlDate = dateFormat.stringToSqlDate(selectedStartDate);	
			}

			if((endDate == null || endDate.equals("")) && (selectedEndDate == null || selectedEndDate.equals("")) ) {
				endSqlDate = dateFormat.getLastDayOfYear();
			}else if(selectedEndDate == null || selectedEndDate.equals("")) {
				endSqlDate = dateFormat.stringToSqlDate(endDate);	
			}else {
				endSqlDate = dateFormat.stringToSqlDate(selectedEndDate);	
			}

			
			
			List<SchdlMngVo> list;
			Map<Integer, Object[]> data = new HashMap();
			
			if(locationCd == null || locationCd.equals("")) {
				userVo.setLocationCd(location);
			}
			int role;
			if ("ROLE_ADMIN".equals(userRole)) {
				role=1;
				data.put(1, new Object[]{"사용자분류", "ID", "휴가번호", "신청일", "시작일", "종료일", "휴가상태", "사용여부"});
			} else if("ROLE_MANAGER".equals(userRole)) {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				userId = userDetails.getUsername();
				userVo.setUserId(userId);
				data.put(1, new Object[]{"지점명", "신청일", "시작일", "종료일", "휴가상태", "사용여부"});
				role=2;
			}else {
				UserDetails userDetails = (UserDetails) authentication.getPrincipal();
				userId = userDetails.getUsername();
				userVo.setUserId(userId);
				data.put(1, new Object[]{"신청일", "시작일", "종료일", "휴가상태", "사용여부"});
				role=3;
			}
			
			
			if(userVo.getFlag().equals("1")) {
				//현재 페이지 저장
				list=schdlMngService.getHolidays(Integer.parseInt(page), startSqlDate, endSqlDate, userVo);
		        for(int i=0;i<list.size();i++) {
		        	String useHoliday="";
		        	LocalDate currentDate = LocalDate.now();
		            LocalDate comparisonDate = list.get(i).getStartDate().toLocalDate();
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            
		            if(list.get(i).getActualUse().equals("N")) {
		        		useHoliday="취소완료";
		        	}else if(currentDate.isAfter(comparisonDate)) {
		        		useHoliday="이미 사용된 휴가";
		        	}else {
		        		useHoliday="사용 예정";
		        	}
		            if(role==1) {
		            	data.put(i+2,new Object[]{list.get(i).getUserType(),list.get(i).getUserId(),list.get(i).getScheduleId()
		            			,dateFormat.format(list.get(i).getAppDate())
		            			,dateFormat.format(list.get(i).getStartDate())
		            			,dateFormat.format(list.get(i).getEndDate())
		            			,useHoliday
		            			,list.get(i).getActualUse()});
		            }else if(role==2) {
		            	data.put(i+2,new Object[]{list.get(i).getStoreName()
		            			,dateFormat.format(list.get(i).getAppDate())
		            			,dateFormat.format(list.get(i).getStartDate())
		            			,dateFormat.format(list.get(i).getEndDate())
		            			,useHoliday
		            			,list.get(i).getActualUse()});
		            }else {
		            	data.put(i+2,new Object[]{ dateFormat.format(list.get(i).getAppDate())
		            			,dateFormat.format(list.get(i).getStartDate())
		            			,dateFormat.format(list.get(i).getEndDate())
		            			,useHoliday
		            			,list.get(i).getActualUse()});
		            }
		        }
			}else {
				//전체 페이지 저장
				list=schdlMngService.getAllHolidays(Integer.parseInt(page), startSqlDate, endSqlDate, userVo);
				
				 for(int i=0;i<list.size();i++) {
			        	String useHoliday="";
			        	LocalDate currentDate = LocalDate.now();
			            LocalDate comparisonDate = list.get(i).getStartDate().toLocalDate();
			            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			            
			            if(list.get(i).getActualUse().equals("N")) {
			        		useHoliday="취소완료";
			        	}else if(currentDate.isAfter(comparisonDate)) {
			        		useHoliday="이미 사용된 휴가";
			        	}else {
			        		useHoliday="사용 예정";
			        	}
			            if(role==1) {
			            	data.put(i+2,new Object[]{list.get(i).getUserType(),list.get(i).getUserId(),list.get(i).getScheduleId()
			            			,dateFormat.format(list.get(i).getAppDate())
			            			,dateFormat.format(list.get(i).getStartDate())
			            			,dateFormat.format(list.get(i).getEndDate())
			            			,useHoliday
			            			,list.get(i).getActualUse()});
			            }else if(role==2) {
			            	data.put(i+2,new Object[]{list.get(i).getStoreName()
			            			,dateFormat.format(list.get(i).getAppDate())
			            			,dateFormat.format(list.get(i).getStartDate())
			            			,dateFormat.format(list.get(i).getEndDate())
			            			,useHoliday
			            			,list.get(i).getActualUse()});
			            }else {
			            	data.put(i+2,new Object[]{ dateFormat.format(list.get(i).getAppDate())
			            			,dateFormat.format(list.get(i).getStartDate())
			            			,dateFormat.format(list.get(i).getEndDate())
			            			,useHoliday
			            			,list.get(i).getActualUse()});
			            }
			        }
			}
			XSSFWorkbook workbook = new XSSFWorkbook();
	       // 빈 Sheet를 생성
	       XSSFSheet sheet = workbook.createSheet("조회한 휴가 목록");

	       // Sheet를 채우기 위한 데이터들을 Map에 저장

	       // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
	       Set<Integer> keyset = data.keySet();
	       int rownum = 0;
	       	
	       for (Integer key : keyset) {
	           Row row = sheet.createRow(rownum++);
	           Object[] objArr = data.get(key);
	           int cellnum = 0;
	           for (Object obj : objArr) {
	               Cell cell = row.createCell(cellnum++);
	               if (obj instanceof String) {
	                   cell.setCellValue((String)obj);
	               } else if (obj instanceof Integer) {
	                   cell.setCellValue((Integer)obj);
	               }
	           }
	       }

	       try {
	           // 현재 날짜 구하기
	           LocalDateTime now = LocalDateTime.now();
	           // 포맷 정의
	           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	           // 포맷 적용
	           String formatedNow = now.format(formatter);
	    
	           FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" ,formatedNow+"_holiday_list.xlsx"));
	           workbook.write(out);
	           
	           out.close();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
		}
		return "";
	}
}
