package kr.co.kccbrew.comm.main.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.comm.main.model.MainPageVo;

@Mapper
@Repository
public interface IMainRepository {
	List<MainPageVo> showAllAsAssignList();     // a/s 배정 리스트 
	List<MainPageVo> showAllAsInfoList();	  // a/s 접수 리스트
	List<MainPageVo> showWaitingMemberList(); // 회원 승인 대기 리스트
	List<MainPageVo> showAsResultList();      // a/s 결과 리스트
	List<MainPageVo> unapprovedMemberList(); // 미승인된 회원리스트
	
	List<MainPageVo> showUserInfoListById(String userId);	//특정 사용자의 정보 리스트
	List<MainPageVo> showStoreInfoListById(String userId);  //점포 정보 리스트
	
	List<MainPageVo> selectLocationCd();					//지역 코드 조회
	List<MainPageVo> selectLocationDtlCd(@Param("mechaLocationCode")String locationCd); //지역 코드 상세 조회
	
	void updateMyProfile(MainPageVo mainPageVo); 			//이미지 포함한 마이페이지 정보 업데이트 
	void updateMyProfileExceptImg(MainPageVo mainPageVo); 	//이미지 제외한 정보만 업데이트
	void updateMyStore(MainPageVo mainPageVo); 				//가게 정보 업데이트
	
	void insertFileInfo(MainPageVo mainPageVo); 			//사용자 이미지 파일 기본 정보 등록
	void insertFileDtlInfo(MainPageVo mainPageVo); 			//사용자 이미지 파일 상세 정보 등록
	
	List<MainPageVo> showAsAssiginListbyId(String userId);  //특정 아이디의 배정 리스트
	List<MainPageVo> showAsInfoListbyId(String userId);		//특정 아이디의 접수 리스트
	List<MainPageVo> showAsResultListbyId(String userId);	//특정 아이디의 결과 리스트
	List<MainPageVo> showAsAssignListbyMechaId(String userId); //수리기사의 배정 리스트
	List<MainPageVo> showAsResultListbyMechaId(String userId); //수리기사의 결과 리스트
	List<MainPageVo> getDataInRange(@Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek); //전체 a/s 일정 날짜 별로 구분해서 보기
	List<MainPageVo> getMechaDataInRangeById(@Param("userId") String userId, @Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek); //특정 수리기사의 a/s 일정 날짜 별로 보기
	List<MainPageVo> getDataInRangeById(@Param("userId") String userId, @Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek); //특정 사용자의 a/s 일정 날짜 별로 보기
    String getUserName(String userId);
}