package kr.co.kccbrew.schdlMng.dao;

import java.sql.Date; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.schdlMng.model.AsAssignVo;
import kr.co.kccbrew.schdlMng.model.AsResultVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;

@Repository
@Mapper
public interface ISchdlMngRepository {

	/*휴가 리스트 조회*/
	public List<SchdlMngVo> selectHolidays(Map<String, Object> map);
	public Integer selectHolidayCount(Map<String, Object> map);

	/*회원 캘린더 조회 */
	public List<SchdlMngVo> selectCalendarSchedule(SchdlMngVo schdlMngVo);

	/*휴가취소*/
	public void cancelHoliday(Integer holidaySeq);

	/*사용한 휴가일수*/
	public int selectUsedHoliday(String userId);

	/*휴가등록*/
	public void insertHoliday(HolidayVo holiday);

	/*지역코드 목록*/
	public List<UserVo> selectLocations();

	/*월근태현황 목록*/
	public List<SchdlMngVo> selectMechaSchedules(UserVo userVo);

	/*조건에 따른 회원아이디 목록 조회*/
	public List<String> selectIdList(UserVo userVo);

	/*아이디와 날짜에 따른 휴무날짜리스트 조회*/
	public List<Map<String, Object>> selectHolidayDates(Map map);

	/*아이디와 날짜에 따른 배정날짜리스트 조회*/
	public List<SchdlMngVo> selectAssignDates(Map map);

	/*아이디와 날짜에 따른 근무날짜리스트 조회*/
	public List<SchdlMngVo> selectResultDates(Map map);
	
	/*관리자가 처리해야 할 AS신청 날짜리스트 조회*/
	public List<SchdlMngVo> selectAsRegDates(Map map);
	
	/*휴일 정보 조회*/
	public List<HolidayVo> selectHoliday(Map map);
	
	/*휴일 정보 조회*/
	public List<HolidayVo> selectHoliday2(Map map);
	
	/*배정 정보 조회*/
	public List<AsAssignVo> selectAssign(Map map);
	
	/*휴가 정보 조회*/
	public List<AsResultVo> selectResult(Map map);
	
	public List<SchdlMngVo> selectAllHolidays(Map<String, Object> map);
}
