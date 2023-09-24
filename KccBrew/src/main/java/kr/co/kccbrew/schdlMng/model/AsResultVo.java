package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class AsResultVo {
	private Integer asResultSeq;
	private Integer asAssignSeq;
	private String resultDetail;
	private Integer storeManagerFeedBack;
	private Date resultDate;
	private Integer fileSeq;
	private double asPrice;
	private String modUser;
	private Date modDate;
	private String reApply;
}
