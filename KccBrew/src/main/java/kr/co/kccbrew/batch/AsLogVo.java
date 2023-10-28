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


}