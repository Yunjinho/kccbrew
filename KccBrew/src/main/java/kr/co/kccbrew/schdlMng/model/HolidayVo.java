package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class HolidayVo {
	private Integer holidaySeq;
	private String groupCodeDetailId; // 사용자 유형: 점주 또는 기사
	private String userId;
	private Date startDate;
	private Date endDate;
	private Date appDate;
	private String actualUse;
	
}
