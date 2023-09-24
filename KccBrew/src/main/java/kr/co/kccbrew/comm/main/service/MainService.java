package kr.co.kccbrew.comm.main.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	
	//사용자 정보 수정하기   mainRepository.updateMyProfile(mainPageVo);
	@Override
	public void updateMyProfile(String userId, MainPageVo mainPageVo, MultipartFile imgFile) throws IOException {
		 if (imgFile != null && !imgFile.isEmpty()) {
		        String newImageName = generateRandomImageName(); // 랜덤한 파일 이름 생성
		        String imagePath = updateProfileImage(null, userId, newImageName, imgFile);
		        mainPageVo.setFileDetailLocation(imagePath);
		    }

		    // 사용자 정보 업데이트
		    mainRepository.updateMyProfile(mainPageVo);
	}
	
	// 랜덤한 이미지 파일 이름 생성
	private String generateRandomImageName() {
	    String uuid = UUID.randomUUID().toString();
	    return uuid + ".jpg"; // 랜덤한 파일 이름 생성 (예: UUID.jpg)
	}
	
	// 이미지 업로드 및 이미지 파일 이름 업데이트 메서드
	private String updateProfileImage(HttpServletRequest request, String userId, String newImageName, MultipartFile imgFile) throws IOException {
	    String basePath = "/resources/img/register/users/";
	    String imagePath = basePath + newImageName;

	    File imageFile = new File(request.getSession().getServletContext().getRealPath(imagePath));
	    imgFile.transferTo(imageFile);

	    return imagePath;
	}

	//점포 정보 수정하기
	@Override
	public void updateMyStore(MainPageVo mainPageVo) {
		mainRepository.updateMyStore(mainPageVo);
	}
}
