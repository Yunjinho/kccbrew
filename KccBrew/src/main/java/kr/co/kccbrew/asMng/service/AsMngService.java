package kr.co.kccbrew.asMng.service;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		map.put("start", ((page-1)*10)+1);
		map.put("end", page*10);
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
		asMngVo.setGrpCdDtlId("02");
		asMngVo=insertImg(asMngVo);
		asRepository.insertAs(asMngVo);
	}

	/**
	 * 사용자 사진 등록
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
	@Transactional
	private AsMngVo insertImg(AsMngVo asMngVo) {
		AsMngVo vo=new AsMngVo();
		vo.setGrpCdDtlId("02");
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
		        String targetPath=asMngVo.getServerSavePath()+"\\"+vo.getFileServerNm();
				try {
					//imgFile.transferTo(targetPath);
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(asMngVo.getLocalSavePath()+"\\"+vo.getFileServerNm()));
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				asRepository.insertFileDtl(vo);
			}
		}
		return asMngVo;
	}

	@Override
	public AsMngVo selectAsInfoDetail(String asInfoSeq,String asAssignSeq) {
		return asRepository.selectAsInfoDetail(asInfoSeq,asAssignSeq);
	}

	@Override
	public List<AsMngVo> selectAsImg(String fileDtlId) {
		return asRepository.selectAsImg(fileDtlId);
	}

	@Override
	public List<AsMngVo> selectLocationCd() {
		return asRepository.selectLocationCd();
	}

	@Override
	public List<AsMngVo> selectLocationDtlCd(String locationCd) {
		return asRepository.selectLocationDtlCd(locationCd);
	}

	@Override
	public int checkStrSchedule(String date, String userId) {
		return asRepository.checkStrSchedule(date, userId);
	}

	@Override
	public List<AsMngVo> selectMechList(String date, String locationCd,String machineCd) {
		return asRepository.selectMechList(date, locationCd,machineCd);
	}
	
	@Transactional
	@Override
	public AsMngVo insertAsAssign(AsMngVo asMngVo) {
		asRepository.insertAsAssign(asMngVo);
		asRepository.updateAsInfoStatus(asMngVo);
		return asMngVo;
	}

	@Override
	public void updateInfoReject(String seq, String content,String userId) {
		asRepository.updateInfoReject(seq, content,userId);
	}

	@Override
	public void updateAssignReject(String seq, String content,String userId) {
		asRepository.updateAssignReject(seq, content,userId);
		
	}

	@Override
	public void updateRejectConfirm(AsMngVo asMngVo,String flag) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("asMngVo", asMngVo);
		map.put("flag", (String)flag);
		
		asRepository.updateRejectConfirm(map);
	}

	@Override
	public void insertAsResult(AsMngVo asMngVo) {
		asMngVo.setGrpCdDtlId("03");
		asMngVo=insertImg(asMngVo);
		asRepository.insertResult(asMngVo);
		asMngVo.setAsStatusCd("04");
		asRepository.updateAsInfoStatus(asMngVo);
	}

	@Override
	public void updateResultMng(AsMngVo asMngVo) {
		asRepository.updateResultMng(asMngVo);
	}
}
