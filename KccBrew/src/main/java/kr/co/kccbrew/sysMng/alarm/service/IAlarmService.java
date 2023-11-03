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
	 * 회원정보(id, type)에 따른 알람 정보 조회
	 * @param Map<String, Object> map : 검색 조건
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public List<AlarmVo> getAlarmsByConditions(Map<String, Object> map);
	
	/**
	 * 회원ID에 따른 알람 정보 조회
	 * @param String userId : 사용자ID
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public List<AlarmVo> getAlarmsByUserId(String userId);
	
	/**
	 * 회원의 유형에 따른 알람 정보 조회
	 * @param String userType : 사용자타입
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public List<AlarmVo> getAlarmsByUserType(String userType);
	
	/**
	 * 1분단위로 신규메세지 실시간알림기능
	 */
	public void sendRealTimeNotifications();
	
	
	/**
	 * 모든 신규 메세지리스트 조회
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public List<AlarmVo> getNewAlarms();
	
	/**
	 * 실시간 알림 띄워주고 난 뒤 더이상 새로운 메세지가 아님을 표시
	 *  @return  List<AlarmVo>: 알람정보 리스트
	 */
	public void checkPosted(List<Integer> alarmSeqs);

}
