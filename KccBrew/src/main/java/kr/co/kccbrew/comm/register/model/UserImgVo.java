package kr.co.kccbrew.comm.register.model;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 회원 가입 시 본인 이미지 등록을 위한 Vo
 * @author YUNJINHO
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserImgVo {
	private int fileSeq;
	private String fileOriginalNm;
	private String fileServerNm;
	private String fileFmt;
	
	@Value("${resouse.img.user}")
	private String storageLocation;
	
	private String userId;
}
