package kr.co.kccbrew.schdlMng.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import kr.co.kccbrew.comm.register.model.StoreListVo;
import kr.co.kccbrew.comm.register.model.UserVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo2;
import kr.co.kccbrew.schdlMng.service.SchdlMngService;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : schdlMngController
 * @Decription : 스케줄 관리를 위한 controller
 * 
 * @   수정일                    수정자                        수정내용
 * ============      ==============     ==============
 * 2023-09-01			           이세은			             최초생성
 * 2023-09-11                       이세은               휴일등록 메서드 작성
 * 2023-09-12                       이세은               휴일기간 중복방지 유효성검사 메서드작성
 * @author YUNJINHO
 * @version 1.0
 */

@Controller
@Slf4j
public class schdlMngController {

	@Autowired
	private SchdlMngService schdlMngService;

	/* 관리자 스케줄조회 */
	@GetMapping("/schedule2")
	public String getLogs2(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") SchdlMngVo2 searchContent,
			Model model,
			HttpSession session 
			) {

		/*파라미터 확인*/
		System.out.println("searchContent: " + searchContent);

		/* 스케줄리스트 데이터 */
		List<SchdlMngVo2> schedules = schdlMngService.getSchedules2(currentPage, searchContent);
		int scheduleCount = schdlMngService.getSchedule2Count(searchContent);

		/*DB 데이터 확인*/
		System.out.println("schedules: " + schedules);
		System.out.println("scheduleCount: " + scheduleCount);

		int totalPage = 0;
		int sharePage = 0;

		//   totalPage 구하기
		if (schedules != null && !schedules.isEmpty()) {
			totalPage = (int) Math.ceil((double) scheduleCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
		}

		// sharePage 구하기
		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalDataNumber", scheduleCount);
		model.addAttribute("schedules", schedules);


		return "schdl/schdlMngList2";
	}

	/* 회원 스케줄조회 */
	@GetMapping("/schedule2/{userId}")
	public String getUserLogs(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") SchdlMngVo2 searchContent,
			Model model,
			HttpSession session 
			) {

		/*파라미터 확인*/
		System.out.println("searchContent: " + searchContent);

		/* 스케줄리스트 데이터 */
		List<SchdlMngVo2> schedules = schdlMngService.getSchedules2(currentPage, searchContent);
		int scheduleCount = schdlMngService.getSchedule2Count(searchContent);

		/*DB 데이터 확인*/
		System.out.println("schedules: " + schedules);
		System.out.println("scheduleCount: " + scheduleCount);

		int totalPage = 0;
		int sharePage = 0;

		//   totalPage 구하기
		if (schedules != null && !schedules.isEmpty()) {
			totalPage = (int) Math.ceil((double) scheduleCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
		}

		// sharePage 구하기
		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalDataNumber", scheduleCount);
		model.addAttribute("schedules", schedules);


		return "schdl/schdlMngList2";
	}

	/*관리자 캘린더 조회*/
	@GetMapping("/calendar")
	public String getCalendar() {
		return "schdl/schdlMngClndr";
	}

	/*회원 캘린더 조회*/
	@GetMapping("/calendar/{userId}")
	public String getUserCalendar(@PathVariable String userId) {
		System.out.println("userId: " + userId);
		return "schdl/schdlMngClndr";
	}

	/*회원 캘린더 월별 조회*/
	@PostMapping("/calendar")
	@ResponseBody
	public List<SchdlMngVo2> getUserCalendar(HttpServletRequest request, 
			@RequestParam("year") Integer year, @RequestParam("month") Integer month,  
			Model model) {
		/*매개변수 확인*/
		System.out.println("year: " + year);
		System.out.println("month: " + month);
		/*System.out.println("dateInfo: " + dateInfo);*/

		/*세션에서 회원정보 추출해서 dto에 저장*/
		HttpSession session = request.getSession();
		SchdlMngVo2 schdlMngVo = new SchdlMngVo2();
		schdlMngVo.setUserId("bsy01");


		/*파라미터에서 날짜정보 추출해서 dto에 저장*/
		/*		try {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		    java.util.Date jsDate = dateFormat.parse(dateInfo);
		    Date sqlDate = new Date(jsDate.getTime());
			schdlMngVo.setScheduleDate(sqlDate);

		} catch (ParseException e) {
		    e.printStackTrace();
		}*/

		List<SchdlMngVo2> schedules = schdlMngService.getCalendarSchedule(schdlMngVo);



		return schedules;
	}

	/*캘린더 테스트*/
	@GetMapping("/cal-test")
	public String calendarTest() {
		return "schdl/calendartest";
	}


	/*휴가 권한구분*/
	@GetMapping("/holiday")
	public String holidayPage(Model model, HttpSession session) {

		/*세션에서 사용자 정보 반환*/
		UserVo user = new UserVo();


		/*세션에 사용자정보가 저장되어 있는 경우*/
		String userType = "manager";
		String dynamicURL = "/" + userType + "/holiday";

		return "redirect:" + dynamicURL;

		/*세션에 사용자 정보가 저장되어 있지 않은 경우 -> 로그인페이지*/

	}



	/*점주 휴일 등록 페이지*/
	@GetMapping("/manager/holiday")
	public String holidayPage(Model model) {

		/*세션에서 점주 및 점포 정보 확인*/
		UserVo user = new UserVo();
		user.setUserId("ngw01");
		String userId = user.getUserId();

		StoreListVo store = new StoreListVo();
		store.setStoreSeq("35");
		store.setStoreNm("잠실점");

		/*휴일정보 확인*/
		List<HolidayVo> holidays = schdlMngService.getHoliday(userId);
		int totalDataNumber = holidays.size();
		int remainingDays = 15 - schdlMngService.getUsedHoliday(userId);

		model.addAttribute("holidays", holidays);
		model.addAttribute("user", user);
		model.addAttribute("store", store);
		model.addAttribute("totalDataNumber", totalDataNumber);
		model.addAttribute("remainingDays", remainingDays);

		return "schdl/schdlMngIns";
	}

	/*휴가신청*/
	@PostMapping("/holiday/add")
	public String addHoliday(@ModelAttribute HolidayVo holiday, HttpSession session ) {

		Calendar calendar = Calendar.getInstance();
		java.util.Date utilDate = calendar.getTime();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

		/*세션에서 사용자ID, 사용자코드 정보 추출*/
		/*session.getAttribute("user");*/
		String userId = "ngw01";
		String groupCodeDetailId = "02";
		holiday.setUserId(userId);
		holiday.setGroupCodeDetailId(groupCodeDetailId);
		holiday.setAppDate(sqlDate);

		System.out.println("보정한 holiday확인: " + holiday);

		if (isOverlapDate(holiday.getStartDate(), holiday.getEndDate()) || isBeforeToday(holiday.getStartDate().toString())) {
			System.out.println("부적절한 날짜 선택!");
		}
		else {
			/*휴가등록*/
			schdlMngService.addHoliday(holiday);
		}

		return "redirect:/holiday";
	}


	/*휴가 중복 검사*/
	public boolean isOverlapDate(Date startDate, Date endDate) {

		/*세션으로 회원정보 확인*/
		String userId = "ngw01";

		List<HolidayVo> vacationDates = schdlMngService.getHoliday(userId);
		Date startDateToCheck = startDate;
		Date endDateToCheck = endDate;

		boolean isOverlap = false;

		/* 휴가일 중복 검사*/
		for (HolidayVo vacation : vacationDates) {
			Date existingStartDate = vacation.getStartDate();
			Date existingEndDate = vacation.getEndDate();

			if (startDateToCheck.compareTo(existingEndDate) <= 0 && endDateToCheck.compareTo(existingStartDate) >= 0) {
				isOverlap = true;
				break; 
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
	public boolean isAsAssignDate(Date startDate, Date endDate) {

		/*세션으로 회원정보 확인*/
		String userId = "ngw01";

		/*수리배정일 리스트*/
		List<HolidayVo> vacationDates = schdlMngService.getHoliday(userId);
		Date startDateToCheck = startDate;
		Date endDateToCheck = endDate;

		boolean isOverlap = false;

		/* 휴가일 중복 검사*/
		for (HolidayVo vacation : vacationDates) {
			Date existingStartDate = vacation.getStartDate();
			Date existingEndDate = vacation.getEndDate();

			if (startDateToCheck.compareTo(existingEndDate) <= 0 && endDateToCheck.compareTo(existingStartDate) >= 0) {
				isOverlap = true;
				break; 
			}
		}
		return isOverlap;
	}


	/*휴가취소*/
	@PostMapping("/holiday/delete")
	public String deleteHoliday(@RequestParam("holidaySeq") Integer holidaySeq, HttpSession session ) {

		/*파라미터 데이터 확인*/
		System.out.println("uri: /holiday/delete, holidaySeq: " + holidaySeq);

		/*세션에서 사용자ID, 사용자코드 정보 추출*/
		/*session.getAttribute("user");*/

		/*휴가취소*/
		schdlMngService.cancelHoliday(holidaySeq);

		return "redirect:/holiday";
	}




}
