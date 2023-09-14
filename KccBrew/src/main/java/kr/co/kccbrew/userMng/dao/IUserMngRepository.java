package kr.co.kccbrew.userMng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.userMng.model.UserMngVo;

public interface IUserMngRepository {
	List<UserMngVo> userList(Map<String, Object> map);
	int getUserCount(Map<String, Object> map);
	List<UserMngVo> newList();
	 UserMngVo findByUserId(String userId);
	 UserMngVo findByUserInfo(@Param("userId")String userId, @Param("userTypeCd") UserMngVo userTypeCd);
	 void save(UserMngVo user);
	 void userMod(UserMngVo user);
	 int getNewCount();

}
