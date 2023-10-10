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
    
    void userChatCreate(ChatDto chatDto);

    void adminChatCreate(ChatDto chatDto);

    void chatDelete(String id);
    
    List<ChatDto> getLogAdmin(String getUuid);
    List<ChatDto> getLog(String getId);


}

