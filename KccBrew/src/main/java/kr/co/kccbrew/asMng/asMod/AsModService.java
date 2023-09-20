package kr.co.kccbrew.asMng.asMod;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
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
public class AsModService implements IAsModService {

	private final IAsMngRepository asRepository;
	private final IAsModRepository asModRepository;

	/**
	 * AS 접수
	 */
	@Transactional
	public void asMod(AsMngVo asMngVo) {

		asMngVo = insertImg(asMngVo);
		asModRepository.asMod(asMngVo);
	}

	@Override
	public void deleteFile(AsMngVo asMngVo, String imgSeq) {
		List<AsMngVo> list = asRepository.selectAsImg(imgSeq);
		AsMngVo vo = new AsMngVo();

		for (AsMngVo e : list) {
			vo.setFileServerNm(e.getFileServerNm());
			vo.setServerSavePath(e.getServerSavePath());
			String targetPath = asMngVo.getServerSavePath() + "\\" + vo.getFileServerNm();
			File existingImg = new File(targetPath);
			if (existingImg.exists()) {
				boolean deleted = existingImg.delete();
				if (deleted) {
					
				}
			}
		}

		asModRepository.deleteFile(imgSeq);
	}

	/**
	 * 사용자 사진 등록
	 * 
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
	@Transactional
	private AsMngVo insertImg(AsMngVo asMngVo) {
		AsMngVo vo = new AsMngVo();
		vo.setGrpCdDtlId("02");
		vo.setUserId(asMngVo.getUserId());
		// 기본 파일정보 등록

		asRepository.insertFile(vo);
		asMngVo.setFileSeq(vo.getFileSeq());

		for (MultipartFile imgFile : asMngVo.getImgFile()) {
			vo.setFileOriginalNm(imgFile.getOriginalFilename());
			if (imgFile.getOriginalFilename() != "") {
				vo.setFileOriginalNm(imgFile.getOriginalFilename());
				vo.setFileServerNm(asMngVo.getUserId() + "_" + new Date(System.currentTimeMillis()) + "_"
						+ imgFile.getOriginalFilename());

				vo.setFileFmt(imgFile.getContentType());
				vo.setStorageLocation(asMngVo.getStorageLocation());
				String targetPath = asMngVo.getServerSavePath() + "\\" + vo.getFileServerNm();
				File existingImg = new File(targetPath);
				File existingImg2 = new File(asMngVo.getLocalSavePath() + "\\" + vo.getFileServerNm());
				if (existingImg.exists()) {
					boolean deleted = existingImg.delete();
					existingImg2.delete();
					if (deleted) {
						// 파일이 성공적으로 삭제되면 데이터베이스 레코드도 업데이트
						// asRepository.deleteFileDetails(vo.getFileSeq());
					}
				}

				System.out.println("------------------------");
				System.out.println(targetPath);
				try {
					// imgFile.transferTo(targetPath);
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
					FileCopyUtils.copy(imgFile.getInputStream(),
							new FileOutputStream(asMngVo.getLocalSavePath() + "\\" + vo.getFileServerNm()));
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ㅃㅃㅃㅃㅃㅃㅃㅃ삥와닐ㅇ.비ㅏㅇ로니ㅏ어니ㅓㄹㅇ나ㅣ이ㅏ뇌란오ㅓㅣㅏㅇㄴ");

				}
				asRepository.insertFileDtl(vo);
			}
		}
		return asMngVo;
	}

}
