package kr.co.kccbrew.comm.security.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.IUserSearchService;
import kr.co.kccbrew.comm.security.service.IUserService;
import kr.co.kccbrew.comm.util.MailUtil;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserSearchController {
	@Autowired
	private IUserSearchService searchService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/userSearch", method = RequestMethod.GET)
	public String userSearch() {

		return "security/searchUser";
	}

	// 아이디 찾기
	@ResponseBody
	@RequestMapping(value = "/userSearch", method = RequestMethod.POST)
	public String userIdSearch(@RequestParam Map<String, Object> user) {
		String userNm = (String) user.get("userNm");
		String userTelNo = (String) user.get("userTelNo");
		String result = searchService.searchId(userNm, userTelNo);
		return result;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/searchPassword", method = RequestMethod.POST)
	public String findPw(@ModelAttribute UserVo vo, Model model) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = "false";
		try {
			// 회원정보 불러오기
			UserVo vo1 = searchService.searchPwd(vo);
			// 가입된 이메일이 존재한다면 이메일 전송
			if (vo1 != null) {
				result = "true";
				// 임시 비밀번호 생성(UUID이용)
				String tempPw = UUID.randomUUID().toString().replace("-", "");// -를 제거
				tempPw = tempPw.substring(0, 10);// tempPw를 앞에서부터 10자리 잘라줌

				vo1.setUserPwd(tempPw);// 임시 비밀번호 담기

				MailUtil mail = new MailUtil(); // 메일 전송하기
				mail.sendEmail(vo1);
				vo.setUserPwd(passwordEncoder.encode(tempPw));
				searchService.updatePwd(vo);

				String securePw = encoder.encode(vo1.getUserPwd());// 회원 비밀번호를 암호화하면 vo객체에 다시 저장
				vo1.setUserPwd(securePw);

			} else {
				result = "false";
			}
			model.addAttribute("result", result);
			System.out.println(result);
		} catch (NullPointerException e) {
			result = "false";
		}
		return "security/searchUser";
	}

	/******************* 비밀번호 변경 *********************/

	@RequestMapping(value = "/mypage/chgpwd", method = RequestMethod.GET)
	public String chgPassword(Model model, Principal principal) {
		String userId = principal.getName();
		UserVo user = userService.getUserById(userId);
		String password = user.getUserPwd();
		System.out.println(password);
		model.addAttribute("password", password);
		return "myPage/chgPwd";
	}

	@RequestMapping(value = "/mypage/chgpwd", method = RequestMethod.POST)
	public String confirmChange(@ModelAttribute UserVo Vo, HttpServletRequest request, Model model,
			@ModelAttribute MainPageVo mainPageVo, Principal principal) {

		String userId = principal.getName();
		UserVo user = userService.getUserById(userId);
		String loginPwd = user.getUserPwd();
		boolean j = passwordEncoder.matches(Vo.getCurrentPassword(), loginPwd);
		if (j) {
			String newPassword = passwordEncoder.encode(Vo.getCheckNewPassword());
			user.setUserPwd(newPassword);
			searchService.updatePwd(user);
			return "redirect:/mypage";
		}
		else {
		return "myPage/chgPwd";
		}
	}

	@RequestMapping(value = "/chgpwd")
	@ResponseBody
	public String pwdTest(@RequestBody UserVo user, Principal principal) {
		String currentPassword = user.getCurrentPassword();
		System.out.println(currentPassword);
		String userId = principal.getName();
		UserVo Vo = userService.getUserById(userId);
		String loginPwd = Vo.getUserPwd();
		boolean j = passwordEncoder.matches(currentPassword, loginPwd);
		String result="false";
		if (j) {
			
			result="true";
		} 
		System.out.println(result);
		return result;
	}
}
