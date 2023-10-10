package kr.co.kccbrew.sysMng.alarm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;

@Mapper
@Repository
public interface IAlarmRepository {
	public void insertAlarm(AlarmVo alarmVo);
	public List<AlarmVo> selectAlarmsByConditions(Map<String, Object> map);
	
}
