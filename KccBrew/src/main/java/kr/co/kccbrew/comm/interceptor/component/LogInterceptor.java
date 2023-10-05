package kr.co.kccbrew.comm.interceptor.component;

import java.sql.Date; 
import java.time.LocalDateTime; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		log.info("컨트롤러 호출후 로깅테스트");

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

		LocalDateTime currentDateTime = LocalDateTime.now();
		Date date = Date.valueOf(currentDateTime.toLocalDate());
		String requestURI = request.getRequestURI(); 
		/*String logId = (String)request.getAttribute(LOG_ID); */

		//세션에서 로그인 회원 정보 반환
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("user");
		String userId = user.getUserId();
		String userType = objectUtilService.getUserTypeName(user.getUserTypeCd());
		String ip = getClientIp(request); // ip


		String status = String.valueOf(response.getStatus());
		log.info("status={}", status);

		String view = (String) session.getAttribute("view");
		LogMngVo logMngVo = new LogMngVo();
		logMngVo.setView(view);

		log.info("REQUEST  date=[{}], uri=[{}], view=[{}], handler=[{}], userId=[{}], userType=[{}], ip=[{}], status=[{}]");
		if (ex != null) {
			log.error("afterCompletion error!!", ex);
		} 

		/* log객체에 값 주입 */
		logMngVo.setDate(date);
		logMngVo.setIp(ip);
		logMngVo.setUserId(userId);
		logMngVo.setUri(requestURI);
		logMngVo.setStatusCode(status);
		logMngVo.setUserType(userType);
		logMngVo.setView(view);


		logService.insertlogVO(logMngVo);
	}


}