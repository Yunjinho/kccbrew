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
	public List<UserMngVo> userList(UserMngVo userMngVo, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("UserMngVo", userMngVo);
		map.put("start", ((page-1)*10)+1);
		map.put("end", page*10);
		return userMngRepository.userList(map);
	}

	@Override
	public int getUserCount(UserMngVo userMngVo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("UserMngVo", userMngVo);
		return userMngRepository.getUserCount(map);
	}
	
	@Override
	public List<UserMngVo> newList(){
		return userMngRepository.newList();
	}
	
	  @Override
	    public void updateUserApproval(UserMngVo userMngVo) {
		  System.out.println(userMngVo);
	            userMngRepository.save(userMngVo); // 변경사항을 데이터베이스에 저장
	      
	    }
	  @Override
	    public void userMod(UserMngVo userMngVo) {
		System.out.println(userMngVo);
	    userMngRepository.userMod(userMngVo); // 변경사항을 데이터베이스에 저장
	      
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
	  public List<UserMngVo> findByUserInfo(String userId) {
		  System.out.println("서비스ㅡㅡㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
	  return userMngRepository.findByUserInfo(userId); 
}
	  
	  @Override
	  public List<UserMngVo> findByUserInfo2(String userId) {
	  return userMngRepository.findByUserInfo2(userId); 
}

}