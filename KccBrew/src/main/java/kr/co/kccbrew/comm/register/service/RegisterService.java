	package kr.co.kccbrew.comm.register.service;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.comm.register.dao.IRegisterRepository;
import kr.co.kccbrew.comm.register.model.RegisterVo;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RegisterService implements IRegisterService{
	/**
	 * CommMapper 변수 선언
	 */
	private final IRegisterRepository registerRepasotory;
	
	/**
	 * 검색한 키워드를 통해 운영하고 있는 점포 리스트를 조회한다.
	 * @return 운영중인 점포 리스트
	 */
	@Override
	public List<RegisterVo> selectStoreList(String keyword,int page) {
		int start=(page-1)*5+1;
		List<RegisterVo> list = registerRepasotory.selectStoreList(keyword,start,start+4);
		return list;
	}
	
	/**
	 * 키워드 조건에 조회된 점포 목록의 수
	 * @return 점포의 수
	 */
	@Override
	public int countStoreList(String keyword) {
		int count = registerRepasotory.countStoreList(keyword);
		return count;
	}
	
	/**
	 * 운영중인 장비군 목록을 조회한다
	 * @return 장비군 목록 리스트
	 */
	@Override
	public List<RegisterVo> selectMechineCode() {
		List<RegisterVo> list = registerRepasotory.selectMechineCode();
		return list;
	}
	
	/**
	 * 아이디 중복체크
	 * @return 아이디 조회했을 때 나오는 갯수
	 */
	@Override
	public int checkUserId(String userId) {
		int count=registerRepasotory.checkUserId(userId);
		return count;
	}
	
	/**
	 * 지역 코드 조회
	 * @return 지역 코드 목록
	 */
	@Override
	public List<RegisterVo> selectLocationCd() {
		List<RegisterVo> list=registerRepasotory.selectLocationCd();
		return list;
	}
	
	/**
	 * 상세 지역 목록 조회
	 * @return 조회한 상세 지역 목록
	 */
	@Override
	public List<RegisterVo> selectLocationDtlCd(String locCd) {
		List<RegisterVo> list=registerRepasotory.selectLocationDtlCd(locCd);
		return list;
	}
	
	/**
	 * 사용자 회원가입
	 */
	@Override
	@Transactional
	public void registerUser(RegisterVo user) {
		//salt값 객체에 저장
		user.setUserSalt(getSalt());
		//암호화된 비밀번호 객체에 저장
		user.setUserPwd(getEncrypt(user.getUserPwd(), user.getUserSalt()));
		//주소+상세주소
		user.setUserAddr(user.getUserAddr()+","+user.getUserAddressDetail());
		//사용자사진등록
		if(!user.getImgFile().isEmpty()) {
			insertUserImg(user);
		}
		System.out.println(user);
		//회원가입
		registerRepasotory.registerUser(user);
		
		//mapper 테이블에 점포, 점주 연결
		if(user.getUserTypeCd().equals("02")){
			registerRepasotory.insertStoreUserMap(user.getUserId(), user.getStoreId());
		}
	}
	
	/**
	 * 사용자 사진 등록
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
	@Transactional
	private RegisterVo insertUserImg(RegisterVo user) {
		RegisterVo vo=new RegisterVo();
		UUID pk = UUID.randomUUID();
		vo.setUserId(user.getUserId());
		//기본 파일정보 등록
		registerRepasotory.insertFileInfo(vo);
		MultipartFile imgFile = user.getImgFile();
		vo.setFileOriginalNm(imgFile.getOriginalFilename());
		vo.setFileServerNm(user.getUserId()+"_"+pk+"_"+imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
		vo.setStorageLocation(user.getStorageLocation());
		user.setFileSeq(vo.getFileSeq());
		//파일 상세 정보 등록
		registerRepasotory.insertFileDtlInfo(vo);
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
	
	/**
	 * 
	 * @param pwd : 입력된 사용자 비밀번호
	 * @param salt : 난수
	 * @return : pwd+salt 값을 더해 해싱된 값
	 */
	private String getEncrypt(String pwd,String salt) {
		String hashingPwd="";
		try {
			//SHA-256 알고리즘 객체 생성
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			//pwd+salt 문자열에 알고리즘 적용
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt=md.digest();
			
			//문자열로 변경
			StringBuffer sb= new StringBuffer();
			for(byte b:pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			hashingPwd=sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return hashingPwd;
	}
	
	/**
	 * 비밀번호 암호화에 쓰이는 salt값 생성
	 * @return 생성한 salt값
	 */
	private String getSalt() {
		//난수 객체 생성
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[20];
		//난수 생성
		r.nextBytes(salt);
		//문자열로 변경
		StringBuffer sb=new StringBuffer();
		for(byte b:salt) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
		
	}

}
