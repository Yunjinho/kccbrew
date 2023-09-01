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
	List<MainPageVo> showAllAssignList();     // a/s 배정 리스트 
	List<MainPageVo> showAllAsInfoList();	  // a/s 접수 리스트
	List<MainPageVo> showWaitingMemberList(); // 회원 승인 대기 리스트
	List<MainPageVo> showAsResultList();      // a/s 결과 리스트
	List<MainPageVo> getDataInRange(@Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek);
    
}
