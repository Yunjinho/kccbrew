package kr.co.kccbrew.comm.security.service;

import java.io.FileOutputStream;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.strMng.model.StrMngVo;

@Service
public class UserService implements IUserService{
	@Autowired
	private IUserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
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
		
		//mapper 테이블에 점포, 점주 연결
		if(userVo.getUserTypeCd().equals("02")){
			userRepository.insertStoreUserMap(userVo.getUserId(), userVo.getStoreId());
		}
		
		userRepository.registerUser(userVo);
	}
	
	@Override
	public UserVo insertUserImg(UserVo user) {
		UserVo vo=new UserVo();
		vo.setUserId(user.getUserId());
		//기본 파일정보 등록
		userRepository.insertFileInfo(vo);
		MultipartFile imgFile = user.getImgFile();
		vo.setFileOriginalNm(imgFile.getOriginalFilename());
		vo.setFileServerNm(user.getUserId()+"_"+imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
		vo.setStorageLocation(user.getStorageLocation());
		user.setFileSeq(vo.getFileSeq());
		//파일 상세 정보 등록
		userRepository.insertFileDtlInfo(vo);
		//이미지 파일 저장
        String targetPath=user.getServerSavePath()+"\\"+vo.getFileServerNm();
        String localPath=user.getLocalSavePath()+"\\"+vo.getFileServerNm();
		try {
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
	public StrMngVo getStoreById(String userId) {
		return userRepository.getStoreById(userId);
	}
	
 
}
