package kr.co.kccbrew.userMng.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMngVo {
	String userId;
	String userPwd;
	String userSalt;
	String userNm;
	String userTelNo;
	String userEmail;
	String userAddr;
	String eqpmnCd; //장비코드
	String eqpmnNm; //장비이름
	int fileSeq;
	String locationCd;
	String locationNm;
	String userTypeCd;
	Date regDttm;
	Date modDttm;
	String useYn; //계정사용여부
	String approveYn; //승인여부
	String approveAdmin; //수정관리자
	String userType;
	String storeNm;
	String storeTelNo;
	String imgNm;
	String imgUrl;
	
	private Integer rowNum;
	int currentPage;
	String startDate;
	String endDate;
}
