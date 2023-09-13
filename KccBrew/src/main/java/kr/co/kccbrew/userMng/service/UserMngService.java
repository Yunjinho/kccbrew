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
	    public void updateUserApproval(String userId, String approveYn) {
	        // userId를 사용하여 사용자 정보를 데이터베이스에서 가져온다고 가정
	        UserMngVo user = userMngRepository.findByUserId(userId);

	        if (user != null) {
	            // 사용자 정보가 존재하면 승인 상태를 업데이트한다.
	            user.setApproveYn(approveYn); // 예: "Y" 또는 "N"을 설정
	            userMngRepository.save(user); // 변경사항을 데이터베이스에 저장
	        } else {
	            // 사용자 정보가 존재하지 않을 경우 예외 처리 (적절한 처리 방법을 선택하세요)
	            throw null;
	        }
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