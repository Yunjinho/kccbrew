package kr.co.kccbrew.comm.security.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.IUserSearchService;
import kr.co.kccbrew.comm.util.MailUtil;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserSearchController {
	@Autowired
	private IUserSearchService searchService;

	@RequestMapping(value = "/user/userSearch", method = RequestMethod.GET)
	public String userSearch() {


		return "security/searchUser";
	}
	
	// 아이디 찾기
	@ResponseBody
	@RequestMapping(value = "/user/userSearch", method = RequestMethod.POST)
	public String userIdSearch(@RequestParam Map<String, Object> user) {
		String userNm = (String) user.get("userNm");
		String userTelNo = (String) user.get("userTelNo");
		System.out.println(userNm);
		System.out.println(userTelNo);
		String result = searchService.searchId(userNm, userTelNo);
		System.out.println(result);
		return result;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/user/searchPassword", method = RequestMethod.GET)
	@ResponseBody
	public String findPw(UserVo vo) throws Exception {
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		String result=null;
		
		//회원정보 불러오기
		UserVo vo1 = searchService.searchPwd(vo);
		System.out.println(vo1);
		
		//가입된 이메일이 존재한다면 이메일 전송
		if(vo1!=null) {
			
			//임시 비밀번호 생성(UUID이용)
			String tempPw=UUID.randomUUID().toString().replace("-", "");//-를 제거
			tempPw = tempPw.substring(0,10);//tempPw를 앞에서부터 10자리 잘라줌
			
			vo1.setUserPwd(tempPw);//임시 비밀번호 담기
	
			MailUtil mail=new MailUtil(); //메일 전송하기
			mail.sendEmail(vo1);
			
			searchService.updatePwd(vo1);
			
			String securePw = encoder.encode(vo1.getUserPwd());//회원 비밀번호를 암호화하면 vo객체에 다시 저장
			vo1.setUserPwd(securePw);
				
			result="true";
			

		}else {
			result="false";
		}
		return result;
	}
}
