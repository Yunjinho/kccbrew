package kr.co.kccbrew.comm.chat.model;

import java.util.Date;
/**
 * 채팅 관련 vo
 */
public class ChatDto {
	//sender - 작성자, user_id - 채팅방 유저
	private String user_id, sender;
	//msg-메세지, uuid-채팅방 번호
	private String msg, id, uuid;
	private Date create_time;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Override
	public String toString() {
		return "ChatDto:[user_id="+user_id+", sender="+sender+", msg="+msg+ ", id="+id+", create_time="
				+create_time+", uuid="+uuid+"]";
	}

}