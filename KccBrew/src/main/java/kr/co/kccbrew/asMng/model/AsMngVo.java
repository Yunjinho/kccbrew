package kr.co.kccbrew.asMng.model;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * AS 관련 vo
 */
@AllArgsConstructor
@Data
public class AsMngVo {
	//AS 번호
	private String asInfoSeq;
	//조회 날짜 구간
	private String startYr;
	private String startMn;
	private String endYr;
	private String endMn;
	//등록 날짜
	private Date regDttm;
	//기본 정보들..
	private String userId;
	private String storeAddr;
	private String machineCd;
	private String statusCd;
	private String storeNm;
	private String locationCd;
	
	private Date modDttm;
	private int fileSeq;
	private String asContent;
	
	//신청 희망일 시작~끝
	private Date wishingStartDate;
	private Date wishingEndDate;
	//AS 신청 이미지
	private List<MultipartFile> imgFile;
	
	
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
