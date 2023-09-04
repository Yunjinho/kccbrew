package kr.co.kccbrew.comm.main.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MainService mainServiceImple;
	
	/*@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		return "comm/main/main";
	}*/
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String adminMain(Model model) {
		List<MainPageVo> assignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList 	= mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("assignList", assignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		// 이번 주 범위 구하기
		LocalDate now = LocalDate.now(); // 현재 날짜
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    model.addAttribute("weeklyData", weeklyData);
	
		return "/WEB-INF/views/comm/main.jsp";
	}
	
	@RequestMapping(value="/managerMain", method=RequestMethod.GET)
	public String managerMain(Model model) {
		List<MainPageVo> assignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList = mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("assignList", assignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		// 이번 주 범위 구하기
		LocalDate now = LocalDate.now(); // 현재 날짜
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    model.addAttribute("weeklyData", weeklyData);
		return "comm/main/managerMain";
	}
	
	@RequestMapping(value="/mechanicMain", method=RequestMethod.GET)
	public String mechanicMain(Model model) {
		List<MainPageVo> assignList = mainServiceImple.showAllAssignList();          //a/s 배정 리스트
		List<MainPageVo> asList = mainServiceImple.showAllAsInfoList();			   //a/s 접수 리스트
		List<MainPageVo> waitingList = mainServiceImple.showWaitingMemberList();     //회원 승인 대기 리스트
		List<MainPageVo> resultList = mainServiceImple.showAsResultList();			// a/s 결과 리스트
		model.addAttribute("assignList", assignList);
		model.addAttribute("asList", asList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("resultList", resultList);
		
		// 이번 주 범위 구하기
		LocalDate now = LocalDate.now(); // 현재 날짜
	    LocalDate startOfWeek = now.with(DayOfWeek.MONDAY); // 이번 주의 월요일
	    LocalDate endOfWeek = now.with(DayOfWeek.SUNDAY);   // 이번 주의 일요일
	    List<MainPageVo> weeklyData = mainServiceImple.getDataInRange(startOfWeek, endOfWeek);
	    model.addAttribute("weeklyData", weeklyData);
		return "comm/main/mechanicMain";
	}
	@RequestMapping(value="/privacy", method=RequestMethod.GET)
	public String openPrivacy() {
		return "comm/main/privacy";
	}
}
