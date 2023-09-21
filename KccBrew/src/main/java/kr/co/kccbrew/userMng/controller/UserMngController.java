package kr.co.kccbrew.userMng.controller;

import java.util.List; 
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.userMng.model.UserMngVo;
import kr.co.kccbrew.userMng.service.IUserMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : UserMngController
 * @Decription : 회원 관리하기 위한 controller
 * 
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-09-11 배수연 최초생성
 * @author BAESOOYEON
 * @version 1.0
 */

@Controller
@RequiredArgsConstructor
public class UserMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IUserMngService userMngService;

	@GetMapping("/user")
	public String userAll(Model model, @RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") UserMngVo searchContent, HttpSession session) {
		String approveAdmin = (String) session.getAttribute("userId"); // 세션에서 아이디
		String userTypeCd = (String) session.getAttribute("userTypeCd");
		//if (approveAdmin != null && !approveAdmin.equals("")) {
			model.addAttribute("approveAdmin", approveAdmin);
			List<UserMngVo> userList = userMngService.userList(searchContent, currentPage);
			List<UserMngVo> newList = userMngService.newList();
			int newTotal = userMngService.getNewCount();
			int totalPage = 0;
			int totalCount = userMngService.getUserCount(searchContent);
			double totalCountDouble = (double) totalCount;
			int sharePage = 0;
			if (userList != null && !userList.isEmpty()) {
				totalPage = (int) Math.ceil(totalCountDouble / 10.0);
			//} else {
			//}

			if (currentPage == 1) {
				sharePage = 0;
			} else {
				sharePage = (currentPage - 1) / 10;
			}
			model.addAttribute("newTotal", newTotal);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("sharePage", sharePage);

			model.addAttribute("total", totalCount);
			model.addAttribute("newList", newList);
			model.addAttribute("list", userList);
			return "userMng/userMngList";
		} else {
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "loginpage";
		}
	}

	@PostMapping("/updateUserApproval")
	public ResponseEntity<String> updateUserApproval(UserMngVo user, @RequestParam Map<String, Object> param,  HttpSession session) {
		String userId = (String) param.get("userId");
		String approveYn = (String) param.get("approveYn");
		String approveAdmin = (String) session.getAttribute("userId");
		user.setApproveAdmin(approveAdmin);
		user.setUserId(userId);
		user.setApproveYn(approveYn);
		// 사용자 승인 업데이트를 수행하는 서비스 메서드 호출
		userMngService.updateUserApproval(user);

		// 성공적인 경우 200 OK 응답 반환
		return ResponseEntity.ok("ok"); // 또는 JSON 형태의 응답
	}

	@RequestMapping(value = "/user/info/{userId}", method = RequestMethod.GET)
	public String findByUserInfo(@PathVariable String userId, Model model) {
		UserMngVo user = userMngService.findByUserId(userId);
		UserMngVo userDtl = userMngService.findByUserInfo(userId);
		model.addAttribute("user", user);
		model.addAttribute("userDtl", userDtl);
		return "userMng/userMngDtl";

	}
	
	@RequestMapping(value = "/user/mod/{userId}", method = RequestMethod.GET)
	public String userMod(@PathVariable String userId, Model model) {
		UserMngVo user = userMngService.findByUserId(userId);
		UserMngVo userDtl = userMngService.findByUserInfo(userId);
		model.addAttribute("user", user);
		model.addAttribute("userDtl", userDtl);
		return "userMng/userMngMod";

	}
	
	@PostMapping(value="/user/mod")
	public ResponseEntity<String> userMod(UserMngVo user, @RequestParam Map<String, Object> param,  HttpSession session) {
		String userId = (String) param.get("userId");
		String approveYn = (String) param.get("approveYn");
		String useYn = (String) param.get("useYn");
		String approveAdmin = (String) session.getAttribute("userId");
		user.setApproveAdmin(approveAdmin);
		user.setUserId(userId);
		user.setUseYn(useYn);
		user.setApproveYn(approveYn);
		// 사용자 승인 업데이트를 수행하는 서비스 메서드 호출
		userMngService.userMod(user);

		// 성공적인 경우 200 OK 응답 반환
		return ResponseEntity.ok("ok"); // 또는 JSON 형태의 응답
	}
}
