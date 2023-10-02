package kr.co.kccbrew.sysMng.alarm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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


public class EchoHandler extends TextWebSocketHandler{

/*	Map<String, WebSocketSession> userIdSession = new HashMap<>();
	Map<String, WebSocketSession> userTypeCdSession = new HashMap<>();*/

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
/*		String userId = getUserId(session);
		userIdSession.put("userId", session);
		
		String userTypeCd = getUserTypeCd(session);
		userTypeCdSession.put("userTypeCd", session);*/



		session.sendMessage(new TextMessage(textMessage.getPayload()));
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