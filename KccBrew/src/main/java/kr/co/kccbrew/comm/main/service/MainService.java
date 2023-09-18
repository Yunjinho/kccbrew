package kr.co.kccbrew.comm.main.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<MainPageVo> showAllAsAssignList() {
		List<MainPageVo> assignList = mainRepository.showAllAsAssignList();
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

	//아이디로 이름 찾기
	@Override
	public String getUserName(String userId) {
		return mainRepository.getUserName(userId);
	}

	//특정 아이디에 해당하는 A/S 배정 데이터 조회
	@Override
	public List<MainPageVo> showAsAssiginListbyId(String userId) {
		List<MainPageVo> assinListById = mainRepository.showAsAssiginListbyId(userId);
		return assinListById;
	}

	//특정 아이디에 해당하는 A/S 접수 데이터 조회
	@Override
	public List<MainPageVo> showAsInfoListbyId(String userId) {
		List<MainPageVo> asInfoListById = mainRepository.showAsInfoListbyId(userId);
		return asInfoListById;
	}
	
	//특정 아이디에 해당하는 처리 결과 데이터 조회
	@Override
	public List<MainPageVo> showAsResultListbyId(String userId) {
		List<MainPageVo> asResultListById = mainRepository.showAsResultListbyId(userId);
		return asResultListById;
	}

	@Override
	public List<MainPageVo> showAsAssignListbyMechaId(String userId) {
		List<MainPageVo> asAssignListbyMechaId = mainRepository.showAsAssignListbyMechaId(userId);
		return asAssignListbyMechaId;
	}

	@Override
	public List<MainPageVo> showAsResultListbyMechaId(String userId) {
		List<MainPageVo> asResultListbyMechaId = mainRepository.showAsResultListbyMechaId(userId);
		return asResultListbyMechaId;
	}

	@Override
	public List<MainPageVo> getMechaDataInRangeById(String userId, LocalDate startOfWeek, LocalDate endOfWeek) {
		return mainRepository.getMechaDataInRangeById(userId, startOfWeek, endOfWeek);
	}

	@Override
	public List<MainPageVo> getDataInRangeById(String userId, LocalDate startOfWeek, LocalDate endOfWeek) {
		return mainRepository.getDataInRangeById(userId, startOfWeek, endOfWeek);
	}

	@Override
	public List<MainPageVo> showUserInfoListById(String userId) {
		List<MainPageVo> userInfoListById = mainRepository.showUserInfoListById(userId);
		return userInfoListById;
	}

	@Override
	public List<MainPageVo> showStoreInfoListById(String userId) {
		List<MainPageVo> storeInfoListById = mainRepository.showStoreInfoListById(userId);
		return storeInfoListById;
	}

	@Override
	public void updateMyProfile(String userId) {
		mainRepository.updateMyProfile(userId);
	}

}
