package kr.co.kccbrew.asMng.service;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.dao.IAsMngRepository;
import kr.co.kccbrew.asMng.model.AsMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsMngService implements IAsMngService{
	
	/**
	 * asRepository 변수 선언
	 */
	private final IAsMngRepository asRepository;
	
	
	/**
	 *  조회한 검색 결과
	 */
	@Override
	public List<AsMngVo> selectASList(AsMngVo asMngVo,int page) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("asVo", asMngVo);
		map.put("start", page);
		map.put("end", page+9);
		return asRepository.selectASList(map);
	}
	
	/**
	 * 조회할 AS 리스트의 수
	 */
	@Override
	public int countASList(AsMngVo asMngVo) {
		return asRepository.countASList(asMngVo);
	}
	
	/**
	 * 장비 코드 리스트 조회
	 * @return : 장비 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectMachineCd() {
		return asRepository.selectMachineCd();
	}

	/**
	 * AS상태 코드 리스트 조회
	 * @return : AS상태 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectAsStatusCd() {
		// TODO Auto-generated method stub
		return asRepository.selectAsStatusCd();
	}

	/**
	 * 지역 코드 리스트 조회
	 * @return : 지역 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectLocationCd() {
		return asRepository.selectLocationCd();
	}
	/**
	 * 로그인한 사용자와 매핑 되어있는 점포 정보
	 */
	@Override
	public AsMngVo selectStrInfo(String userId) {
		return asRepository.selectStrInfo(userId);
	}
	
	/**
	 * AS 접수
	 */
	@Transactional
	public void insertAs(AsMngVo asMngVo) {
		asMngVo=insertUserImg(asMngVo);
		asRepository.insertAs(asMngVo);
	}

	/**
	 * 사용자 사진 등록
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
	@Transactional
	private AsMngVo insertUserImg(AsMngVo asMngVo) {
		AsMngVo vo=new AsMngVo();
		vo.setUserId(asMngVo.getUserId());
		//기본 파일정보 등록
		asRepository.insertFile(vo);
		asMngVo.setFileSeq(vo.getFileSeq());
		
		for(MultipartFile imgFile:asMngVo.getImgFile()) {
			vo.setFileOriginalNm(imgFile.getOriginalFilename());
			if(imgFile.getOriginalFilename()!="") {
				vo.setFileOriginalNm(imgFile.getOriginalFilename());
				vo.setFileServerNm(asMngVo.getUserId()+"_"+new Date(System.currentTimeMillis())+"_"+imgFile.getOriginalFilename());
				vo.setFileFmt(imgFile.getContentType());
				vo.setStorageLocation(asMngVo.getStorageLocation());
				Path path = Paths.get(vo.getStorageLocation()).toAbsolutePath().normalize();
		        Path targetPath = path.resolve(vo.getFileServerNm()).normalize();
				try {
					//imgFile.transferTo(targetPath);
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath.toFile()));
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				asRepository.insertFileDtl(vo);
			}
		}
		return asMngVo;
	}

}
