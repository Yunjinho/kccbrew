package kr.co.kccbrew.schdlMng.model;

import java.sql.Date; 

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SchdlMngVo {
	private String userId;
	private String userType;
	private String userName;
	private String userPhoneNumber;
	private String location; 
	private String sublocation;
	private String scheduleType; // 근무·휴무 구분
	private Date scheduleDate;
	
	/*검색*/
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;
}
