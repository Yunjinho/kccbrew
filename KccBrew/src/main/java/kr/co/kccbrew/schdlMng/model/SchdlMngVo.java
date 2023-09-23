package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class SchdlMngVo {
	private Integer  rowNumber;
	private String scheduleType;
	private Integer scheduleId;
	
	private String userType;
	private String userId;
	private String userName;
	private String userPhoneNumber;
	private String equipmentCd;
	
	private Integer storeId;
	private String storeName;
	private String storePhoneNumber;
	private String storeLocation;
	private String storeSubLocation;
	
	private Date appDate;
	private Date startDate;
	private Date endDate;
	private String actualUse;
	
	private Date visitDate;
	private Date resultDate;
	
	private String locationCd;
	
	/*검색*/
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;

	private String searchKeword;
}
