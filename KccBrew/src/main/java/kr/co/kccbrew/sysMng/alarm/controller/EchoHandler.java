package kr.co.kccbrew.sysMng.alarm.controller;

import java.io.IOException;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;
import kr.co.kccbrew.sysMng.alarm.service.IAlarmService;

/**
 * @ClassNmae : EchoHandler
 * @Decription : AS절차에 따른 사용자 알람수신
 * 
 * @   수정일               			수정자             					수정내용
 * ============      ==============     ========================
 * 2023-09-28							이세은									최초생성
 * 2023-10-03							이세은						휴가신청에 따른 알람 구현
 * 
 * @author LEESEEION
 * @version 1.0
 */


public class EchoHandler extends TextWebSocketHandler implements WebSocketHandler{

	@Autowired
	private IAlarmService alarmService;

	Map<String, WebSocketSession> userIdSessions = new HashMap<>();
	Map<String, WebSocketSession> adminSessions = new HashMap<>();
	Map<String, WebSocketSession> managerSessions = new HashMap<>();
	Map<String, WebSocketSession> mechaSessions = new HashMap<>();

	String userId = null;
	String userType = null;


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}



	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
		String sendMessege = "test";

		// JSON타입 메세지 파싱
		System.out.println("Received JSON: " + textMessage.getPayload());
		System.out.println("userIdSessions: " + userIdSessions);

		String messageJson = textMessage.getPayload();
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> messageMap = objectMapper.readValue(messageJson, Map.class);

		// 사용자 정보 저장
		setUserInfo(session, messageMap);

		// 행위에 따른 알람 메세지 전송
		if (messageMap.containsKey("title")) {
			String title = (String) messageMap.get("title");
			switch(title){
			case "holiday" : 
				holidayMessage(messageMap);
				break;
			}
		}
	}


	/*사용자 정보(ID, 권한) 저장*/
	public 	void setUserInfo(WebSocketSession session, Map<String, Object> messageMap) {

		if (messageMap.containsKey("userId")) {
			userId = (String) messageMap.get("userId");
			userIdSessions.put(userId, session);
			System.out.println("userIdSessions:" + userIdSessions);
		}

		if (messageMap.containsKey("userType")) {
			String userType = (String) messageMap.get("userType");

			switch(userType){
			case "[ROLE_ADMIN]" : 
				adminSessions.put(userId, session); 
				System.out.println("adminSessions: " + adminSessions);
				break;
			case "[ROLE_MANAGER]" : 
				managerSessions.put(userId, session); 
				System.out.println("managerSessions: " + managerSessions);
				break;  
			default :
				mechaSessions.put(userId, session);
				System.out.println("mechaSessions: " + mechaSessions);
			}

		}
	}


	/*휴가신청 시 관리자에게 알람메세지 전송 및 DB저장*/
	public void holidayMessage(Map<String, Object> messageMap) {
		String userType = getUserType((String) messageMap.get("userType"));
		String userId = (String) messageMap.get("userId");
		String startDate = (String) messageMap.get("startDate");
		String endDate = (String) messageMap.get("endDate");

		String message = userType + "(" + userId + ")님이 휴가" + "(" + startDate + "~" + endDate + ")" + "를 신청하였습니다.";

		// JSON 객체 생성
		Map<String, Object> jsonMessage = new HashMap<>();
		jsonMessage.put("category", "alarm");
		jsonMessage.put("title", "휴가신청");
		jsonMessage.put("content", message);

		// AlarmVo객체에 값 대입
		AlarmVo alarmVo = new AlarmVo();
		alarmVo.setCauseAgent(userId);
		alarmVo.setReceiverType("관리자");
		alarmVo.setAlarmTitle("휴가신청");
		alarmVo.setAlarmContent(message);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		alarmVo.setCauseDate(sqlDate);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(jsonMessage);

			// 관리자 웹소켓 세션에 전송
			Collection<WebSocketSession> sessions = adminSessions.values();
			for (WebSocketSession session : sessions) {
				try {
					session.sendMessage(new TextMessage(jsonStr));
					alarmService.addAlarm(alarmVo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*사용자의 타입을 한글로 반환*/
	public String getUserType(String userType) {
		switch(userType){
		case "[ROLE_ADMIN]" : 
			return "관리자";
		case "[ROLE_MANAGER]" : 
			return "점주";
		default :
			return "수리기사";
		}

	}



	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}


}