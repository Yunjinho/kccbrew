package kr.co.kccbrew.sysMng.alarm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.sysMng.alarm.model.AlarmVo;
import kr.co.kccbrew.sysMng.alarm.service.IAlarmService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : AlarmContoller
 * @Decription : 특정행위에 따른 알림기능 구현
 * 
 * @   수정일           			    수정자            		 수정내용
 * ============      ==============     ==============
 * 2023-10-02							이세은					   	최초생성
 * @author LEESEEUN
 * @version 1.0
 */

@Slf4j
@Controller
public class AlarmContoller {
	@Autowired
	private IAlarmService alarmService;

	/**
	 * 
	 * @return JSP페이지
	 */
	@GetMapping("/websocket-test")
	public String alarmPage() {
		return "/sysMng/alarm/websockettest";
	}

	/**
	 * 
	 * @param String userId : 사용자ID | String userType: 사용자 권한
	 * @return List<AlarmVo> getAlarms: 사용자에 따른 알람리스트
	 */
	@GetMapping("/getAlarmData")
	@ResponseBody
	public List<AlarmVo> getAlarms(@RequestParam(value = "userId", required = false) String userId, 
																		@RequestParam(value="userType", required = false) String userType) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("receiverId", userId);
		map.put("receiverType", userType);

		return alarmService.getAlarmsByConditions(map);
	}
}