package kr.co.kccbrew.comm.chat.service;

import java.util.List;

import kr.co.kccbrew.comm.chat.model.ChatDto;

public interface IChatService {
	// Service
	void userChatCreate(ChatDto chatDto);

	void adminChatCreate(ChatDto chatDto);

	void chatDelete(String id);

	List<ChatDto> getChatLog(ChatDto chatDto);
}
