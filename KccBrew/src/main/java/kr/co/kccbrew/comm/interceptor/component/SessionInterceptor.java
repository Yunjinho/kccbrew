package kr.co.kccbrew.comm.interceptor.component;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
import kr.co.kccbrew.userMng.model.UserMngVo;
import kr.co.kccbrew.userMng.service.UserMngService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : SessionInterceptor
 * @Decription : 사용자정보가 담긴 세션을 지속적으로 업데이트
 * 
 * @   수정일           			    수정자            		 																		수정내용
 * ============      ==============     ====================================================================
 * 2023-10-13							이세은					시큐리티의 사용자ID정보를 통해 DB의 정보를 지속적으로 조회하여 세션정보 업데이트
 * @author LEESEEUN
 * @version 1.0
 */

@Slf4j
@Component
public class SessionInterceptor extends Interceptor {
	@Autowired
	private UserService userService;
	@Autowired
	private UserMngService userMngService;

	/**
	 * 클라이언트 요청을 컨트롤러에 전달 전
	 * 세션 값, 계정 권한 체크
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		String userId = null;
		if (authentication != null) {
			userId = authentication.getName();
			log.info("userId: " + userId);
		}

		GrantedAuthority userType = null;
		for ( GrantedAuthority authority : authentication.getAuthorities()) {
			userType = authority;
			log.info("userType: " + userType);
		}

		UserVo user = userService.getUserById(userId);
		List<UserMngVo> userMngVo = userMngService.findByUserInfo2(userId);

		if (userMngVo != null && !userMngVo.isEmpty()) {
			if(userMngVo.get(0).getImgNm() != null && userMngVo.get(0).getImgUrl() != null) {
				user.setFileServerNm(userMngVo.get(0).getImgNm());
				user.setStorageLocation(userMngVo.get(0).getImgUrl());
			}
		}

		HttpSession session = request.getSession();
		session.setAttribute("user", user);


		/*		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();
			Object attributeValue = session.getAttribute(attributeName);
			System.out.println(attributeName + ": " + attributeValue);
		}*/

		return true;
	}
	/**
	 * API 접근한 IP 세션에 저장
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
}