package kr.co.kccbrew.sysMng.alarm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.alarm.dao.IAlarmRepository;
import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlarmService implements IAlarmService{

	@Autowired
	private IAlarmRepository alarmRepository;

	@Override
	public void addAlarm(AlarmVo alarmVo) {
		alarmRepository.insertAlarm(alarmVo);
	}

	@Override
	public List<AlarmVo> getAlarmsByConditions(Map<String, Object> map) {
		String userId = (String) map.get("receiverId");
		String userType =  (String) map.get("receiverType");
		List<AlarmVo> alarmsByUserId = getAlarmsByUserId(userId);
		List<AlarmVo> alarmsByUserType = getAlarmsByUserType(userType);
		
		List<AlarmVo> alarms = new ArrayList<>();
		alarms.addAll(alarmsByUserId);
		alarms.addAll(alarmsByUserType);
		
		log.info("AlarmService.getAlarmsByConditions");
		log.info("alarms: " + alarms) ;
		
		return alarms;
	}

	@Override
	public List<AlarmVo> getAlarmsByUserId(String userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("receiverId", userId);
		return alarmRepository.selectAlarmsByConditions(map);
	}

	@Override
	public List<AlarmVo> getAlarmsByUserType(String userType) {
		Map<String, Object> map = new HashMap<>();
		map.put("receiverId", "all");
		map.put("receiverType", userType);
		return alarmRepository.selectAlarmsByConditions(map);
	}
	
	
	





}
