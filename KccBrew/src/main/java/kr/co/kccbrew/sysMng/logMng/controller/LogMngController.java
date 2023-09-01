package kr.co.kccbrew.sysMng.logMng.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LogMngController {

	private final LogMngService logService;

	@Getter
	@Setter
	@NoArgsConstructor
	public class UserVO {
		private String userId = "seeun";
		private String userType = "admin";
	}

	/* 로깅 테스트 컨트롤러 */
	@RequestMapping("/log-test")
	public String logTest(HttpServletRequest request, 
			HttpServletResponse response) {

		UserVO user = new UserVO();
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		return "myfirstlog";
	}

	/* 두번째 로깅 컨트롤러 */
	@RequestMapping("/second-log")
	public String secondLog() {
		return "mysecondlog";
	}


	/* 로그 리스트 */
	@RequestMapping("/log")
	public String paging(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") LogMngVo searchContent,
			Model model,
			HttpSession session
			) {

		log.info("currentPage={}, searchContent={}", currentPage, searchContent);

		//	List<LogMngVo> logs = logService.getAllLogs();
		List<LogMngVo> logs = logService.getRetrievedLogs(searchContent);
		int totalPage = 0;
		int totalLog = 0;

		//	검색결과가 있는 경우 paging처리
		if (logs != null && !logs.isEmpty()) {
			log.info("검색된 첫번째 로그 logs={}", logs.get(0));
			totalLog = logs.size();
			int partitionSize = 10;
			List<List<LogMngVo>> partitionedList = Lists.partition(logs, partitionSize);
			totalPage = partitionedList.size();
			logs = partitionedList.get(currentPage-1);
		} else {
			log.info("logs={}", logs);
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);

		model.addAttribute("totalLog", totalLog);
		model.addAttribute("logs", logs);

		return "sysMng/logMng/logMngList";
	}
}



