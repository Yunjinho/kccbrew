package kr.co.kccbrew.comm.main.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MainPageVo {
	String userId; 				//사용자 아이디
	String userName; 			//사용자 이름
	String userTelNo; 			//사용자 전화번호
	String userEmail;			//사용자 이메일
	String userAddress;			//사용자 주소
	String userAddressDtl;		//사용자 상세 주소
	Date userRegDate;			//사용자 정보 등록일
	Date userModDate;			//사용자 정보 수정일
	String userInUse;			//사용자 정보 사용 여부
	String approveStatus;		//사용자 가입 승인 여부
	String admWhoAcp;       	//가입 승인한 관리자
	
	/**
	 * 파일 저장 VO
	 **/
	MultipartFile userImg;		//사용자 이미지
	int fileId;					//파일 번호
	String fileOriginalName;	//파일 원본 이름
	String fileDetailLocation; 	//파일 저장 경로
	String fileDetailServerName;//파일 서버 내 이름
	String fileFmt;				//파일 형식
	String localSavePath;
	String serverSavePath;
	
	
	
	/**
	 * 그룹 코드 VO
	 */
	String grpCdId;			//그룹 코드 아이디
	String grpCdNm;			//그룹 코드명
	String grpCdDtlId;		//그룹 코드 디테일 아이디
	String grpCdDtlNm;		//그룹 코드 디테일명
	
	/**
	 * A/S VO
	 */
	int asAssignNum; 		//배정 번호
	int asInfoNum;  		//접수 번호
	Date visitDate;  		//방문 예정일 
	Date confirmDate; 		//접수일
	String asStatus; 		//as 상태 코드
	
	/**
	 * 수리기사 관련 VO
	 */
	String mechanicId; 			//수리기사 아이디
	String mechanicName; 		//수리기사 이름
	String machineCode; 		//장비 코드
	String mechaLocationCode; 	//지역 상세 코드
	String mechaLocation;  		//지역 코드
	
	/**
	 * 점주 관련 VO
	 */
	String managerId;  		//점주 아이디
	String managerName; 	//점주 이름
	String storeName;		//점포명
	String storeTelNo;		//점포 전화번호
	String storeSeq;		//점포 아이디
	String storeNm;			//점포 이름
	
	String locationCode; 	//점포 지역 코드
	String userType; 		//사용자 구분 코드
	
	String reapply;			//재접수
	
	LocalDate startOfWeek;	//A/S 일정 날짜 구분
	LocalDate endOfWeek;	//A/S 일정 날짜 구분
}
