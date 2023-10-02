package kr.co.kccbrew.sysMng.alarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlarmContoller {

	@GetMapping("/websocket-test")
	public String alarmPage() {
		return "/sysMng/alarm/websockettest";
	}
}