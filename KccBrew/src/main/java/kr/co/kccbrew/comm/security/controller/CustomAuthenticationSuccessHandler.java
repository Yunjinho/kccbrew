package kr.co.kccbrew.comm.security.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.stereotype.Component;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.main.service.MainService;
import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
import kr.co.kccbrew.schdlMng.service.SchdlMngService;
import kr.co.kccbrew.strMng.model.StrMngVo;
import kr.co.kccbrew.userMng.model.UserMngVo;
import kr.co.kccbrew.userMng.service.UserMngService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	@Autowired
	private UserService userService;
	@Autowired
	private UserMngService userMngService;
	@Autowired
	private MainService mainService;
	@Autowired
	private SchdlMngService schdlMngService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("CustomAuthenticationSuccessHandler.onAuthenticationSuccess");

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String userId = userDetails.getUsername();
		UserVo user = userService.getUserById(userId);

		// 파일정보 UserVo에 저장
		List<UserMngVo> userMngVo = userMngService.findByUserInfo2(userId);
		user.setFileServerNm(userMngVo.get(0).getImgNm());
		user.setStorageLocation(userMngVo.get(0).getImgUrl());

		// 회원정보 세션에 저장
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		SaveTasksInSessions(session, user);

		// 승인되지 않은 회원의 경우 다시 안내메세지
		GrantedAuthority role = null;
		for ( GrantedAuthority authority : userDetails.getAuthorities()) {
			role = authority;
		}

		if(role.equals(new SimpleGrantedAuthority("ROLE_NOTAPPROVED"))) {
			response.sendRedirect("/not-approved");
		} else {
			response.sendRedirect("/");
		} 
	}
	
	

	/*사용자 프로필에 사용자 할일 표시*/
	public void SaveTasksInSessions(HttpSession session, UserVo user) {
		String userId = user.getUserId();
		
		// 관리자 프로필 정보 세션에 저장
		if(user.getUserTypeCd().equals("01")) {
			List<AsMngVo> asList = userService.getAsAccepting(null);
			int asListCount = asList.size();
			int unapprovedMemberList = mainService.showUnapprovedMemberList().size();
			session.setAttribute("asListCount", asListCount);
			session.setAttribute("unapprovedMemberList", unapprovedMemberList);
		}
		
		
		
		// 점주 프로필 정보 세션에 저장
		else if(user.getUserTypeCd().equals("02")) {
			List<StrMngVo> storeList = userService.getStoreById(userId);
			StrMngVo vo=storeList.get(0);
			session.setAttribute("store", vo);

			List<AsMngVo> asAssignListById = userService.getAsAccepting(userId);          //a/s 배정 리스트
			List<AsMngVo> asListById 	= userService.getAsSubmissionCompleted(userId);   //a/s 접수 리스트
			List<AsMngVo> resultListById = userService.getAsProcessingCompleted(userId);	// a/s 결과 리스트
			int asAssignListByIdCount = asAssignListById.size();
			int asListByIdCount = asListById.size();
			int resultListByIdCount = resultListById.size();
			session.setAttribute("asAssignListByIdCount", asAssignListByIdCount);
			session.setAttribute("asListByIdCount", asListByIdCount);
			session.setAttribute("resultListByIdCount", resultListByIdCount);

			int usedHolidays = schdlMngService.getUsedHoliday(user);
			session.setAttribute("usedHolidays", usedHolidays);
		}
		
		
		
		// 수리기사 프로필 정보 세션에 저장
		else if(user.getUserTypeCd().equals("03")) {
			List<AsMngVo> asAssignList = userService.getAsSubmissionCompleted(userId);
			int asAssignListCount = asAssignList.size();
			session.setAttribute("asAssignListCount", asAssignListCount);

			int usedHolidays = schdlMngService.getUsedHoliday(user);
			session.setAttribute("usedHolidays", usedHolidays);
		}
	}
}
