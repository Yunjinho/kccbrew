package kr.co.kccbrew.comm.security.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.util.S3Service;
import kr.co.kccbrew.strMng.model.StrMngVo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final S3Service s3Service;
	/**
	 * 검색한 키워드를 통해 운영하고 있는 점포 리스트를 조회한다.
	 * @return 운영중인 점포 리스트
	 */
	@Override
	public List<UserVo> selectStoreList(String keyword,int page) {
		int start=(page-1)*5+1;
		List<UserVo> list = userRepository.selectStoreList(keyword,start,start+4);
		return list;
	}

	/**
	 * 키워드 조건에 조회된 점포 목록의 수
	 * @return 점포의 수
	 */
	@Override
	public int countStoreList(String keyword) {
		int count = userRepository.countStoreList(keyword);
		return count;
	}

	/**
	 * 운영중인 장비군 목록을 조회한다
	 * @return 장비군 목록 리스트
	 */
	@Override
	public List<UserVo> selectMechineCode() {
		List<UserVo> list = userRepository.selectMechineCode();
		return list;
	}

	/**
	 * 아이디 중복체크
	 * @return 아이디 조회했을 때 나오는 갯수
	 */
	@Override
	public int checkUserId(String userId) {
		int count=userRepository.checkUserId(userId);
		return count;
	}

	/**
	 * 지역 코드 조회
	 * @return 지역 코드 목록
	 */
	@Override
	public List<UserVo> selectLocationCd() {
		List<UserVo> list=userRepository.selectLocationCd();
		return list;
	}

	/**
	 * 상세 지역 목록 조회
	 * @return 조회한 상세 지역 목록
	 */
	@Override
	public List<UserVo> selectLocationDtlCd(String locCd) {
		List<UserVo> list=userRepository.selectLocationDtlCd(locCd);
		return list;
	}




	@Override
	public void registerUser(UserVo userVo) {
		userVo.setUserPwd(passwordEncoder.encode(userVo.getUserPwd()));

		//주소+상세주소
		userVo.setUserAddr(userVo.getUserAddr()+","+userVo.getUserAddressDetail());
		//사용자사진등록
		if(!userVo.getImgFile().isEmpty()) {
			insertUserImg(userVo);
		}

		if(userVo.getLocationCd().equals("지역 상세 선택")) {
			userVo.setLocationCd(userVo.getLocation());
		}

		userRepository.registerUser(userVo);
		
		/*점주일 경우  STORE_USER_MAP테이블에 점주-가게 연결*/
		if(userVo.getUserTypeCd().equals("02")) {
			userRepository.insertStoreUserMap(userVo.getUserId(), userVo.getStoreId());
		}
	}


/*	@Override
	public void insertStoreUserMap(String userId, int storeId) {
		userRepository.insertStoreUserMap(userId, storeId);
	}*/

	@Override
	public UserVo insertUserImg(UserVo user) {
		try {
		UserVo vo=new UserVo();
		vo.setUserId(user.getUserId());
		UUID uuid= UUID.randomUUID();
		//기본 파일정보 등록
		userRepository.insertFileInfo(vo);
		MultipartFile imgFile = user.getImgFile();
		vo.setFileOriginalNm(imgFile.getOriginalFilename());
		
		vo.setFileServerNm(uuid+"_"+imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
//		vo.setStorageLocation(user.getStorageLocation());
		vo.setStorageLocation(s3Service.upload(imgFile, "register"));
		user.setFileSeq(vo.getFileSeq());
		//파일 상세 정보 등록
		userRepository.insertFileDtlInfo(vo);
		//이미지 파일 저장
		String targetPath=user.getServerSavePath()+"\\"+vo.getFileServerNm();
		String localPath=user.getLocalSavePath()+"\\"+vo.getFileServerNm();
			//imgFile.transferTo(targetPath);
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(localPath));
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}


	@Override
	public UserVo getUserById(String userId) {
		return userRepository.getUserById(userId) ;
	}


	@Override
	public List<StrMngVo> getStoreById(String userId) {
		return userRepository.getStoreById(userId);
	}

	/**
	 * 회원ID에 따른 AS신청(배정전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *  @return List<AsMngVo>: AS신청(배정전) 리스트
	 */
	@Override
	public List<AsMngVo> getAsAccepting(String userId) {
		return userRepository.selectAsAccepting(userId);
	}

	/**
	 * 회원ID에 따른 AS배정(처리전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *@return List<AsMngVo>: AS배정(처리전) 리스트
	 */
	@Override
	public List<AsMngVo> getAsSubmissionCompleted(String userId) {
		return userRepository.selectAsSubmissionCompleted(userId);
	}

	/**
	 * 회원ID에 따른 AS처리완료 리스트 조회
	 * @param userId : 사용자 아이디@return
	 * @return List<AsMngVo>:  AS처리완료 리스트
	 */
	@Override
	public List<AsMngVo> getAsProcessingCompleted(String userId) {
		return userRepository.selectAsProcessingCompleted(userId);
	}
}
