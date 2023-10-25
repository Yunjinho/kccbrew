package kr.co.kccbrew.sysMng.alarm.model;

import java.sql.Date;

import lombok.Data;

@Data
public class AlarmVo {
	private int alarmSeq;
	private String causeAgent;
	private String receiverType;
	private String receiverId;
	private String alarmCategory;
	private String alarmTitle;
	private String alarmContent;
	private Date causeDate;
	private String isPosted;
}
