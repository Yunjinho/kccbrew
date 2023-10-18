package kr.co.kccbrew.sysMng.logMng.model;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.kccbrew.comm.util.DateFormat;
import lombok.Data;


@Data
public class LogMngVo {
	@Autowired
	private DateFormat dateFormat;


	private Integer logSeq;
	private Timestamp date; 
	private String uri;
	private String view;
	private String userId;
	private String userType;
	private String ip;
	private String statusCode;

	/*검색용*/
	private Date startDate;
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setStartDate(String startDate) {
		setStartDate(dateFormat.stringToSqlDate(startDate));
	}

	private Date endDate;
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setEndDate(String endDate) {
		setEndDate(dateFormat.stringToSqlDate(endDate));
	}
}
