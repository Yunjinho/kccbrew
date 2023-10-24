package kr.co.kccbrew.comm.chat.service;

import java.util.List;

import kr.co.kccbrew.comm.chat.model.ChatDto;

public interface IChatService {
	/**
	 * @return 사용자 채팅작성
	 */
	void userChatCreate(ChatDto chatDto);
	/**
	 * @return 관리자 채팅 작성
	 */
	void adminChatCreate(ChatDto chatDto);
	/**
	 * @return 채팅방 나가기
	 */
	void chatDelete(String id);
	/**
	 * @return 채팅로그
	 */
	List<ChatDto> getChatLog(ChatDto chatDto);
	
	 ChatDto getUser(String uuid);
}
