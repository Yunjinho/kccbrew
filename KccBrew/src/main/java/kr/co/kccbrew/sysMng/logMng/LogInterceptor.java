package kr.co.kccbrew.sysMng.logMng;


import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import lombok.extern.slf4j.Slf4j;


/**
 * @ClassNmae : LogInterceptor
 * @Decription : 로그 기록 수집 및 저장하는 인터셉터 구현
 * 
 * @   수정일               수정자             				수정내용
 * ============      ==============     ==============
 * 2023-08-22				  이세은							 최초생성
 * @author LEESEEUN
 * @version 1.0
 */

@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor { 

	@Autowired
	private LogMngVo logVO;
	@Autowired
	private LogMngService logService;

	public static final String LOG_ID = "logId";

	/* ip반환 메소드 */
	public String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-RealIP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/*컨트롤러 호출전*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true; 
	}



	/*컨트롤러 호출후*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		if (modelAndView.getViewName() != null && !modelAndView.getViewName().isEmpty()) {
			String view =
					modelAndView.getViewName(); HttpSession session = request.getSession();
					session.setAttribute("view", view); }

	}



	/*컨트롤러 요청완료 이후*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		HttpSession session = request.getSession();

		LocalDateTime localDateTime = LocalDateTime.now();
		String requestURI = request.getRequestURI(); 
		String ip = getClientIp(request);
		String userId = null;
		String userType = null;
//		String status = String.valueOf(response.getStatus());
		String status = "";
		String view = null;
		if ( session.getAttribute("view") != null) {
			view = (String) session.getAttribute("view");
		}

		// 세션에서 로그인 회원 정보 반환
		userId = (String) session.getAttribute("userId");
		userType = (String) session.getAttribute("userTypeCd");
		if(userType=="01") {
			userType = "admin";
		}else if(userType=="02") {
			userType = "store-owner";
		}else {
			userType = "mechanic";
		}

//		log.info("REQUEST  date=[{}], uri=[{}], view=[{}], handler=[{}], userId=[{}], userType=[{}], ip=[{}], status=[{}]", 
//				localDateTime, requestURI, view, handler, userId, userType, ip, status);
		log.info("REQUEST  date=[{}], uri=[{}], view=[{}], handler=[{}], userId=[{}], userType=[{}], ip=[{}], status=[{}]");
		if (ex != null) {
			log.error("afterCompletion error!!", ex);
		} 

		// log객체에 값 주입 
		logVO.setDate(localDateTime);
		logVO.setIp(ip);
		logVO.setUri(requestURI);
		logVO.setUserId(userId);
		logVO.setUserType(userType);
		logVO.setStatusCode(status);
		if (view != null) {
			logVO.setView(view);
		}

		logService.insertlogVO(logVO);
	}


}
