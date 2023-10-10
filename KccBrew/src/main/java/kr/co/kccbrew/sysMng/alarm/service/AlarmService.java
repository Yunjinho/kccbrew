package kr.co.kccbrew.sysMng.alarm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.alarm.dao.IAlarmRepository;
import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;

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
		return alarmRepository.selectAlarmsByConditions(map);
	}





}
