package kr.co.kccbrew.sysMng.alarm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.kccbrew.sysMng.alarm.controller.EchoHandler;
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

	@Override
	public void schedulerTest() {
		log.info("AlarmService.schedulerTest");
		
		// JSON 객체 생성
		Map<String, Object> jsonMessage = new HashMap<>();
		jsonMessage.put("category", "as_progress");
		jsonMessage.put("title", "만족도평가");
		jsonMessage.put("content", "manager08님이 만족도평가(AS접수번호: 105)을(를) 하였습니다.");

		Map<String, WebSocketSession> adminIdSessions = EchoHandler.adminSessions;
		Map<String, WebSocketSession> userIdSessions = EchoHandler.userIdSessions;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(jsonMessage);

			// 관리자 웹소켓 세션에 전송
			Collection<WebSocketSession> adminSessions = adminIdSessions.values();
			Collection<WebSocketSession> userSessions = userIdSessions.values();
			for (WebSocketSession session : userSessions) {
				try {
					session.sendMessage(new TextMessage(jsonStr));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
