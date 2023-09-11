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
	//기본 정보들..
	private String userId;
	private String userTypeCd;
	private String searchId;
	private String machineCd;
	private String asStatusCd;
	private String asStatusNm;
	private String machineCdNm;
	private String locationCd;
	private Date regDttm;
	private Date modDttm;
	private int fileSeq;
	private String asContent;
	//신청 희망일 시작~끝
	private String wishingStartDate;
	private String wishingEndDate;

	//AS 신청 이미지 정보
	private List<MultipartFile> imgFile;
	private String fileOriginalNm;
	private String fileServerNm;
	private String fileFmt;
	private String storageLocation;
	
	//점포 정보
	private String storeNm;
	private String storeAddr;
	private String storeAddrDtl;
	private String latitude;
	private String longitude;
	private String storeTelNo;
	
	//그룸코드
	//장비 코드 리스트
	//as 상태 코드 리스트
	//지역 리스트
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
	
	//인자 없는 생성자

	public AsMngVo() {
		this.asInfoSeq = "";
		this.startYr = "";
		this.startMn = "";
		this.endYr = "";
		this.endMn = "";
		this.userId = "";
		this.userTypeCd = "";
		this.searchId = "";
		this.machineCd = "";
		this.asStatusCd = "";
		this.asStatusNm = "";
		this.machineCdNm = "";
		this.locationCd = "";
		this.asContent = "";
		this.wishingStartDate = "";
		this.wishingEndDate = "";
		this.fileOriginalNm = "";
		this.fileServerNm = "";
		this.fileFmt = "";
		this.storageLocation = "";
		this.storeNm = "";
		this.storeAddr = "";
		this.storeAddrDtl = "";
		this.latitude = "";
		this.longitude = "";
		this.storeTelNo = "";
		this.grpCdDtlId = "";
		this.grpCdId = "";
		this.grpCdNm = "";
		this.grpCdDtlNm = "";
	}

	
}
