package kr.co.kccbrew.schdlMng.dao;

import java.sql.Date; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

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

	/*AS배정날짜 목록*/
	public List<Date> selectAssignDates(String userId);
	
}
