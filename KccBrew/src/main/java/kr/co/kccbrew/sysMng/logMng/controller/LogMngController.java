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


	   /* 로그조회 */
	   @GetMapping("/log")
	   public String getLogs(
	         @RequestParam(defaultValue = "1") int currentPage,
	         @ModelAttribute("searchContent") LogMngVo searchContent,
	         Model model,
	         HttpSession session
	         ) {

	      log.info("이세은 LogController#log: currentPage={}, searchContent={}", currentPage, searchContent);

	      List<LogMngVo> logs = logService.getRetrievedLogs(searchContent);
	      int totalPage = 0;
	      int totalLog = 0;

	      //   paging처리
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
	      
	      int sharePage = currentPage / 10;

	      model.addAttribute("totalPage", totalPage);
	      model.addAttribute("currentPage", currentPage);
	      model.addAttribute("sharePage", sharePage);

	      model.addAttribute("totalLog", totalLog);
	      model.addAttribute("logs", logs);


	      return "log/logMngList";
	   }
}