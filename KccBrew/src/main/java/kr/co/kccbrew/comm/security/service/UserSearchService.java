
package kr.co.kccbrew.comm.security.service;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.security.dao.IUserSearchRepository;
import kr.co.kccbrew.comm.security.model.UserVo;

@Service
public class UserSearchService implements IUserSearchService {
	
	@Autowired
	private IUserSearchRepository userSearchRepository;
	private SqlSessionTemplate sqlSession;
	

	@Override
	public String searchId(String userNm, String userTelNo) {
		System.out.println("--------------------------------------------");
		// userSearchRepository = sqlSession.getMapper(IUserSearchRepository.class);

		String result = "";

		try {
			result = userSearchRepository.searchId(userNm, userTelNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	@Override
	public UserVo searchPwd(UserVo vo) {
		return userSearchRepository.searchPwd(vo);
	}
	@Override
	public void updatePwd(UserVo vol) {
		userSearchRepository.updatePwd(vol);
	}
	//
//			public void mailSendWithPassword(String userId, String userEmail, HttpServletRequest request) {
//				// 비밀번호는 6자리로 보내고 데이터베이스 비밀번호를 바꿔준다
//				String key = getKey(false, 6);
//				userSearchRepository = sqlSession.getMapper(IUserSearchRepository.class);
//	
//				// 회원 이름 꺼내는 코드
//				UserVo vo = userSearchRepository.userInfo(userId);
//				String name = vo.getUserNm();
//	
//				MimeMessage mail = mailSender.createMimeMessage();
//				String htmlStr = "<h2>안녕하세요 '" + name + "' 님</h2><br><br>" + "<p>비밀번호 찾기를 신청해주셔서 임시 비밀번호를 발급해드렸습니다.</p>"
//						+ "<p>임시로 발급 드린 비밀번호는 <h2 style='color : blue'>'" + key
//						+ "'</h2>이며 로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다.</p><br>"
//						+ "<h3><a href='http://54.180.123.73/MS/'>MS :p 홈페이지 접속 ^0^</a></h3><br><br>"
//						+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
//				try {
//					mail.setSubject("[MS :p] 임시 비밀번호가 발급되었습니다", "utf-8");
//					mail.setText(htmlStr, "utf-8", "html");
//					mail.addRecipient(RecipientType.TO, new InternetAddress(user_email));
//					mailSender.send(mail);
//				} catch (MessagingException e) {
//					e.printStackTrace();
//				}
//				// 비밀번호 암호화해주는 메서드
//				key = UserSha256.encrypt(key);
//				// 데이터 베이스 값은 암호한 값으로 저장시킨다.
//				userSearchRepository.searchPassword(user_id, user_email, key);
//	
//			}

}
