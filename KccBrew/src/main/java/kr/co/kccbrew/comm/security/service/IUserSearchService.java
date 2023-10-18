package kr.co.kccbrew.comm.security.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.security.model.UserVo;

public interface IUserSearchService {
	/**
	 * @return 사용자 채팅작성
	 * @Param userNm: 사용자이름, userTelNo: 사용자전화번호
	 */
	List<UserVo> searchId(@Param("userNm")String userNm, @Param("userTelNo")String userTelNo);
	
	/**
	 * @return 비밀번호찾기
	 */
	UserVo searchPwd(UserVo vo);
	
	/**
	 * @return 비밀번호 변경
	 */
	void updatePwd(UserVo vo);
}
