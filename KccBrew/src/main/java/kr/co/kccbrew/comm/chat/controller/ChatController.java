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


@Controller
public class ChatController {
	
	@Autowired
	IChatService chatService;

	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/userChat");
		return mv;
	}
	
	@RequestMapping("/admin/chat")
	public ModelAndView adminChat(ModelAndView mv, HttpServletRequest request) {
		System.out.println(request.getHeader("REFERER"));
		if(request.getHeader("REFERER") == null) {
			mv.addObject("error", "잘못된 접근입니다");
			mv.setViewName("redirect:/");
			return mv;
		}
		mv.setViewName("chatList");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/adminChatCreate", method=RequestMethod.POST)
	public void adminChatCreate(ChatDto chatDto) {
		chatService.adminChatCreate(chatDto);
	}
	
	@ResponseBody
	@RequestMapping(value="/chatCreate", method=RequestMethod.POST)
	public void chatCreate(ChatDto chatDto) {
		chatService.userChatCreate(chatDto);
	}
	
	@ResponseBody
	@RequestMapping(value="/chatDelete", method=RequestMethod.POST)
	public void chatDelete(String id) {
		chatService.chatDelete(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/getChatLog", method=RequestMethod.POST)
	public List<ChatDto> getChatLog(ChatDto chatDto){
		List<ChatDto> list = chatService.getChatLog(chatDto);
		if(list != null) {
			System.out.println("====================+++");
			System.out.println(list);
		return list;
		}
		else return null;
	}


}