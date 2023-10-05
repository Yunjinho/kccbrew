package kr.co.kccbrew.sysMng.logMng.model;


import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class LogMngVo {
	private Integer logSeq;
	private Date date; 
	private String uri;
	private String view;
	private String userId;
	private String userType;
	private String ip;
	private String statusCode;

	/*검색용*/
	private String startDate;
	private String endDate;
}
