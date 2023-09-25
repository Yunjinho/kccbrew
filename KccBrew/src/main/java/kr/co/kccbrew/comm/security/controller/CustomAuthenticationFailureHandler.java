package kr.co.kccbrew.comm.security.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kccbrew.comm.security.service.CustomUserDetailsService;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String errorMessage;

		if(exception instanceof BadCredentialsException) {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";;
		} else if (exception instanceof UsernameNotFoundException) {
			errorMessage = "존재하지 않는 계정입니다. 회원가입 후 로그인해주세요.";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMessage = "인증 요청이 거부되었습니다. 관리자에게 문의하세요.";
		} else {
			errorMessage = "아이디 또는 비밀번호가 맞지 않습니다. 다시 확인해주세요.";;
		}

		errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); /* 한글 인코딩 깨진 문제 방지 */
		response.sendRedirect("/login?error=true&exception="+errorMessage);



	}
}
