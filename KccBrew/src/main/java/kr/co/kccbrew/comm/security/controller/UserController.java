package kr.co.kccbrew.comm.security.controller;

public class UserController {
	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegistrationForm registrationForm) {
		// 사용자 등록 로직
		String encodedPassword = passwordEncoder.encode(registrationForm.getPassword());
		// 암호화된 비밀번호를 사용하여 사용자 등록
		userService.registerUser(registrationForm.getUsername(), encodedPassword, /* 기타 필드 */);
		return "redirect:/login"; // 회원가입 후 로그인 페이지로 이동
	}

}
