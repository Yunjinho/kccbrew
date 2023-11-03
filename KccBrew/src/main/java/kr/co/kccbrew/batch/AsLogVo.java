package kr.co.kccbrew.batch;

import java.sql.Date;

import lombok.Data;

@Data
public class AsLogVo {
	Integer asLogSeq;
	String asInfoSeq;
	String asStatus;
	String asProcess;
	String asPerformer;
	Date asProcessDate;
	String storeSeq;
	String mechanicId;

	/*as상태가 01(접수중)인 경우*/
	Date wishingEndDate;
	/*as상태가 02(AS접수반려)인 경우*/
	String rejectContentStr;
	Date modDttm;
	/*as상태가 03(접수완료)인 경우*/
	Date visitDttm;
	/*as상태가 04(완료)인 경우*/
	Date resultDttm;
}