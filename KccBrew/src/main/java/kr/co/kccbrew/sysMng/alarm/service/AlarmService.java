package kr.co.kccbrew.sysMng.alarm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.alarm.dao.IAlarmRepository;
import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("alarmService")
public class AlarmService implements IAlarmService{

	@Autowired
	private IAlarmRepository alarmRepository;

	@Override
	public void addAlarm(AlarmVo alarmVo) {
		alarmRepository.insertAlarm(alarmVo);
	}

	/*	@Override
	public List<AlarmVo> getAlarms() {
		log.info("alarmList: " + alarmRepository.selectAlarms());
		return alarmRepository.selectAlarms();
	}*/

	/*	@Override*/
	/*	public List<AlarmVo> getAlarmsByConditions(Map<String, Object> map) {
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
	}*/


	@Override
	public void getAlarmsByConditions() {
		log.info("AlarmService.getAlarmsByConditions");
/*		log.info("AlarmService.getAlarmsByConditions");

		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return null;
		}

		String userId = null;
		userId = authentication.getName();
		log.info("userId: " + userId);

		GrantedAuthority role = null;
		String userType = null;
		for ( GrantedAuthority authority : authentication.getAuthorities()) {
			role = authority;
			log.info("role: " + role);
		}

		if(role.equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			userType = "관리자";
		} else if (role.equals(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			userType = "점주";
		} else if(role.equals(new SimpleGrantedAuthority("ROLE_MECHA")))  {
			userType = "수리기사";
		} else {
			log.info("권한이 없는 사용자입니다!");
		}

		List<AlarmVo> alarmsByUserId = getAlarmsByUserId(userId);
		List<AlarmVo> alarmsByUserType = getAlarmsByUserType(userType);

		List<AlarmVo> alarms = new ArrayList<>();
		alarms.addAll(alarmsByUserId);
		alarms.addAll(alarmsByUserType);

		log.info("alarms: " + alarms) ;

		return alarms;*/
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
		map.put("receiverType", userType);
		return alarmRepository.selectAlarmsByConditions(map);
	}








}
