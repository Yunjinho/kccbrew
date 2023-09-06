package kr.co.kccbrew.asMng.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * AS 관련 vo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsMngVo {
	//List 조회
	int asInfoSeq;
	Date startDate;
	Date endDate;
	Date regDttm;
	String userId;
	String storeAddr;
	String machineCd;
	String statusCd;
	String storeNm;
	String locationCd;
	
	
	Date modDttm;
	int fileSeq;
	String asContent;
	
	
	//장비 코드 리스트
	//as 상태 코드 리스트
	//지역 리스트
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
	
}
