package kr.co.kccbrew.schdlMng.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo2;
import kr.co.kccbrew.schdlMng.service.SchdlMngService;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import lombok.extern.slf4j.Slf4j;

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
	
	
}
