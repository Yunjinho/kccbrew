package kr.co.kccbrew.schdlMng.dao;

import java.sql.Date; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;

@Repository
@Mapper
public interface ISchdlMngRepository {

	/*스케줄 리스트 조회*/
	public List<SchdlMngVo> selectSchedules2(Map<String, Object> map);
	public Integer selectSchedule2Count(Map<String, Object> map);

	/*회원 캘린더 조회 */
	public List<SchdlMngVo> selectCalendarSchedule(SchdlMngVo schdlMngVo);

	/*휴일 정보 조회*/
	public List<HolidayVo> selectHoliday(String userId);

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
	
	/*아이디에 따른 휴무날짜 조회*/
	public List<Map<String, Object>> selectHolidayDates(String userId);
	
	/*아이디에 따른 배정날짜 조회*/
	public List<Date> selectAssignDates(String userId);
	
	/*아이디에 따른 근무날짜 조회*/
	public List<Date> selectResultDates(String userId);
}
