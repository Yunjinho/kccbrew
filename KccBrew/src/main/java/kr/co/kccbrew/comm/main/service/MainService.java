package kr.co.kccbrew.comm.main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.main.dao.IMainRepository;
import kr.co.kccbrew.comm.main.model.MainPageVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService implements IMainService{
	private final IMainRepository mainRepository;

	//배정 리스트
	@Override
	public List<MainPageVo> showAllAssignList() {
		List<MainPageVo> assignList = mainRepository.showAllAssignList();
		return assignList;
	}

	// a/s 접수 리스트
	@Override
	public List<MainPageVo> showAllAsInfoList() {
		List<MainPageVo> asList = mainRepository.showAllAsInfoList();
		return asList;
	}

	//회원 승인 대기 리스트
	@Override
	public List<MainPageVo> showWaitingMemberList() {
		List<MainPageVo> waitingList = mainRepository.showWaitingMemberList();
		return waitingList;
	}

	// a/s 결과 리스트
	@Override
	public List<MainPageVo> showAsResultList() {
		List<MainPageVo> resultList = mainRepository.showAsResultList();
		return resultList;
	}

	//이번 주 범위
	@Override
	public List<MainPageVo> getDataInRange(LocalDate startOfWeek, LocalDate endOfWeek) {
		return mainRepository.getDataInRange(startOfWeek, endOfWeek);
	}

}
