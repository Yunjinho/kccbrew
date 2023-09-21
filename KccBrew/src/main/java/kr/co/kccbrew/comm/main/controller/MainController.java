package kr.co.kccbrew.comm.main.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;
import kr.co.kccbrew.comm.security.model.UserVo;

@Controller
public class MainController {
	@Autowired
	MainService mainServiceImple;

	/****************** 마이페이지 *********************/
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String showUserInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				List<MainPageVo> userInfoList = mainServiceImple.showUserInfoListById(userId);
				List<MainPageVo> storeInfoList = mainServiceImple.showStoreInfoListById(userId);

				model.addAttribute("userInfoList", userInfoList);
				model.addAttribute("storeInfoList",storeInfoList);
			}
		}
		return "MyPageP1";

	}
	
	/****************** 마이페이지 수정 *********************/
	@RequestMapping(value = "/mypage/mod", method = RequestMethod.GET)
	public String modUserInfo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				List<MainPageVo> userInfoList = mainServiceImple.showUserInfoListById(userId);
				List<MainPageVo> storeInfoList = mainServiceImple.showStoreInfoListById(userId);

				model.addAttribute("userInfoList", userInfoList);
				model.addAttribute("storeInfoList",storeInfoList);
			}
		}
		return "MyPageP2";
	}
	
	@RequestMapping(value= "/confirmmod", method = RequestMethod.POST)
	public String confirmModProfile(Model model, @ModelAttribute MainPageVo mainPageVo,
						            @RequestParam("machineCode") String machineCode,
						            @RequestParam("mechaLocationCode") String mechaLocationCode) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				mainPageVo.setUserId(userId);
				mainPageVo.setMachineCode(machineCode);
	            mainPageVo.setMechaLocationCode(mechaLocationCode);
				mainServiceImple.updateMyProfile(mainPageVo);
			}
		}
		return "redirect:/mypage";
	}
	
	/******************* 비밀번호 변경 *********************/
	
	@RequestMapping(value = "/mypage/chgpwd", method = RequestMethod.GET)
	public String chgPassword(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				List<MainPageVo> userInfoList = mainServiceImple.showUserInfoListById(userId);

				model.addAttribute("userInfoList", userInfoList);
			}
		}
		return "MyPageP3";
	}
	
	@RequestMapping(value= "/confirmchg", method = RequestMethod.POST)
	public String confirmChange(Model model, @ModelAttribute MainPageVo mainPageVo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				mainPageVo.setUserId(userId);
				mainServiceImple.updateMyProfile(mainPageVo);
			}
		}
		return "redirect:/mypage";
	}
	
	/****************** 관리자 페이지 **************************/

	//회원 관리 페이지
	@RequestMapping(value="/admin/member/manage", method=RequestMethod.GET)
	public String memberMngPage(Model model) {
		return "adminPageM1";
	}

	//로그 조회 페이지
	@RequestMapping(value="/admin/log/check", method=RequestMethod.GET)
	public String adminLogPage(Model model) {
		return "adminPageL1";
	}

	//파일 조회 페이지
	@RequestMapping(value="/admin/file/check", method=RequestMethod.GET)
	public String adminFilePage(Model model) {
		return "adminPageF1";
	}

	//코드 관리 페이지
	@RequestMapping(value="/admin/code/check", method=RequestMethod.GET)
	public String adminCodePage(Model model) {
		return "adminPageC1";
	}
	


	
	/************************ 점주 페이지 ******************************/

	//점포 수정 페이지
	@RequestMapping(value="/manager/store/mod", method=RequestMethod.GET)
	public String goStoreModPage(Model model) {
		return "modStoreInfo";
	}



	/*************************** 수리 기사 페이지 **************************/


	
	
	
	
	
	
	/************************** 관리자 메인 ***************************/
	@RequestMapping(value="/admin/main", method=RequestMethod.GET)
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
	@RequestMapping(value="/manager/main", method=RequestMethod.GET)
	public String mngMain(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

		UserVo userVo = (UserVo) session.getAttribute("user");
		String userId = userVo.getUserId();

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

	/************************** 수리 기사 메인 ***************************/
	@RequestMapping(value="/mechanic/main", method=RequestMethod.GET)
	public String mechaMain(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		UserVo userVo = (UserVo) session.getAttribute("user");
		String userId = userVo.getUserId();

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


	@RequestMapping(value="/privacy", method=RequestMethod.GET)
	public String openPrivacy() {
		return "comm/main/privacy";
	}

}
