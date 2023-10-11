package kr.co.kccbrew.comm.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.comm.chat.model.ChatDto;
import lombok.RequiredArgsConstructor;

@Repository
public interface IChatRepository {
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
	 * @return 관리자 채팅 로그
	 */
    List<ChatDto> getLogAdmin(String getUuid);
	
    /**
	 * @return 사용자 채팅로그
	 */
    List<ChatDto> getLog(String getId);


}

