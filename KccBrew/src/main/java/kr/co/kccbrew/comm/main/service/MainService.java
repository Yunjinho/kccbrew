package kr.co.kccbrew.comm.main.service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.comm.main.dao.IMainRepository;
import kr.co.kccbrew.comm.main.model.MainPageVo;
import kr.co.kccbrew.comm.util.S3Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService implements IMainService{
	private final IMainRepository mainRepository;
	private final S3Service s3Service;
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
	

	//미승인 회원 리스트
	@Override
	public List<MainPageVo> showUnapprovedMemberList() {
		List<MainPageVo> unapprovedMemberList = mainRepository.unapprovedMemberList();
		return unapprovedMemberList;
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
		mainPageVo.setUserAddress(mainPageVo.getUserAddress() + ", " + mainPageVo.getUserAddressDtl());
		List<MainPageVo> vo = showUserInfoListById(mainPageVo.getUserId());
		String fileNm=vo.get(0).getFileDetailLocation();
		s3Service.deleteFile(fileNm);
		
		insertUserImg(mainPageVo);
		mainRepository.updateMyProfile(mainPageVo);
		
		if(mainPageVo.getMechaLocationCode().equals("지역 상세 선택")) {
			mainPageVo.setMechaLocationCode(mainPageVo.getMechaLocation());
		}
	}
	
	//이미지를 제외하고 사용자 정보 수정하기
	@Override
	public void updateMyProfileExceptImg(MainPageVo mainPageVo) {
		mainPageVo.setUserAddress(mainPageVo.getUserAddress() + ", " + mainPageVo.getUserAddressDtl());
		mainRepository.updateMyProfileExceptImg(mainPageVo);
		
		if(mainPageVo.getMechaLocationCode().equals("지역 상세 선택")) {
			mainPageVo.setMechaLocationCode(mainPageVo.getMechaLocation());
		}
	}
	
	//이미지 정보 등록하기
	@Override
	public MainPageVo insertUserImg(MainPageVo mainPageVo) {
		MainPageVo vo = new MainPageVo();
		vo.setUserId(mainPageVo.getUserId());
		UUID uuid= UUID.randomUUID();
		//기본 파일정보 등록
		mainRepository.insertFileInfo(vo);
		MultipartFile imgFile = mainPageVo.getUserImg();
		
		vo.setFileOriginalName(imgFile.getOriginalFilename());
		vo.setFileDetailServerName(uuid+"_"+mainPageVo.getUserId()+"_"+imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
		vo.setFileDetailLocation(uuid+"_"+mainPageVo.getFileDetailLocation());
		try {
			vo.setFileDetailLocation(s3Service.upload(imgFile, "register"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainPageVo.setFileId(vo.getFileId());
		//파일 상세 정보 등록
		mainRepository.insertFileDtlInfo(vo);
		//이미지 파일 저장
		String targetPath = mainPageVo.getServerSavePath()+"\\"+vo.getFileDetailServerName();
		String localPath = mainPageVo.getLocalSavePath()+vo.getFileDetailServerName();
		try {
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(localPath));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return mainPageVo;
	}
		
	//점포 정보 수정하기
	@Override
	public void updateMyStore(MainPageVo mainPageVo) {
		mainRepository.updateMyStore(mainPageVo);
	}
	
	//지역 코드 조회
	@Override
	public List<MainPageVo> selectLocationCd() {
		List<MainPageVo> list = mainRepository.selectLocationCd();
		return list;
	}
	
	//상세 지역 코드 조회
	@Override
	public List<MainPageVo> selectLocationDtlCd(String locCd) {
		List<MainPageVo> list = mainRepository.selectLocationDtlCd(locCd);
		return list;
	}
}
