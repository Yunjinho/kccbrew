package kr.co.kccbrew.comm.main.service;


import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

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
	
	//수리기사 앞으로 온 배정 내역
	@Override
	public List<MainPageVo> showAsAssignListbyMechaId(String userId) {
		List<MainPageVo> asAssignListbyMechaId = mainRepository.showAsAssignListbyMechaId(userId);
		return asAssignListbyMechaId;
	}

	//수리기사의 결과 내역
	@Override
	public List<MainPageVo> showAsResultListbyMechaId(String userId) {
		List<MainPageVo> asResultListbyMechaId = mainRepository.showAsResultListbyMechaId(userId);
		return asResultListbyMechaId;
	}
	
	//수리기사의 날짜별 a/s 내역 
	@Override
	public List<MainPageVo> getMechaDataInRangeById(String userId, LocalDate startOfWeek, LocalDate endOfWeek) {
		return mainRepository.getMechaDataInRangeById(userId, startOfWeek, endOfWeek);
	}
	
	//날짜별로 a/s 내역 가져오기
	@Override
	public List<MainPageVo> getDataInRangeById(String userId, LocalDate startOfWeek, LocalDate endOfWeek) {
		return mainRepository.getDataInRangeById(userId, startOfWeek, endOfWeek);
	}

	//사용자 정보 가져오기
	@Override
	public List<MainPageVo> showUserInfoListById(String userId) {
		List<MainPageVo> userInfoListById = mainRepository.showUserInfoListById(userId);
		return userInfoListById;
	}
	
	//점포 정보 가져오기
	@Override
	public List<MainPageVo> showStoreInfoListById(String userId) {
		List<MainPageVo> storeInfoListById = mainRepository.showStoreInfoListById(userId);
		return storeInfoListById;
	}
	
	//사용자 정보 수정하기   
	@Override
	public void updateMyProfile(MainPageVo mainPageVo) {
		mainRepository.updateMyProfile(mainPageVo);
	}
	
	//이미지 정보 등록하기
	@Override
	public MainPageVo insertUserImg(MainPageVo mainPageVo) {
		MainPageVo vo = new MainPageVo();
		vo.setUserId(mainPageVo.getUserId());
		//기본 파일정보 등록
		mainRepository.insertFileInfo(mainPageVo);
		MultipartFile imgFile = mainPageVo.getUserImg();
		vo.setFileOriginalName(imgFile.getOriginalFilename());
		vo.setFileDetailServerName(mainPageVo.getUserId()+"_"+imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
		vo.setFileDetailLocation(mainPageVo.getFileDetailLocation());
		mainPageVo.setFileId(vo.getFileId());
		//파일 상세 정보 등록
		mainRepository.insertFileDtlInfo(mainPageVo);
		//이미지 파일 저장
		String targetPath = mainPageVo.getServerSavePath()+"\\"+vo.getFileDetailServerName();
		String localPath = mainPageVo.getLocalSavePath()+"\\"+vo.getFileDetailServerName();
		try {
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(localPath));
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return mainPageVo;
	}
	
	//사용자 이미지 수정 및 신규 이미지 추가
	@Override
	public void updateMyProfileImg(MainPageVo mainPageVo) {
		insertUserImg(mainPageVo);
		mainRepository.updateMyProfileImg(mainPageVo);
	}
		
	//점포 정보 수정하기
	@Override
	public void updateMyStore(MainPageVo mainPageVo) {
		mainRepository.updateMyStore(mainPageVo);
	}
}
