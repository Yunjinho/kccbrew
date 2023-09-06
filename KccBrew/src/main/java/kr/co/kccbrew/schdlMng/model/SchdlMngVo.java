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
	private String location; // sub-location이 있는경우는 sub-location 값 할당
	private String dateType; // 근무·휴무 구분
	private Date date;
	
	/*검색*/
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;
}
