package kr.co.kccbrew.comm.security.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.security.model.UserVo;

public interface IUserSearchService {
	String searchId(@Param("userNm")String userNm, @Param("userTelNo")String userTelNo);
	UserVo searchPwd(UserVo vo);
	void updatePwd(UserVo vo);
}
