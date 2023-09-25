package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class AsAssignVo {
	private Integer AsAssignSeq;
	private Integer AsInfoSeq;
	private Date visitDate;
	private Date confirmDate;
	private String userId;
	private Date modDate;
	private String modUser;
	private Date regDate;
	private String regUser;
	private String reAssign;
	private String rejectContentMecha;

}
