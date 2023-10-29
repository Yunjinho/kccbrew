package kr.co.kccbrew.asMng.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.batch.AsLogVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * AS 관련 vo
 */
@AllArgsConstructor
@Data
public class AsMngVo {
	//seq
	private String asInfoSeq;
	private String asAssignSeq;
	private String asResultSeq;
	//조회 날짜 구간 (시작, 끝 - 년도,월)
	private String startYr;
	private String startMn; 
	private String endYr;
	private String endMn;
	//기본 정보들
	//아이디, 유저 타입, 검색 아이디, 장비코드,장비 코드 이름, as 상태코드, as 상태 코드 이름, 지역 코드 ,등록 날짜, 유저 이름
	private String userId;
	private String userTypeCd;
	private String searchId;
	private String machineCd;
	private String asStatusCd;
	private String asStatusNm;
	private String machineCdNm;
	private String userNm;
	
	//수정 날짜, 등록 사용자,수정 사용자, AS 접수 이미지,AS 접수 내용
	private String locationCd;
	private String regDttm;
	private Date modDttm;
	private String regUser;
	private String modUser;
	private String fileSeq;
	private String asContent;
	
	//신청 희망일 시작~끝
	private String wishingStartDate;
	private String wishingEndDate;

	//AS 신청 이미지 정보, 파일 원본이름, 파일 서버 저장이름 ,파일 형식, 저장 위치,접수 반려 여부, 접수 반려 이유
	private List<MultipartFile> imgFile;
	private String fileOriginalNm;
	private String fileServerNm;
	private String fileFmt;
	private String storageLocation;
	private String resubmission;
	private String rejectContentStr;
	
	//점주 아이디, 점포 이름, 점포 주소, 점포 상세 주소,위도, 경도, 점포 전화번호, 점포 SEQ
	private String storeMngId;
	private String storeNm;
	private String storeAddr;
	private String storeAddrDtl;
	private String latitude;
	private String longitude;
	private String storeTelNo;
	private String storeSeq;
	//그룸코드
	//장비 코드 리스트
	//as 상태 코드 리스트
	//지역 리스트
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
	//멤버 로컬 저장 위치
	private String localSavePath;
	private String serverSavePath;

	// 기사 이름, 기사 아이디, 배정 반려내용 , 방문 예정일
    private String mechanicNm;
    private String mechanicId;
    private String rejectContentMcha;
    private String visitDttm;
    
    //재배정 여부, as 결과 , as 날짜, as 만족도, as 결과 이미지 , as 청구 금액, 재접수 여부 , 재접수 확인여부 
    private String reassign;
    private String resultDtl;
    private String resultDttm;
    private String storeMngFb;
    private String resultFileSeq;
    private String asPrice;
    private String resultReapply;
    private String reapplyConfirm;
    
    /*누락데이터*/
    private String isOmitted;
    private Date omissionCheckDttm;
    public AsMngVo(AsLogVo asLogVo) {
    	AsMngVo asMngVo = new AsMngVo();
    	asMngVo.setAsInfoSeq(asLogVo.getAsInfoSeq());
    	
    	 LocalDate localDate = LocalDate.now();
    	omissionCheckDttm = java.sql.Date.valueOf(localDate);
    	asMngVo.setOmissionCheckDttm(omissionCheckDttm);
    }
    private String omittedStatus;
	
	//현재페이지
    private int currentPage;
    private String rowNumber;
    private String asProcessNm;
    private String asStatus;
    private String asPerfromer;
    private String asProcessDate;
    private String userTypeNm;
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
		this.localSavePath="";
		this.serverSavePath="";
	    this.mechanicNm="";
	    this.mechanicId="";
	    this.rejectContentStr="";
	    this.rejectContentMcha="";
	    this.visitDttm="";
	    this.reassign="";
	    this.resultDtl="";
	    this.resultDttm="";
	    this.storeMngFb="";
	    this.resultFileSeq="";
	    this.asPrice="";
	    this.resultReapply="";
	    this.storeMngId="";
	}
}
