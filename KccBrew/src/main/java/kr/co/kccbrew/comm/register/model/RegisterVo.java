package kr.co.kccbrew.comm.register.model;

import org.springframework.beans.factory.annotation.Value;
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
public class RegisterVo {
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
	
	/**
	 * 회원가입시 등록된  장비군,지역코드를 조회하기 위한 Vo
	 * @author YUNJINHO
	 */
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
	
	/**
	 * 회원가입시 점포 리스트를 조회하기 위한 Vo
	 * @author YUNJINHO
	 *
	 */
	private String storeSeq;
	private String storeNm;
	private String storeAddr;
	private String storeTelNo;
	/**
	 * 회원 가입 시 본인 이미지 등록을 위한 Vo
	 * @author YUNJINHO
	 *
	 */
	private String fileOriginalNm;
	private String fileServerNm;
	private String fileFmt;
	
	private String storageLocation;
	
}
