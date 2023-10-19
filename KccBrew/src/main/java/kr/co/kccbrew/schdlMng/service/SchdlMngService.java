package kr.co.kccbrew.schdlMng.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	
	public List<SchdlMngVo> getAllHolidays(int page, Date startSqlDate, Date endSqlDate, UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("firstRowNum", page*10-9);
		map.put("lastRowNum", page*10);
		map.put("startDate", startSqlDate);
		map.put("endDate", endSqlDate);
		map.put("userVo", userVo);

		return schdlMngRepository.selectAllHolidays(map);
	}

	/*스케줄 리스트 갯수 반환*/
	@Override
	public int getHolidayCount(Date startDate, Date endDate, UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("userVo", userVo);

		System.out.println("SchdlMngService.getHolidayCount");
		System.out.println("userVo: " + userVo);

		Integer dataCount = schdlMngRepository.selectHolidayCount(map);
		int intDataCount = dataCount != null ? dataCount.intValue() : 0;
		return intDataCount;
	}

	/*회원 캘린더 조회*/
	@Override
	public List<SchdlMngVo> getCalendarSchedule(SchdlMngVo schdlMngVo) {
		return schdlMngRepository.selectCalendarSchedule(schdlMngVo);
	}

	/*휴일취소*/
	@Override
	public void cancelHoliday(Integer holidaySeq) {
		schdlMngRepository.cancelHoliday(holidaySeq);
	}


	/*잔여 휴가일수 조회*/
	@Override
	public int getUsedHoliday(UserVo userVo) {
		int usedHolidays = 0;

		// 이번년도의 마지막 날
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		calendar.set(currentYear + 1, Calendar.JANUARY, 1);
		calendar.add(Calendar.DATE, -1);
		java.util.Date lastDayOfYear = calendar.getTime();

		// 이번년도의 첫날
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		java.util.Date firstDayOfYear = calendar.getTime();

		// java.util.Date를 java.sql.Date로 변환
		Date sqlLastDayOfYear = new Date(lastDayOfYear.getTime());
		Date sqlFirstDayOfYear = new Date(firstDayOfYear.getTime());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", sqlFirstDayOfYear);
		map.put("endDate", sqlLastDayOfYear);
		map.put("userVo", userVo);
		map.put("actualUse", "Y");

		List<SchdlMngVo> holidays = schdlMngRepository.selectHolidays(map);

		for(SchdlMngVo holiday : holidays) {
			long diffInMilliseconds = holiday.getEndDate().getTime() - holiday.getStartDate().getTime();
			int diffInDays = Math.round(diffInMilliseconds / (24 * 60 * 60 * 1000)) + 1;
			usedHolidays += diffInDays;
		}

		return usedHolidays;
	}


	/*휴가등록*/
	@Override
	public void addHoliday(HolidayVo holiday) {
		schdlMngRepository.insertHoliday(holiday);
	}


	/*AS배정일 조회*/
	@Override
	public List<SchdlMngVo> getAssignDates(String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("id", userId);

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
	public List<Map<String, Object>> getAllSchedules(List<String> IdList, String year, String month,String role) {
		List<Map<String, Object>>  allSchedules = new ArrayList<>();

		if (IdList == null) {
			Map<String, String> parameterMap = new HashMap<>();
			parameterMap.put("yr", year);
			parameterMap.put("mn", month);

			List<SchdlMngVo> asRegDates = schdlMngRepository.selectAsRegDates(parameterMap);
			List<SchdlMngVo> resultDates = schdlMngRepository.selectResultDates(parameterMap);
			Map<String, Object> scheduleMap = new HashMap<>();

			scheduleMap.put("asRegDates", asRegDates);
			scheduleMap.put("resultDates", resultDates);
			allSchedules.add(scheduleMap);

			return allSchedules;
		}

		for (String id : IdList) {
			Map<String, String> parameterMap = new HashMap<>();
			parameterMap.put("id", id);
			parameterMap.put("yr", year);
			parameterMap.put("mn", month);
			parameterMap.put("userTypeCd",role);

			UserVo userVo = userRepository.getUserById(id);
			// 실제사용이 Y인 휴가리스트 조회
			List<Map<String, Object>> holidayDates = schdlMngRepository.selectHolidayDates(parameterMap);
			List<SchdlMngVo> assignDates = schdlMngRepository.selectAssignDates(parameterMap);
			List<SchdlMngVo> resultDates = schdlMngRepository.selectResultDates(parameterMap);

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
	}


	/*	@Override
	public List<HolidayVo> getHolidaysByIdAndDate(String userId, Date date) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userId);
		parameterMap.put("date", date);

		return schdlMngRepository.selecHoliday2(parameterMap);
	}*/

	/*휴일조회*/
	@Override
	public List<HolidayVo> getHolidays(String userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		return schdlMngRepository.selectHoliday(map);
	}

	/*휴일조회(실제사용: Y)*/
	@Override
	public List<HolidayVo> getHolidays(String userId, Date date) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userId);
		parameterMap.put("date", date);
		parameterMap.put("actualUse", "Y");
		return schdlMngRepository.selectHoliday2(parameterMap);
	}

	@Override
	public List<AsAssignVo> getAssigns(String userId, Date date) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userId);
		parameterMap.put("date", date);
		return schdlMngRepository.selectAssign(parameterMap);
	}

	@Override
	public List<AsResultVo> getResults(String userId, Date date) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("userId", userId);
		parameterMap.put("date", date);
		return schdlMngRepository.selectResult(parameterMap);
	}



}
