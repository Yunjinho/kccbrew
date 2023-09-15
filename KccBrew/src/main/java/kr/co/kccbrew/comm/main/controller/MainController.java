package kr.co.kccbrew.comm.main.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;
import kr.co.kccbrew.userMng.model.UserMngVo;
import kr.co.kccbrew.userMng.service.UserMngService;

@Controller
public class MainController {
	@Autowired
	MainService mainServiceImple;
	
	/***** 테스트 ******/
	@RequestMapping(value="/testpage", method=RequestMethod.GET)
	public String goTestPage(Model model) {
		return "testPage";
	}
	
	/****************** 관리자 페이지 **************************/
	
	//점포 등록 페이지
	@RequestMapping(value="/adminstoreregpage", method=RequestMethod.GET)
	public String adminStoreRegPage(Model model) {
		return "adminPageS1";
	}
	
	//점포 조회 페이지
	@RequestMapping(value="/adminstorelistpage", method=RequestMethod.GET)
	public String adminStoreListPage(Model model) {
		return "adminPageS2";
	}
	
	//점포 검색 페이지
	@RequestMapping(value="/adminstoresearchpage", method=RequestMethod.GET)
	public String adminStoreSearchPage(Model model) {
		return "adminPageS3";
	}
	
	//회원 관리 페이지
	@RequestMapping(value="/membermngpage", method=RequestMethod.GET)
	public String memberMngPage(Model model) {
		return "adminPageM1";
	}
	
	//로그 조회 페이지
	@RequestMapping(value="/adminlogpage", method=RequestMethod.GET)
	public String adminLogPage(Model model) {
		return "adminPageL1";
	}
	
	//파일 조회 페이지
	@RequestMapping(value="/adminfilepage", method=RequestMethod.GET)
	public String adminFilePage(Model model) {
		return "adminPageF1";
	}
	
	//코드 관리 페이지
	
	
	
	
	
	
	
	
	
	/************************ 점주 페이지 ******************************/
	
	//점포 조회 페이지
	@RequestMapping(value="/storesearchpage", method=RequestMethod.GET)
	public String goStoreSearchPage(Model model) {
		return "managerPageS1";
	}
	
	//점포 수정 페이지
	@RequestMapping(value="/storemodpage", method=RequestMethod.GET)
	public String goStoreModPage(Model model) {
		return "managerPageS2";
	}
	
	//마이 페이지	
	
	
	/*************************** 수리 기사 페이지 **************************/
	
	//일정 조회
	
	//마이페이지	
	

	/************************** 관리자 메인 ***************************/
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
	
	/************************** 점주 메인 ***************************/
	@RequestMapping(value="/managerMain", method=RequestMethod.GET)
	public String mngMain(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String userIdInSession = (String)session.getAttribute("userId");
		if (userIdInSession == "null") {
			//redirectAttributes.addFlashAttribute("userState", "로그인");
			return "loginPage";
		} else {
			String userId = userIdInSession;
			List<MainPageVo> asAssignListById = mainServiceImple.showAsAssiginListbyId(userId);          //a/s 배정 리스트
			List<MainPageVo> asListById 	= mainServiceImple.showAsInfoListbyId(userId);		   //a/s 접수 리스트
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
		    
		    List<MainPageVo> managerDailyData = mainServiceImple.getDataInRangeById(userId, startOfDay, endOfDay);
		    List<MainPageVo> managerWeeklyData = mainServiceImple.getDataInRangeById(userId, startOfWeek, endOfWeek);
		    List<MainPageVo> managerMonthlyData = mainServiceImple.getDataInRangeById(userId, startOfMonth, endOfMonth);
		    
		    model.addAttribute("managerDailyData", managerDailyData);
		    model.addAttribute("managerWeeklyData", managerWeeklyData);
		    model.addAttribute("managerMonthlyData", managerMonthlyData);

			return "managerPage";
		}
		
	}
	
	/************************** 수리 기사 메인 ***************************/
	@RequestMapping(value="/mechanicMain", method=RequestMethod.GET)
	public String mecMain(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		String userIdInSession = (String)session.getAttribute("userId");
		if (userIdInSession == "null") {
			//redirectAttributes.addFlashAttribute("userState", "로그인");
			return "loginPage";
		} else {
			String userId = userIdInSession;
			List<MainPageVo> asAssignList = mainServiceImple.showAsAssignListbyMechaId(userId);          //a/s 배정 리스트
			List<MainPageVo> resultList = mainServiceImple.showAsResultListbyMechaId(userId);							// a/s 결과 리스트
			model.addAttribute("asAssignListbyMechaId", asAssignList);
			model.addAttribute("resultListbyMechaId", resultList);
			
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
		    
		    List<MainPageVo> mechaDailyData = mainServiceImple.getMechaDataInRangeById(userId, startOfDay, endOfDay);
		    List<MainPageVo> mechaWeeklyData = mainServiceImple.getMechaDataInRangeById(userId, startOfWeek, endOfWeek);
		    List<MainPageVo> mechaMonthlyData = mainServiceImple.getMechaDataInRangeById(userId, startOfMonth, endOfMonth);
		    
		    model.addAttribute("mechaDailyData", mechaDailyData);
		    model.addAttribute("mechaWeeklyData", mechaWeeklyData);
		    model.addAttribute("mechaMonthlyData", mechaMonthlyData);
		
			return "mechanicPage";
		}
	}
	
	@RequestMapping(value="/privacy", method=RequestMethod.GET)
	public String openPrivacy() {
		return "comm/main/privacy";
	}
	
	/****************** 사용자 정보 가져오기 *********************/
	@RequestMapping(value = "/userinfo", method = RequestMethod.GET)
	public String showUserInfo(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		List<MainPageVo> userInfoList = mainServiceImple.showUserInfoListById(userId);
		List<MainPageVo> storeInfoList = mainServiceImple.showStoreInfoListById(userId);
		
		model.addAttribute("userInfoList", userInfoList);
		model.addAttribute("storeInfoList",storeInfoList);
		return "adminPageP1";

	}
}
