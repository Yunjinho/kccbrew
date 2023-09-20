package kr.co.kccbrew.schdlMng.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.schdlMng.dao.ISchdlMngRepository;
import kr.co.kccbrew.schdlMng.model.AsAssignVo;
import kr.co.kccbrew.schdlMng.model.AsResultVo;
import kr.co.kccbrew.schdlMng.model.HolidayVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;

@Service
public class SchdlMngService implements ISchdlMngService {

	@Autowired
	private ISchdlMngRepository schdlMngRepository;
	@Autowired
	private IUserRepository userRepository;


	/*검색에 따른 휴가리스트 조회*/
	@Override
	public List<SchdlMngVo> getHolidays(int currentPage, Date startDate, Date endDate, UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userVo", userVo);

		return schdlMngRepository.selectHolidays(map);
	}

	/*스케줄 리스트 갯수 반환*/
	@Override
	public int getHolidayCount(Date startDate, Date endDate, UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userVo", userVo);

		Integer dataCount = schdlMngRepository.selectHolidayCount(map);
		int intDataCount = dataCount != null ? dataCount.intValue() : 0;
		return intDataCount;
	}

	/*회원 캘린더 조회*/
	@Override
	public List<SchdlMngVo> getCalendarSchedule(SchdlMngVo schdlMngVo) {
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
		Map<String, String> map = new HashMap<>();
		map.put("Id", userId);
		
		return schdlMngRepository.selectAssignDates(map);
	}

	/*지역코드 조회*/
	@Override
	public List<UserVo> getLocations() {
		return schdlMngRepository.selectLocations();
	}

	/*월근태현황 조회*/
	@Override
	public List<SchdlMngVo> getMechaSchedules(UserVo userVo) {
		return schdlMngRepository.selectMechaSchedules(userVo);
	}

	/*조건에 따른 아이디리스트 조회*/
	@Override
	public List<String> getIdList(UserVo userVo) {
		return schdlMngRepository.selectIdList(userVo);
	}

	/*회원아이디에 따른 스케줄맵 조회*/
	@Override
	public List<Map<String, Object>> getAllSchedules(List<String> IdList, String year, String month) {
		List<Map<String, Object>>  allSchedules = new ArrayList<>();
		
		if (IdList.size() != 0) {
		for (String id : IdList) {
			Map<String, String> parameterMap = new HashMap<>();
			parameterMap.put("Id", id);
			parameterMap.put("yr", year);
			parameterMap.put("mn", month);
			
			UserVo userVo = userRepository.getUserById(id);
			List<Map<String, Object>> holidayDates = schdlMngRepository.selectHolidayDates(parameterMap);
			List<Date> assignDates = schdlMngRepository.selectAssignDates(parameterMap);
			List<Date> resultDates = schdlMngRepository.selectResultDates(parameterMap);
			
			Map<String, Object> scheduleMap = new HashMap<>();
			scheduleMap.put("userId", id);
			scheduleMap.put("userNm", userVo.getUserNm());
			scheduleMap.put("locationCd", userVo.getLocationCd());
			scheduleMap.put("eqpmnCd", userVo.getEqpmnCd());
			
			scheduleMap.put("holidayDates", holidayDates);
			scheduleMap.put("assignDates", assignDates);
			scheduleMap.put("resultDates", resultDates);
			
			allSchedules.add(scheduleMap);
		}
		return allSchedules;
		} else {
			return null;
		}
	}

	@Override
	public HolidayVo getHoliday(String userId, Date date) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", userId);
        parameterMap.put("date", date);

		return schdlMngRepository.selecHoliday2(parameterMap);
	}

	@Override
	public AsAssignVo getAssign(String userId, Date date) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", userId);
        parameterMap.put("date", date);
		return schdlMngRepository.selectAssign(parameterMap);
	}

	@Override
	public AsResultVo getResult(String userId, Date date) {
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", userId);
        parameterMap.put("date", date);
		return schdlMngRepository.selectResult(parameterMap);
	}








}
