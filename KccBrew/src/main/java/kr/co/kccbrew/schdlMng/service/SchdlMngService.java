package kr.co.kccbrew.schdlMng.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.schdlMng.dao.ISchdlMngRepository;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo2;

@Service
public class SchdlMngService implements ISchdlMngService {

	@Autowired
	private ISchdlMngRepository schdlMngRepository;

	@Override
	public List<SchdlMngVo> getMechaSchedules(int currentPage, SchdlMngVo searchContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchContent", searchContent);
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);

		return schdlMngRepository.selectMechaSchedules(map);
	}

	/*	@Override
	public int getMechaScheduleCount(SchdlMngVo serchContent) {
		return 0;
	}*/


	/*검색에 따른 휴가리스트 조회*/
	@Override
	public List<SchdlMngVo2> getSchedules2(int currentPage, SchdlMngVo2 searchContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchContent", searchContent);
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);

		return schdlMngRepository.selectSchedules2(map);
	}

	/*스케줄 리스트 갯수 반환*/
	@Override
	public int getSchedule2Count(SchdlMngVo2 searchContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchContent", searchContent);

		Integer dataCount = schdlMngRepository.selectSchedule2Count(map);
		int intDataCount = dataCount != null ? dataCount.intValue() : 0;
		return intDataCount;
	}

	/*회원 캘린더 조회*/
	@Override
	public List<SchdlMngVo2> getCalendarSchedule(SchdlMngVo2 schdlMngVo) {
		return schdlMngRepository.selectCalendarSchedule(schdlMngVo);
	}


	/*휴일조회*/
	@Override
	public List<HolidayVo> getHoliday(String userId) {
		return schdlMngRepository.selectHoliday(userId);
	}

	/*휴일취소*/
	@Override
	public void cancelHoliday(Integer holidaySeq) {
		schdlMngRepository.cancelHoliday(holidaySeq);		
	}

	
	/*휴가일수*/
	@Override
	public int getUsedHoliday(String userId) {
		return schdlMngRepository.selectUsedHoliday(userId);
	}

	/*휴가등록*/
	@Override
	public void addHoliday(HolidayVo holiday) {
		schdlMngRepository.insertHoliday(holiday);
	}

	
	/*AS배정일 조회*/
	@Override
	public List<Date> getAssignDates(String userId) {
		return schdlMngRepository.selectAssignDates(userId);
	}
	
	

	

}
