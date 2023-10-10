package kr.co.kccbrew.comm.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.chat.dao.IChatRepository;
import kr.co.kccbrew.comm.chat.model.ChatDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatService implements IChatService{

	@Autowired
	private final IChatRepository chatRepository;
	
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
		String uuid = chatDto.getUuid();
		if(uuid != null) {
			return chatRepository.getLogAdmin(chatDto.getUuid());
		}
		else {
			return chatRepository.getLog(chatDto.getId());
		}
	}


}
