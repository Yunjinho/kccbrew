package kr.co.kccbrew.userMng.service;

import java.util.List;

import kr.co.kccbrew.userMng.model.UserMngVo;

public interface IUserMngService {
	List<UserMngVo> userList(UserMngVo user, int currentPage);
	int getUserCount(UserMngVo user);
	List<UserMngVo> newList();
	void updateUserApproval(UserMngVo user);
	void userMod(UserMngVo user);
	 int getNewCount();
	 UserMngVo findByUserId(String userId);
	 UserMngVo findByUserInfo(String userId);
}
