package kr.co.kccbrew.comm.main.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.main.model.MainPageVo;

public interface IMainService {
	List<MainPageVo> showAllAsAssignList();     // a/s 배정 리스트 
	List<MainPageVo> showAllAsInfoList();	  // a/s 접수 리스트
	List<MainPageVo> showWaitingMemberList(); // 회원 승인 대기 리스트
	List<MainPageVo> showAsResultList();      // a/s 결과 리스트
	
	List<MainPageVo> showUserInfoListById(String userId);	//특정 사용자의 정보 리스트
	List<MainPageVo> showStoreInfoListById(String userId);  //점포 정보 리스트
	
	void updateMyProfileImg(MainPageVo mainPageVo); //프로필 이미지 수정
	MainPageVo insertUserImg(MainPageVo mainPageVo);//사용자 이미지 넣기
	
	void updateMyProfile(MainPageVo mainPageVo);//마이페이지 정보 업데이트
	void updateMyStore(MainPageVo mainPageVo); //가게 정보 업데이트
	
	List<MainPageVo> showAsAssiginListbyId(String userId);
	List<MainPageVo> showAsInfoListbyId(String userId);
	List<MainPageVo> showAsResultListbyId(String userId);
	List<MainPageVo> showAsAssignListbyMechaId(String userId);
	List<MainPageVo> showAsResultListbyMechaId(String userId);
	List<MainPageVo> getDataInRange(LocalDate startOfWeek, LocalDate endOfWeek);    //이번 주 범위 구하기
	List<MainPageVo> getMechaDataInRangeById(@Param("userId") String userId, @Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek);
	List<MainPageVo> getDataInRangeById(@Param("userId") String userId, @Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek);
	String getUserName(String userId);
}
