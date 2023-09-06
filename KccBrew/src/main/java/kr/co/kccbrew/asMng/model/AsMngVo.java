package kr.co.kccbrew.asMng.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * AS 관련 vo
 */
@AllArgsConstructor
@Data
public class AsMngVo {
	//List 조회
	private String asInfoSeq;
	private String startYr;
	private String startMn;
	private String endYr;
	private String endMn;
	private Date regDttm;
	private String userId;
	private String storeAddr;
	private String machineCd;
	private String statusCd;
	private String storeNm;
	private String locationCd;
	
	private Date modDttm;
	private int fileSeq;
	private String asContent;
	
	//장비 코드 리스트
	//as 상태 코드 리스트
	//지역 리스트
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
	
	//인자 없는 생성자
	public AsMngVo() {
		this.asInfoSeq="";
		this.startYr="";
		this.startMn="";
		this.endYr="";
		this.endMn="";
		this.userId="";
		this.storeAddr="";
		this.machineCd="";
		this.statusCd="";
		this.storeNm="";
	}

	
}
