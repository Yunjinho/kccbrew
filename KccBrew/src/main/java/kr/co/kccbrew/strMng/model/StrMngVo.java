package kr.co.kccbrew.strMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 점포관리를 위한 Vo
 * 
 * @author BAESOOYEON
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StrMngVo {
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
	String useYn;
	String userNm;
	String userTelNo;
	int storeSeq;
	//검색조건
	int currentPage;
	String startDate;
	String endDate;
	String keyword;
	
	String rowNumber;
	String flag;
}
