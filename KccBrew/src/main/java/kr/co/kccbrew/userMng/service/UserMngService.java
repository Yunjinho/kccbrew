package kr.co.kccbrew.userMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.userMng.dao.IUserMngRepository;
import kr.co.kccbrew.userMng.model.UserMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserMngService implements IUserMngService {

	private final IUserMngRepository userMngRepository;
	
	@Override
	public List<UserMngVo> userList(UserMngVo user, int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserMngVo", user);
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);
		return userMngRepository.userList(map);
	}

	@Override
	public int getUserCount(UserMngVo user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UserMngVo", user);
		return userMngRepository.getUserCount(map);
	}
	
	@Override
	public List<UserMngVo> newList(){
		return userMngRepository.newList();
	}
	
	  @Override
	    public void updateUserApproval(UserMngVo user) {
		  System.out.println(user);
	            userMngRepository.save(user); // 변경사항을 데이터베이스에 저장
	      
	    }
	  @Override
	    public void userMod(UserMngVo user) {
		System.out.println(user);
	    userMngRepository.userMod(user); // 변경사항을 데이터베이스에 저장
	      
	    }
	  @Override
	  public int getNewCount() {
		  return userMngRepository.getNewCount();
	  }
	  
	  @Override
	  public UserMngVo findByUserId(String userId) {
	  return userMngRepository.findByUserId(userId); 
}
	  @Override
	  public UserMngVo findByUserInfo(String userId) {
		 UserMngVo userTypeCd = userMngRepository.findByUserId(userId);
	  return userMngRepository.findByUserInfo(userId, userTypeCd); 
}}