package kr.co.kccbrew.comm.security.dao;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.security.model.UserVo;

public interface IUserSearchRepository {
	String searchId(@Param("userNm")String userNm, @Param("userTelNo")String userTelNo);
//	int searchPassword(String userId, String userEmail, String key); 
	UserVo searchPwd(UserVo vo);
	void updatePwd(UserVo vo);


}
