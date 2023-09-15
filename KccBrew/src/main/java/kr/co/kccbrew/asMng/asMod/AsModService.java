package kr.co.kccbrew.asMng.asMod;

import java.io.FileOutputStream;
import java.sql.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.dao.IAsMngRepository;
import kr.co.kccbrew.asMng.model.AsMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsModService implements IAsModService {

	private final IAsMngRepository asRepository;
	private final IAsModRepository asModRepository;
	
	/**
	 * AS 접수
	 */
	@Transactional
	public void asMod(AsMngVo asMngVo) {
//		asMngVo=insertUserImg(asMngVo);
		asModRepository.asMod(asMngVo);
	}

	/**
	 * 사용자 사진 등록
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
//	@Transactional
//	private AsMngVo insertUserImg(AsMngVo asMngVo) {
//		AsMngVo vo=new AsMngVo();
//		vo.setUserId(asMngVo.getUserId());
//		//기본 파일정보 등록
//		asRepository.insertFile(vo);
//		asMngVo.setFileSeq(vo.getFileSeq());
//		
//		for(MultipartFile imgFile:asMngVo.getImgFile()) {
//			vo.setFileOriginalNm(imgFile.getOriginalFilename());
//			if(imgFile.getOriginalFilename()!="") {
//				vo.setFileOriginalNm(imgFile.getOriginalFilename());
//				vo.setFileServerNm(asMngVo.getUserId()+"_"+new Date(System.currentTimeMillis())+"_"+imgFile.getOriginalFilename());
//				vo.setFileFmt(imgFile.getContentType());
//				vo.setStorageLocation(asMngVo.getStorageLocation());
//		        String targetPath=asMngVo.getServerSavePath()+"\\"+vo.getFileServerNm();
//				try {
//					//imgFile.transferTo(targetPath);
//					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
//					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(asMngVo.getLocalSavePath()+"\\"+vo.getFileServerNm()));
//				}catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				asRepository.insertFileDtl(vo);
//			}
//		}
//		return asMngVo;
//	}
//	

}
