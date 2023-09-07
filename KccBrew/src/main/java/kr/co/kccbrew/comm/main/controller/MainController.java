package kr.co.kccbrew.comm.main.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;

@Controller
public class MainController {
	@Autowired
	MainService mainServiceImple;
	
	/**************** 로그인 페이지 *****************************/
	@RequestMapping(value="/goLoginPage", method=RequestMethod.GET)
	public String goLoginPage(Model model) {
		return "loginPage";
	}
	
	/**************** 회원가입 페이지 *****************************/
	@RequestMapping(value="/goRegisterPage", method=RequestMethod.GET)
	public String goRegisterPage(Model model) {
		return "registerPage";
	}
	
	/******************  관리자 페이지 **************************/
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test1(Model model) {
		return "adminPageT1";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	public String test2(Model model) {
		return "adminPageT2";
	}
	
	@RequestMapping(value="/test3", method=RequestMethod.GET)
	public String test3(Model model) {
		return "adminPageT3";
	}
	
	
	
	
	
	
	
	
	
	/************************ 점주 페이지 ******************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*************************** 수리 기사 페이지 **************************/
	
	
	
	
	
	
	
	
	
	//사용자 유형별 페이지 구분 ==> 건우야 이거 jsp에서 구분해도돼~
//	@RequestMapping(value="/homepage", method=RequestMethod.GET)  
//	public String homePage(Model model, HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		String userTypeCd = (String) session.getAttribute("userTypeCd");
//		
//		if ("01".equals(userTypeCd)) { 				//관리자
//	        return "adminPage";
//	    } else if ("02".equals(userTypeCd)) {		//점주
//	        return "managerPage";
//	    } else if ("03".equals(userTypeCd)) {		//수리 기사
//	        return "mechanicPage";
//	    } else {
//	        return "adminPageT1";
//	    }
//	}
	
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public String admMain(Model model) {
		List<MainPageVo> asAssignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList 	= mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("asAssignList", asAssignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		LocalDate now = LocalDate.now(); // 현재 날짜
		
		//하루 구하기
		LocalDate startOfDay = now;
		LocalDate endOfDay = now;
		
		//이번 주 구하기
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    
	    //이번 달 구하기
	    LocalDate startOfMonth = now.withDayOfMonth(1); //이번 달 시작일
	    LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth()); //이번 달 종료일
	    
	    List<MainPageVo> dailyData = mainServiceImple.getDataInRange(startOfDay, endOfDay);
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    List<MainPageVo> monthlyData = mainServiceImple.getDataInRange(startOfMonth, endOfMonth);
	    
	    model.addAttribute("dailyData", dailyData);
	    model.addAttribute("weeklyData", weeklyData);
	    model.addAttribute("monthlyData", monthlyData);
	
		return "adminPage";
	}
	

	@RequestMapping(value="/managerMain", method=RequestMethod.GET)
	public String mngMain(Model model) {
		List<MainPageVo> asAssignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList 	= mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("asAssignList", asAssignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		LocalDate now = LocalDate.now(); // 현재 날짜
		
		//하루 구하기
		LocalDate startOfDay = now;
		LocalDate endOfDay = now;
		
		//이번 주 구하기
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    
	    //이번 달 구하기
	    LocalDate startOfMonth = now.withDayOfMonth(1); //이번 달 시작일
	    LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth()); //이번 달 종료일
	    
	    List<MainPageVo> dailyData = mainServiceImple.getDataInRange(startOfDay, endOfDay);
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    List<MainPageVo> monthlyData = mainServiceImple.getDataInRange(startOfMonth, endOfMonth);
	    
	    model.addAttribute("dailyData", dailyData);
	    model.addAttribute("weeklyData", weeklyData);
	    model.addAttribute("monthlyData", monthlyData);
	
		return "managerPage";
	}
	

	@RequestMapping(value="/mechanicMain", method=RequestMethod.GET)
	public String mecMain(Model model) {
		List<MainPageVo> asAssignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList 	= mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("asAssignList", asAssignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		LocalDate now = LocalDate.now(); // 현재 날짜
		
		//하루 구하기
		LocalDate startOfDay = now;
		LocalDate endOfDay = now;
		
		//이번 주 구하기
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    
	    //이번 달 구하기
	    LocalDate startOfMonth = now.withDayOfMonth(1); //이번 달 시작일
	    LocalDate endOfMonth = now.withDayOfMonth(now.lengthOfMonth()); //이번 달 종료일
	    
	    List<MainPageVo> dailyData = mainServiceImple.getDataInRange(startOfDay, endOfDay);
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    List<MainPageVo> monthlyData = mainServiceImple.getDataInRange(startOfMonth, endOfMonth);
	    
	    model.addAttribute("dailyData", dailyData);
	    model.addAttribute("weeklyData", weeklyData);
	    model.addAttribute("monthlyData", monthlyData);
	
		return "mechanicPage";
	}
	
	@RequestMapping(value="/privacy", method=RequestMethod.GET)
	public String openPrivacy() {
		return "comm/main/privacy";
	}
}
