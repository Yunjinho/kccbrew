package kr.co.kccbrew.comm.interceptor.component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.util.ObjectUtilService;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : LogInterceptor
 * @Decription : 이용자 활동 추적을 위한 로그 생성을 위한 interceptor
 * 
 * @   수정일                    수정자                        			수정내용
 * ============      ==============     ============================
 * 2023-09-01			           이세은			        						최초생성
 * 2023-10-05			           이세은			        view값 구할 때, modelAndView가 null일때를 고려한 조건문 추가
 * 
 * @author LEESEEUN
 * @version 1.0
 */

@Slf4j
@Component
public class LogInterceptor extends Interceptor { 
	@Autowired
	private LogMngService logService;
	@Autowired
	private ObjectUtilService objectUtilService;

	public static final String LOG_ID = "logId";

	/*컨트롤러 호출전*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}



	/*컨트롤러 호출후*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse 
			response, Object handler, ModelAndView modelAndView) throws Exception {

		String view = null;
		if( modelAndView != null && modelAndView.getViewName() != null && !modelAndView.getViewName().equals("")) {
			view = modelAndView.getViewName();

			HttpSession session = request.getSession();
			session.setAttribute("view", view);
		}
	}

	/*컨트롤러 요청완료 이후*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("컨트롤러 호출완료 이후 로깅테스트");

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		log.info("timestamp: " + timestamp) ;
		String requestURI = request.getRequestURI(); 
		/*String logId = (String)request.getAttribute(LOG_ID); */

		//세션에서 로그인 회원 정보 반환
		HttpSession session = request.getSession();
		String userId = "non-member";
		String userType = "non-member";

		if (session.getAttribute("user") != null) {
			UserVo user = (UserVo) session.getAttribute("user");
			userId = user.getUserId();
			userType = objectUtilService.getUserTypeName(user.getUserTypeCd());
		}
		String ip = getClientIp(request); // ip


		String status = String.valueOf(response.getStatus());
		switch(status) {
		case "200":
			status = "200 OK";
			break;
		case "201":
			status = "201 Created";
			break;
		case "400": 
			status = "400 Bad Request";
			break;
		case "403":
			status = "403 Forbidden";
			break;
		case "404":
			status = "404 Not Found";
			break;
		case "500":
			status = "500 Internal Server Error";
			break;
		}

		String view = (String) session.getAttribute("view");
		LogMngVo logMngVo = new LogMngVo();
		logMngVo.setView(view);

		if (ex != null) {
			log.error("afterCompletion error!!", ex);
		} 

		/* log객체에 값 주입 */
		logMngVo.setDate(timestamp);
		logMngVo.setIp(ip);
		logMngVo.setUserId(userId);
		logMngVo.setUri(requestURI);
		logMngVo.setStatusCode(status);
		logMngVo.setUserType(userType);
		logMngVo.setView(view);


		logService.insertlogVO(logMngVo);
	}

	public String getUserId(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		return userId;
	}

	public String getUserType(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		GrantedAuthority role = null;
		for ( GrantedAuthority authority : userDetails.getAuthorities()) {
			role = authority;
		}

		if(role.equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "admin";
		} else if (role.equals(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			return "manager";
		} else {
			return "mechanic";
		} 
	}



}