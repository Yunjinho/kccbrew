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
	/**
	 * @return 사용자 채팅작성
	 */
	@Override
	public void userChatCreate(ChatDto chatDto) {		
		chatRepository.userChatCreate(chatDto);
	}
	/**
	 * @return 관리자 채팅 작성
	 */
	@Override
	public void adminChatCreate(ChatDto chatDto) {
		chatRepository.adminChatCreate(chatDto);
	}
	/**
	 * @return 채팅방 나가기
	 */
	@Override
	public void chatDelete(String id) {
		chatRepository.chatDelete(id);		
	}
	/**
	 * @return uuid에 따른 관리자채팅방, 사용자채팅방
	 */
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
