package kr.co.kccbrew.comm.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.login.model.LogInVo;
import kr.co.kccbrew.comm.login.service.ILogInService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : LogInContorller
 * @Decription : 로그인 기능을 관리하기 위한 controller
 * 
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-08-28			윤진호				최초생성
 * @author YUNJINHO
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class LogInController {
	/**
	 * loginService변수 생성
	 */
	private final ILogInService loginService;
	
	/** 로그인 페이지로 이동 */
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public String login() {
		return "/comm/login/login";
	}
	
	/** 로그인 처리 */
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login(String id, String pwd,Model model,HttpServletRequest httpServletRequest) {
		LogInVo vo = new LogInVo();
		vo.setUserId(id);
		vo.setUserPwd(pwd);
		LogInVo user=loginService.logIn(vo);
		if(user.getUserId()!=null) {
			if(user.getApproveYn().equals("Y")) {
				// 세션을 생성하기 전에 기존의 세션 파기
		        httpServletRequest.getSession().invalidate();
		        HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성
		        // 세션에 userId를 넣어줌
				session.setAttribute("userTypeCd", user.getUserTypeCd());
				session.setAttribute("userId", user.getUserId());
				session.setMaxInactiveInterval(1800); // Session이 30분동안 유지
				return "1";
			}else {
				return "2";
			}
		}else {
			return "3";
		}
	}
	
}
