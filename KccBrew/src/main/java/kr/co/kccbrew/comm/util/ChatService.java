package kr.co.kccbrew.comm.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService implements IChatService{

	@Autowired
	private IChatRepository chatRepository;
	
	@Override
	public void userChatCreate(ChatDto chatDto) {		
		chatRepository.userChatCreate(chatDto);
	}
	
	@Override
	public void adminChatCreate(ChatDto chatDto) {
		chatRepository.adminChatCreate(chatDto);
	}

	@Override
	public void chatDelete(String id) {
		chatRepository.chatDelete(id);		
	}

	@Override
	public List<ChatDto> getChatLog(ChatDto chatDto) {
		return chatRepository.getChatLog(chatDto);
	}


}
