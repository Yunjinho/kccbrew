package kr.co.kccbrew.comm.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		UserVo user = userService.getUserById(userId);

		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		System.out.println("user: " + user);

		if(user.getUserTypeCd().equals("02")) {
			StrMngVo store = userService.getStoreById(userId);
			session.setAttribute("store", store);
			System.out.println("store: " + store);
		}
		response.sendRedirect("/");
	}
}
