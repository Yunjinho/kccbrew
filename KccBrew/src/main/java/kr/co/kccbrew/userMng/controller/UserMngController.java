package kr.co.kccbrew.userMng.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-10-13 배수연 검색조건 유지
 * @author BAESOOYEON
 * @version 1.0
 */

@Controller
@RequiredArgsConstructor
public class UserMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IUserMngService userMngService;

		@GetMapping("/user")
		public String userAll(Model model, UserMngVo userMngVo, HttpSession session, Principal principal) {
			String approveAdmin = principal.getName();
			model.addAttribute("approveAdmin", approveAdmin);
				List<UserMngVo> userList = userMngService.userList(userMngVo, 1);
				List<UserMngVo> newList = userMngService.newList();
				int newTotal = userMngService.getNewCount();
				int totalPage = 0;
				int totalCount = userMngService.getUserCount(userMngVo);
				double totalCountDouble = (double) totalCount;
				if (userList != null && !userList.isEmpty()) {
					totalPage = (int) Math.ceil(totalCountDouble / 10.0);
				}

				int startPage=((int)Math.ceil(userMngVo.getCurrentPage()/10) * 10) + 1;
				int endPage=((int)Math.ceil(userMngVo.getCurrentPage()/10) + 1) * 10;
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("currentPage", 1);
				model.addAttribute("startPage", startPage);
				model.addAttribute("endPage", endPage);

				model.addAttribute("totalCount", totalCount);
				
				model.addAttribute("newTotal", newTotal);
				model.addAttribute("newList", newList);
				model.addAttribute("list", userList);
				return "adminUserMng";
		}
		
		@GetMapping("/user/search")
		public String userSearch(Model model, UserMngVo userMngVo, HttpSession session, Principal principal) {
			String approveAdmin = principal.getName();
				model.addAttribute("approveAdmin", approveAdmin);
				List<UserMngVo> userList = userMngService.userList(userMngVo, userMngVo.getCurrentPage());
				List<UserMngVo> newList = userMngService.newList();
				int newTotal = userMngService.getNewCount();
				int totalPage = 0;
				int totalCount = userMngService.getUserCount(userMngVo);
				double totalCountDouble = (double) totalCount;
				if (userList != null && !userList.isEmpty()) {
					totalPage = (int) Math.ceil(totalCountDouble / 10.0);
				}

				int startPage=((int)Math.ceil(userMngVo.getCurrentPage()/10) * 10) + 1;
				int endPage=((int)Math.ceil(userMngVo.getCurrentPage()/10) + 1) * 10;
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("currentPage", userMngVo.getCurrentPage());
				model.addAttribute("startPage", startPage);
				model.addAttribute("endPage", endPage);
				model.addAttribute("searchContent", userMngVo);
				model.addAttribute("totalCount", totalCount);
				
				model.addAttribute("newTotal", newTotal);
				model.addAttribute("newList", newList);
				model.addAttribute("list", userList);
				return "adminUserMng";
		}

	@PostMapping("/user/approval")
	public ResponseEntity<String> updateUserApproval(Principal principal, UserMngVo userMngVo, @RequestParam Map<String, Object> param,  HttpSession session) {
		String userId = (String) param.get("userId");
		String approveYn = (String) param.get("approveYn");
		String approveAdmin = principal.getName();
		userMngVo.setApproveAdmin(approveAdmin);
		userMngVo.setUserId(userId);
		userMngVo.setApproveYn(approveYn);
		// 사용자 승인 업데이트를 수행하는 서비스 메서드 호출
		userMngService.updateUserApproval(userMngVo);

		// 성공적인 경우 200 OK 응답 반환
		return ResponseEntity.ok("ok"); // 또는 JSON 형태의 응답
	}

	@RequestMapping(value = "/user/info/{userId}", method = RequestMethod.GET)
	public String findByUserInfo(@PathVariable("userId") String userId, Model model) {
		System.out.println("컨트롤러러ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ");
		UserMngVo userMngVo = userMngService.findByUserId(userId);
		List<UserMngVo> userDtl = userMngService.findByUserInfo(userId);
		model.addAttribute("userMngVo", userMngVo);
		model.addAttribute("userDtl", userDtl);
		System.out.println("여기봐여기여기이ㅕ기여기여긱여ㅣㅇ겨ㅣㅇ겨이겨"+userDtl);
		System.out.println(userMngVo);
		return "userMng/userMngDtl";

	}
	
	@RequestMapping(value = "/user/mod/{userId}", method = RequestMethod.GET)
	public String userMod(@PathVariable("userId") String userId, Model model) {
		UserMngVo userMngVo = userMngService.findByUserId(userId);
		List<UserMngVo> userDtl = userMngService.findByUserInfo(userId);
		model.addAttribute("userMngVo", userMngVo);
		model.addAttribute("userDtl", userDtl);
		return "userMng/userMngMod";

	}
	
	@PostMapping(value="/user/mod")
	public ResponseEntity<String> userMod(Principal principal, UserMngVo userMngVo, @RequestParam Map<String, Object> param,  HttpSession session) {
		String userId = (String) param.get("userId");
		String approveYn = (String) param.get("approveYn");
		String useYn = (String) param.get("useYn");
		String approveAdmin = principal.getName();
		userMngVo.setApproveAdmin(approveAdmin);
		userMngVo.setUserId(userId);
		userMngVo.setUseYn(useYn);
		userMngVo.setApproveYn(approveYn);
		// 사용자 승인 업데이트를 수행하는 서비스 메서드 호출
		userMngService.userMod(userMngVo);

		// 성공적인 경우 200 OK 응답 반환
		return ResponseEntity.ok("ok"); // 또는 JSON 형태의 응답
	}
}
