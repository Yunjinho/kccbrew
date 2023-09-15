package kr.co.kccbrew.schdlMng.service;

import java.sql.Date; 
import java.util.List;

import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;

public interface ISchdlMngService {
	
	/*스케줄 리스트 조회*/
	public List<SchdlMngVo> getSchedules2(int currentPage, SchdlMngVo searchContent);
	public int getSchedule2Count(SchdlMngVo searchContent);
	
	/*회원 캘린더 조회*/
	public List<SchdlMngVo> getCalendarSchedule(SchdlMngVo schdlMngVo);
	
	/*휴일 조회*/
	public List<HolidayVo> getHoliday(String userId);
	
	/*휴가취소*/
	public void cancelHoliday(Integer holidaySeq);
	
	/*휴가일수*/
	public int getUsedHoliday(String userId);
	
	/*휴가등록*/
	public void addHoliday(HolidayVo holiday);
	
	/*AS배정일 조회*/
	public List<Date> getAssignDates(String userId);
	
}
