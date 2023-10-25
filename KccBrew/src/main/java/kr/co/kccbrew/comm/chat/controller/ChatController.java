package kr.co.kccbrew.comm.chat.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.kccbrew.comm.chat.model.ChatDto;
import kr.co.kccbrew.comm.chat.service.IChatService;
/**
 * @ClassNmae : ChatController
 * @Decription : 실시간 문의 채팅
 * 
 * @   수정일           			    수정자            		 수정내용
 * ============      ==============     ==============
 * 2023-10-02							배수연					   	최초생성
 * 2023-10-14							배수연					   	jsp 리턴 수정
 * @author BAESOOYEON
 * @version 1.0
 */

@Controller
public class ChatController {
	
	@Autowired
	IChatService chatService;

	/**
	 * 
	 * 수리기사, 점주 채팅방
	 */
	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/userChat");
		return mv;
	}
	/**
	 * 
	 * 관리자 채팅방
	 */
	@RequestMapping("/admin/chat")
	public ModelAndView adminChat(ModelAndView mv, HttpServletRequest request) {
		System.out.println("---ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
		System.out.println(request.getHeader("REFERER"));
		System.out.println("리퍼러 확인");
		if(request.getHeader("REFERER") == null) {
		mv.addObject("error", "잘못된 접근입니다");
		mv.setViewName("redirect:/");
			return mv;
		}
		mv.setViewName("chat/adminChat");
		return mv;
	}
	
	
	/*관리자 채팅작성*/
	@ResponseBody
	@RequestMapping(value="/adminChatCreate", method=RequestMethod.POST)
	public void adminChatCreate(ChatDto chatDto) {
		chatService.adminChatCreate(chatDto);
	}
	
	/*사용자 채팅작성*/
	@ResponseBody
	@RequestMapping(value="/chatCreate", method=RequestMethod.POST)
	public void chatCreate(ChatDto chatDto) {
		chatService.userChatCreate(chatDto);
	}
	
	/*채팅끄기*/
	@ResponseBody
	@RequestMapping(value="/chatDelete", method=RequestMethod.POST)
	public void chatDelete(String id) {
		chatService.chatDelete(id);
	}
	
	/*채팅로그가져오기*/
	@ResponseBody
	@RequestMapping(value="/getChatLog", method=RequestMethod.POST)
	public List<ChatDto> getChatLog(ChatDto chatDto){
		List<ChatDto> list = chatService.getChatLog(chatDto);
		if(list != null) {
		return list;
		}
		else return null;
	}

	@ResponseBody
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public ChatDto getUser(String uuid) {
		System.out.println(uuid);
		ChatDto chatDto= chatService.getUser(uuid);
		return chatDto;
	}

}