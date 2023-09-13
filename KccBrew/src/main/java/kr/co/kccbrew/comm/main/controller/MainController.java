package kr.co.kccbrew.comm.main.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;

@Controller
public class MainController {
	@Autowired
	MainService mainServiceImple;
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();	
		String userType=(String) session.getAttribute("userTypeCd");
		String userId=(String) session.getAttribute("userId");
		if(userType.equals("01")) {
			List<MainPageVo> asAssignList = mainServiceImple.showAllAsAssignList();          //a/s 배정 리스트
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
		}else if(userType.equals("02")) {
			List<MainPageVo> asAssignListById = mainServiceImple.showAsInfoListbyId(userId);          //a/s 배정 리스트
			List<MainPageVo> asListById 	= mainServiceImple.showAsAssiginListbyId(userId);		   //a/s 접수 리스트
			List<MainPageVo> resultListById = mainServiceImple.showAsResultListbyId(userId);		// a/s 결과 리스트
			model.addAttribute("asAssignListById", asAssignListById);
			model.addAttribute("asListById", asListById);
			model.addAttribute("resultListById", resultListById);
			
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
		}else {
			List<MainPageVo> asAssignList = mainServiceImple.showAllAsAssignList();          //a/s 배정 리스트
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
		
	}
	
	
	
	/*************************** 수리 기사 페이지 **************************/
	
	
	
	
	

	
	@RequestMapping(value="/adminMain", method=RequestMethod.GET)
	public String admMain(Model model) {
		List<MainPageVo> asAssignList = mainServiceImple.showAllAsAssignList();          //a/s 배정 리스트
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
	public String mngMain(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String userIdInSession = String.valueOf(session.getAttribute("userId"));
		if (userIdInSession == "null") {
			//redirectAttributes.addFlashAttribute("userState", "로그인");
			return "loginPage";
		} else {
			String userId = userIdInSession;
			List<MainPageVo> asAssignListById = mainServiceImple.showAsInfoListbyId(userId);          //a/s 배정 리스트
			List<MainPageVo> asListById 	= mainServiceImple.showAsAssiginListbyId(userId);		   //a/s 접수 리스트
			List<MainPageVo> resultListById = mainServiceImple.showAsResultListbyId(userId);		// a/s 결과 리스트
			model.addAttribute("asAssignListById", asAssignListById);
			model.addAttribute("asListById", asListById);
			model.addAttribute("resultListById", resultListById);
			
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
		
	}
	

	@RequestMapping(value="/mechanicMain", method=RequestMethod.GET)
	public String mecMain(Model model) {
		List<MainPageVo> asAssignList = mainServiceImple.showAllAsAssignList();          //a/s 배정 리스트
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
