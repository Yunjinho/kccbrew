package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import kr.co.kccbrew.schdlMng.validation.LegalHoliday;
import lombok.Data;

@Data
public class HolidayVo {
	private int rowNum;
	private Integer holidaySeq;
	private String groupCodeDetailId; // 사용자 유형: 점주 또는 기사
	private String userId;
	@LegalHoliday
	private Date startDate;
	@LegalHoliday
	private Date endDate;
	private Date appDate;
	private String actualUse;
	
}
