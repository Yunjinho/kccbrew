package kr.co.kccbrew.comm.security.controller;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegistrationForm registrationForm) {
		// 사용자 등록 로직
		String encodedPassword = PasswordEncoder.encode(registrationForm.getPassword());
		// 암호화된 비밀번호를 사용하여 사용자 등록
		return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
	}

}
