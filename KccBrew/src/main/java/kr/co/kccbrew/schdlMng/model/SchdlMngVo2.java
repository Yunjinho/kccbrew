package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SchdlMngVo2 {
	private int rowNum;
	private String scheduleType;
	private Integer scheduleId;
	private String userType;
	private String userId;
	private String userName;
	private String userPhoneNumber;
	private Integer storeId;
	private String storeName;
	private String storePhoneNumber;
	private String storeLocation;
	private String storeSubLocation;
	private Date scheduleDate;
	
	/*검색*/
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;
}
