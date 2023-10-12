package kr.co.kccbrew.userMng.service;

import java.util.List;

import kr.co.kccbrew.userMng.model.UserMngVo;

public interface IUserMngService {
	List<UserMngVo> userList(UserMngVo userMngVo, int page);
	int getUserCount(UserMngVo userMngVo);
	List<UserMngVo> newList();
	void updateUserApproval(UserMngVo userMngVo);
	void userMod(UserMngVo userMngVo);
	 int getNewCount();
	 UserMngVo findByUserId(String userId);
	 List<UserMngVo> findByUserInfo(String userId);
	 List<UserMngVo> findByUserInfo2(String userId);
}
