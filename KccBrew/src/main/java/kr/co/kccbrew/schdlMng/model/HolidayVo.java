package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import kr.co.kccbrew.schdlMng.validator.LegalHoliday;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class HolidayVo {
	private int rowNum;
	private Integer holidaySeq;
	private String groupCodeDetailId; // 사용자 유형: 점주 또는 기사
	private String userId;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	private Date appDate;
	private String actualUse;
	private String storeSeq;
	
	/***유효성검증***/
	@PositiveOrZero
	private int remainingDays;
	@LegalHoliday
	private Map<String, Date> period;
	/***유효성검증***/

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public void setHolidaySeq(Integer holidaySeq) {
		this.holidaySeq = holidaySeq;
	}
	public void setGroupCodeDetailId(String groupCodeDetailId) {
		this.groupCodeDetailId = groupCodeDetailId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		updatePeriod("startDate", startDate);
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		updatePeriod("endDate", endDate);
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	public void setActualUse(String actualUse) {
		this.actualUse = actualUse;
	}
	public void setRemainingDays(int remainingDays) {
		this.remainingDays = remainingDays;
	}
	public void setPeriod(Map<String, Date> period) {
		this.period = period;
	}

	public void updatePeriod(String dateType, Date date) {
		if (period != null) {
			period.put(dateType, date);
		} else {
			period = new HashMap<>();
			period.put(dateType, date);
		}
	}
	public void setStoreSeq(String storeSeq) {
		this.storeSeq = storeSeq;
	}

}
