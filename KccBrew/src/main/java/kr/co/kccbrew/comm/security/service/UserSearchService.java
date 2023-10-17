
package kr.co.kccbrew.comm.security.service;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.security.dao.IUserSearchRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSearchService implements IUserSearchService {
	
	@Autowired
	private IUserSearchRepository userSearchRepository;
	private SqlSessionTemplate sqlSession;
	
	/**
	 * @return 아이디찾기
	 */
	@Override
	public List<UserVo> searchId(String userNm, String userTelNo) {
		List<UserVo> result = null;
		try {
			result = userSearchRepository.searchId(userNm, userTelNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @return 비밀번호 찾기
	 */
	@Override
	public UserVo searchPwd(UserVo vo) {
		return userSearchRepository.searchPwd(vo);
	}
	
	/**
	 * @return 비밀번호 변경
	 */
	@Override
	public void updatePwd(UserVo vol) {
		userSearchRepository.updatePwd(vol);
	}
	

}
