package kr.co.kccbrew.comm.main.service;

import java.time.LocalDate;
import java.util.List;

import kr.co.kccbrew.comm.main.model.MainPageVo;

public interface IMainService {
	List<MainPageVo> showAllAssignList();     // a/s 배정 리스트 
	List<MainPageVo> showAllAsInfoList();	  // a/s 접수 리스트
	List<MainPageVo> showWaitingMemberList(); // 회원 승인 대기 리스트
	List<MainPageVo> showAsResultList();      // a/s 결과 리스트
	List<MainPageVo> getDataInRange(LocalDate startOfWeek, LocalDate endOfWeek);    //이번 주 범위 구하기
}
