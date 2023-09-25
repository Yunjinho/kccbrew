package kr.co.kccbrew.comm.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
import kr.co.kccbrew.strMng.model.StrMngVo;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("CustomAuthenticationSuccessHandler.onAuthenticationSuccess");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		UserVo user = userService.getUserById(userId);

		// 회원정보 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		// 점주일 경우 점포정보 세션에 저장
		if(user.getUserTypeCd().equals("02")) {
			StrMngVo store = userService.getStoreById(userId);
			session.setAttribute("store", store);
		}

		GrantedAuthority role = null;
		for ( GrantedAuthority authority : userDetails.getAuthorities()) {
			role = authority;
		}
		
		/*승인되지 않은 회원의 경우 다시 안내메세지*/
		if(role.equals(new SimpleGrantedAuthority("ROLE_NOTAPPROVED"))) {
			response.sendRedirect("/not-approved");
		} else {
			response.sendRedirect("/");
		} 
	}
}
