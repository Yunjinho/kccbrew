package kr.co.kccbrew.comm.interceptor.component;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kccbrew.sysMng.logMng.controller.LogMngController.UserVO;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class LogInterceptor extends Interceptor { 

	@Autowired
	private LogMngVo logVO;
	@Autowired
	private LogMngService logService;

	public static final String LOG_ID = "logId";

	/*컨트롤러 호출전*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("컨트롤러 호출전 로깅테스트");

		LocalDateTime date = LocalDateTime.now();
		String requestURI = request.getRequestURI(); // path
		String uuid = UUID.randomUUID().toString(); // uuid
		request.setAttribute(LOG_ID, uuid); 

		String userId = null; // userId
		String userType = null; // userType
		String ip = null; // ip

		//@RequestMapping: HandlerMethod
		//정적 리소스: ResourceHttpRequestHandler 
		if (handler instanceof HandlerMethod) {

			HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
		}


		//세션에서 로그인 회원 정보 반환
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		if (user instanceof UserVO) {
			UserVO userVO = (UserVO) user;
			userId = userVO.getUserId();
			userType = userVO.getUserType();
		}

		//log.info("REQUEST  [{}][{}][{}][{}][{}][{}]", uuid, requestURI, handler, userId, userType, ip);
		log.info("REQUEST  [{}][{}][{}][{}][{}][{}]");
		return true; //false 진행X 
	}

	/*컨트롤러 호출후*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse 
			response, Object handler, ModelAndView modelAndView) throws Exception {
		log.info("컨트롤러 호출후 로깅테스트");
//		log.info("postHandle [{}]", modelAndView);
		String view = modelAndView.getViewName();
		
		HttpSession session = request.getSession();
		session.setAttribute("view", view);
		
		log.info("view: {}", view);
	}

	/*컨트롤러 요청완료 이후*/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info("컨트롤러 호출완료 이후 로깅테스트");

		LocalDateTime localDateTime = LocalDateTime.now();
		String requestURI = request.getRequestURI(); 
		String logId = (String)request.getAttribute(LOG_ID); 

		//세션에서 로그인 회원 정보 반환
		HttpSession session = request.getSession();
		Object user2 = session.getAttribute("user");
		String userId = null; // userId
		String userType = null; // userType
		String ip = getClientIp(request); // ip
		if (user2 instanceof UserVO) {
			UserVO userVO = (UserVO) user2;
			userId = userVO.getUserId();
			userType = userVO.getUserType();
		}

//		String status = String.valueOf(response.getStatus());
		String status = null;
		log.info("status={}", status);
		
		String view = (String) session.getAttribute("view");
		logVO.setView(view);

//		log.info("REQUEST  date=[{}], uri=[{}], view=[{}], handler=[{}], userId=[{}], userType=[{}], ip=[{}], status=[{}]", 
//											   localDateTime, requestURI, view, handler, userId, userType, ip, status);
		log.info("REQUEST  date=[{}], uri=[{}], view=[{}], handler=[{}], userId=[{}], userType=[{}], ip=[{}], status=[{}]");
		if (ex != null) {
			log.error("afterCompletion error!!", ex);
		} 

		/* log객체에 값 주입 */
		logVO.setDate(localDateTime);
		logVO.setIp(ip);
		logVO.setStatusCode(null);
		logVO.setUri(requestURI);
		logVO.setUserId(userId);
		logVO.setUserType(userType);
		logVO.setStatusCode(status);
		
		logService.insertlogVO(logVO);
	}
	

}