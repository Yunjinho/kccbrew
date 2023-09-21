package kr.co.kccbrew.sysMng.logMng.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/* 로깅 테스트 컨트롤러 */
	@RequestMapping("/logtest")
	public String logTest(HttpServletRequest request, 
			HttpServletResponse response) {

		return "log/logtest";
	}

	/* 로깅 테스트 컨트롤러 */
	@RequestMapping("/logtest2")
	@ResponseBody
	public String logTest2(HttpServletRequest request, 
			HttpServletResponse response) {
		LogMngVo logVo = new LogMngVo();
		List<LogMngVo> logList = logService.selectSearchedLogs(logVo, 1);

		return "i am log mapper";
	}


	/* 로그조회 */
	@GetMapping("/admin/log")
	public String getLogs(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") LogMngVo searchContent, // Model에 'searchContent' 자동추가
			Model model,
			HttpSession session
			) {

		log.info("이세은 LogController#log: currentPage={}, searchContent={}", currentPage, searchContent);

		List<LogMngVo> selectedLogs = logService.selectSearchedLogs(searchContent, currentPage);
		System.out.println("selectedLogs: " + selectedLogs);
		int totalPage = 0;
		int totalLogCount = logService.getSearchedLogsCount(searchContent);
		int sharePage = 0;

		//   paging처리
		if (selectedLogs != null && !selectedLogs.isEmpty()) {
			totalPage = (int) Math.ceil((double) totalLogCount/10);
		} else {
		}

		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalLog", totalLogCount);
		model.addAttribute("logs", selectedLogs);



		return "adminLogManage";
	}
}