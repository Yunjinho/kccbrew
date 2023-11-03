package kr.co.kccbrew.sysMng.alarm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.kccbrew.batch.JobDemo;
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
		map.put("receiverType", userType);
		return alarmRepository.selectAlarmsByConditions(map);
	}

	@Override
	public void sendRealTimeNotifications() {
		log.info("AlarmService.schedulerTest");
		List<AlarmVo> newAlarms = getNewAlarms();

		Map<String, WebSocketSession> adminIdSessions = EchoHandler.adminSessions;
		Map<String, WebSocketSession> userIdSessions = EchoHandler.userIdSessions;

		Map<String, List<AlarmVo>> alarmsByUserId = new HashMap<>();
		List<AlarmVo> alarmsForAdmin = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStr = null;

		List<Integer> alarmSeqs = new ArrayList<>();

		try {
			Set<String> userIds = userIdSessions.keySet();
			for (String userId : userIds) {
				alarmsByUserId.put(userId, new ArrayList<AlarmVo>());
			}

			for(AlarmVo newAlarm : newAlarms) {
				alarmSeqs.add(newAlarm.getAlarmSeq());

				// 받는사용자id에 따른 알림리스트 생성
				if (userIdSessions.size() != 0 && !userIdSessions.isEmpty()) {
					if (newAlarm.getReceiverId() != null && !newAlarm.getReceiverId().isEmpty()) {
						String[] receiverIdArray = newAlarm.getReceiverId().split(",");
						for (int i = 0; i < receiverIdArray.length; i++) {
							String receiverId = receiverIdArray[i];
							if(userIdSessions.containsKey(receiverId) == true) {
								List<AlarmVo> alarms = alarmsByUserId.get(receiverId);
								alarms.add(newAlarm);
							}
						}
					}

				}
				// 권한에 따른 알림리스트생성
				if (newAlarm.getReceiverType() != null && !newAlarm.getReceiverType().isEmpty()) {
					String[] receiverTypeArray = newAlarm.getReceiverType().split(",");
					for (int i = 0; i < receiverTypeArray.length; i++) {
						String receiverType = receiverTypeArray[i];
						// 관리자인 경우의 알림리스트 생성
						if(receiverType.equals("01")) {
							alarmsForAdmin.add(newAlarm);
						}
					}
				}
			}

			// 받는사용자id에 따른 알람리스트 발송
			for (String userId : userIds) {
				if (alarmsByUserId.size() != 0 && !alarmsByUserId.isEmpty()) {
					List<AlarmVo> alarms = alarmsByUserId.get(userId);
					jsonStr = objectMapper.writeValueAsString(alarms);
					userIdSessions.get(userId).sendMessage(new TextMessage(jsonStr));
				}
			}

			// 관리자권한 알림전송
			if (adminIdSessions.size() != 0 && !adminIdSessions.isEmpty()) {
				Collection<WebSocketSession> sessions = adminIdSessions.values();
				for (WebSocketSession session : sessions) {
					try {
						if (alarmsForAdmin.size() != 0 && !alarmsForAdmin.isEmpty()) {
							jsonStr = objectMapper.writeValueAsString(alarmsForAdmin);
							session.sendMessage(new TextMessage(jsonStr));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		if (alarmSeqs.size() !=0 && !alarmSeqs.isEmpty()) {
			checkPosted(alarmSeqs);
		}
	}


	@Override
	public List<AlarmVo> getNewAlarms() {
		return alarmRepository.selectNewAlarms();
	}


	@Override
	public void checkPosted(List<Integer> alarmSeqs) {
		alarmRepository.updatePosted(alarmSeqs);
	}


	
	








}
