package kr.co.kccbrew.comm.util;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IChatRepository {
	
	@Autowired
	SqlSession ss;
	
	public void userChatCreate(ChatDto chatDto) {
		ss.insert("chat.userChatCreate", chatDto);
	}

	public void adminChatCreate(ChatDto chatDto) {
		ss.insert("chat.adminChatCreate", chatDto);
	}

	public void chatDelete(String id) {
		ss.delete("chat.delete", id);		
	}

	public List<ChatDto> getChatLog(ChatDto chatDto) {
		String uuid = chatDto.getUuid();
		if(uuid != null) {
			return ss.selectList("chat.getLogAdmin", chatDto.getUuid());
		}
		else {
			return ss.selectList("chat.getLog", chatDto.getId());
		}
	}


}

