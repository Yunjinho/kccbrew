package kr.co.kccbrew.comm.login.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 사용자 회원가입을 위한 Vo
 * @author YUNJINHO
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInVo {
	//공통 데이터
	private String userTypeCd;
	private String userId;
	private String userPwd;
	private String userNm;
	private String userTelNo;
	private String userEmail;
	private String userAddr;
	private String userAddressDetail;
	private String userSalt;
	private MultipartFile imgFile;
	private int fileSeq;
	private String approveYn;
	//가맹 점주 데이터
	private int storeId;
	//수리 기사 데이터
	private String eqpmnCd;
	private String locationCd;
	

}
