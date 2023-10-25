package kr.co.kccbrew.sysMng.alarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;

@Mapper
@Repository
public interface IAlarmRepository {
	
	/**
	 * 알림 등록
	 * @param AlarmVo alarmVo : 알림객체
	 */
	public void insertAlarm(AlarmVo alarmVo);
	
	/**
	 * 회원ID, 권한에 따른 알림리스트 조회
	 * @param Map<String, Object> map : 사용자타입
	 *  @return  List<AlarmVo> selectAlarmsByConditions: 알림리스트
	 */
	public List<AlarmVo> selectAlarmsByConditions(Map<String, Object> map);
	
	/**
	 * 신규알림리스트 조회
	 *  @return  List<AlarmVo>: 신규알림리스트
	 */
	public List<AlarmVo> selectNewAlarms();
	
	/**
	 * 더이상 새로운 알림이 아님을 표시
	 * @param List<Integer> alarmSeqs : 표시해줄 알림ID
	 */
	public void updatePosted(List<Integer> alarmSeqs);
	
}
