package kr.co.kccbrew.comm.main.model;

import java.util.Date;

import lombok.Data;

@Data
public class MainPageVo {
	int asAssignNum; 		//배정 번호
	int asInfoNum;  		//접수 번호
	Date visitDate;  		//방문 예정일 
	Date confirmDate; 		//접수일
	String mechanicId; 		//수리기사 아이디 
	String managerId;  		//점주 아이디
	String userId; 			//사용자 아이디
	String userName; 		//사용자 이름
	String userTelNo; 		//사용자 전화번호
	String mechanicName; 	//수리기사 이름
	String managerName; 	//점주 이름
	String approveStatus;  	//회원 승인 여부
	String asStatus; 		//as 상태 코드
	String locationCode; 	//점포 지역 코드
	String machineCode; 	//장비 코드
	String userType; 		//사용자 구분 코드
}
