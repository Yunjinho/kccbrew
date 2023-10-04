package kr.co.kccbrew.sysMng.alarm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;

@Service
public interface IAlarmService {
	
	/**
	 * 알람행위 발생 시 DB에 정보 저장
	 * @param AlarmVo alarmVo : 알람 정보
	 */
	public void addAlarm(AlarmVo alarmVo);
	
	/**
	 * 회원ID에 따른 알람 정보 조회
	 * @param Map<String, Object> map : 검색 조건
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public List<AlarmVo> getAlarmsByConditions(Map<String, Object> map);

}
