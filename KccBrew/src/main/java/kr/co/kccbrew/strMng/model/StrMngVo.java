package kr.co.kccbrew.strMng.model;

import java.sql.Date;

import lombok.Data;
/**
 * 점포관리를 위한 Vo
 * 
 * @author BAESOOYEON
 */
@Data
public class StrMngVo {
	int storeSeq;
	String storeNm;
	String storeAddr;
	String storeAddrDtl;
	double latitude;
	double longitude;
	String storeTelNo;
	String locationCd;
	String locationCdSeoul;
	String locationNm;
	Date regDttm;
	String regUser;
	Date modDttm;
	String modUser;
	char useYn;
	String userNm;
	String userTelNo;
	
	
}
