package kr.co.kccbrew.schdlMng.service;

import java.sql.Date; 
import java.util.List;
import java.util.Map;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.schdlMng.model.AsAssignVo;
import kr.co.kccbrew.schdlMng.model.AsResultVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;

public interface ISchdlMngService {
	
	/* 휴무리스트 조회*/
	
	public List<SchdlMngVo> getHolidays(int currentPage, Date startDate, Date endDate, UserVo userVo);
	public int getHolidayCount(Date startDate, Date endDate, UserVo userVo);
	
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
	
	/*AS배정일리스트 조회*/
	public List<Date> getAssignDates(String userId);
	
	/*지역목록 조회*/
	public List<UserVo> getLocations();
	
	/*월근태현황 조회*/
	public List<SchdlMngVo> getMechaSchedules(UserVo userVo);
	
	/*조건에 따른 회원아이디 목록 조회*/
	public List<String> getIdList(UserVo userVo);
	
	/*회원아이디에 따른 스케줄맵 조회*/
	public List<Map<String, Object>> getAllSchedules(List<String> IdList, String year, String month);
	
	/*휴가 정보 조회*/
	public HolidayVo getHoliday(String userId, Date date);
	
	/*배정 정보 조회*/
	public AsAssignVo getAssign(String userId, Date date);
	
	/*휴가 정보 조회*/
	public AsResultVo getResult(String userId, Date date);
	
}
