package kr.co.kccbrew.sysMng.alarm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.kccbrew.comm.security.model.UserVo;

/**
 * @ClassNmae : EchoHandler
 * @Decription : AS절차에 따른 사용자 알람수신
 * 
 * @   수정일               			수정자             		수정내용
 * ============      ==============     ==============
 * 2023-09-28							이세은						최초생성
 * 
 * @author LEESEEION
 * @version 1.0
 */


public class EchoHandler extends TextWebSocketHandler implements WebSocketHandler{

	Map<String, WebSocketSession> userIdSessions = new HashMap<>();
	Map<String, WebSocketSession> userTypeCdSessions = new HashMap<>();
	HttpSession session;



	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

	}



	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
		System.out.println("Received JSON: " + textMessage.getPayload());
		System.out.println("userIdSessions: " + userIdSessions);

		// JSON 문자열을 파싱하여 JavaScript 객체로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonMap = objectMapper.readValue(textMessage.getPayload(), new TypeReference<Map<String, Object>>() {});

		System.out.println("jsonMap:" + jsonMap);
		
		// userId 필드가 있는지 확인
		if (jsonMap.containsKey("userId")) {
			String userId = (String) jsonMap.get("userId");
			userIdSessions.put(userId, session);
			System.out.println("userId: " + userId);
		}

		// userTypeCd 필드가 있는지 확인
/*		if (jsonMap.containsKey("userTypeCd")) {
			String userTypeCd = (String) jsonMap.get("userTypeCd");
			System.out.println("userTypeCd: " + userTypeCd);
		}*/



		session.sendMessage(new TextMessage(textMessage.getPayload()));

		System.out.println("session: " + session);

		Map<String, Object> webSocketSession = session.getAttributes();
		System.out.println("webSocketSession: " + webSocketSession);

		String websocketId = session.getId();
		System.out.println("websocketId: " + websocketId);

	}



	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.afterConnectionClosed(session, status);
	}



	/*사용자ID에 따른 session Map*/
	private String getUserId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVo userVo = (UserVo) httpSession.get("user");
		return userVo.getUserId();

	}

	/*사용자타입에 따른 session Map*/
	private String getUserTypeCd(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		UserVo userVo = (UserVo) httpSession.get("user");
		return userVo.getUserTypeCd();
	}


}