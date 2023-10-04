package kr.co.kccbrew.comm.util;

import java.util.List;

public interface IChatService {
	// Service
	void userChatCreate(ChatDto chatDto);

	void adminChatCreate(ChatDto chatDto);

	void chatDelete(String id);

	List<ChatDto> getChatLog(ChatDto chatDto);
}
