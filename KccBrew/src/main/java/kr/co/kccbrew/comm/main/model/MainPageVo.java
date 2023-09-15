package kr.co.kccbrew.comm.main.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.Data;

@Data
public class MainPageVo {
	String userId; 			//사용자 아이디
	String userName; 		//사용자 이름
	String userTelNo; 		//사용자 전화번호
	String userEmail;		//사용자 이메일
	String userAddress;		//사용자 주소
	Date userRegDate;		//사용자 정보 등록일
	Date userModDate;		//사용자 정보 수정일
	String userInUse;		//사용자 정보 사용 여부
	String approveStatus;	//사용자 가입 승인 여부
	String admWhoAcp;       //가입 승인한 관리자
	
	
	int asAssignNum; 		//배정 번호
	int asInfoNum;  		//접수 번호
	Date visitDate;  		//방문 예정일 
	Date confirmDate; 		//접수일
	String asStatus; 		//as 상태 코드
	
	
	String mechanicId; 		//수리기사 아이디
	String mechanicName; 	//수리기사 이름
	String eqpmnCd;			//장비 코드
	String mechaLocationCd;	//활동 지역
	
	String managerId;  		//점주 아이디
	String managerName; 	//점주 이름
	String storeName;		//점포명
	String storeTelNo;		//점포 전화번호
	String storeSeq;		//점포 아이디
	
	String locationCode; 	//점포 지역 코드
	String machineCode; 	//장비 코드
	String userType; 		//사용자 구분 코드
	String groupCodeName;	//그룹 코드 이름
	
	LocalDate startOfWeek;	//A/S 일정 날짜 구분
	LocalDate endOfWeek;	//A/S 일정 날짜 구분
}
