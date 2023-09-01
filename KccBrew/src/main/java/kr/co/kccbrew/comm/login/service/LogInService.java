package kr.co.kccbrew.comm.login.service;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.login.dao.ILogInRepository;
import kr.co.kccbrew.comm.login.model.LogInVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogInService implements ILogInService{
	
	/**
	 * logInRepository 변수 선언
	 */
	private final ILogInRepository logInRepository;
	
	/**
	 * 아이디 비밀번호 체크 
	 */
	@Override
	public LogInVo logIn(LogInVo vo) {
		LogInVo user=new LogInVo();
		String userId=vo.getUserId();
		String salt=logInRepository.selectUserSalt(userId);
		//아이디가 존재하지 않는 경우
		if(salt==null) return user;
		vo.setUserPwd(getEncrypt(vo.getUserPwd(), salt));
		user=logInRepository.logIn(vo);
		return user;
	}
	
	/**
	 * 
	 * @param pwd : 입력된 사용자 비밀번호
	 * @param salt : 난수
	 * @return : pwd+salt 값을 더해 해싱된 값
	 */
	private String getEncrypt(String pwd,String salt) {
		String hashingPwd="";
		try {
			//SHA-256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//pwd+salt 문자열에 알고리즘 적용
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt=md.digest();
			
			//문자열로 변경
			StringBuffer sb= new StringBuffer();
			for(byte b:pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			hashingPwd=sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return hashingPwd;
	}


}
