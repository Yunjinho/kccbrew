package kr.co.kccbrew.sysMng.logMng.controller;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.util.DateFormat;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassNmae : LogMngController
 * @Decription : 이용자 활동 추적을 위한 로그 생성 및 조회를 하는 페이지의 controller
 * 
 * @   수정일                    수정자                        			수정내용
 * ============      ==============     ============================
 * 2023-09-01			           이세은			        						최초생성
 * 2023-10-05			           이세은			        		로그조회 시 페이징 처리 수정
 * 
 * @author LEESEEUN
 * @version 1.0
 */

@Controller
@Slf4j
@RequiredArgsConstructor
public class LogMngController {
	@Autowired
	private DateFormat dateFormat;
	@Autowired
	private LogMngService logService;

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


	/**
	 * 로그조회 - 로그조회 페이지 요청할 때 페이지 반환한다.
	 * 
	 * @param int currentPage - 현재 페이지 
	 * @param LogMngVo searchContent - model에 추가하기 위한 LogMngVo객체
	 * @param Model model - 데이터를 저장해서 jsp에 뿌려줄 용도
	 * 
	 * @return data(Map<String, Object>) - 페이징과 검색에 따른 로그 데이터를 저장한 map
	 * @throws 
	 */
	@GetMapping("/admin/log")
	public String getLogs(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") LogMngVo searchContent, // Model에 'searchContent' 자동추가
			Model model
			) {
		Date firstDay = dateFormat.getFirstDayOfYear();
		Date lastDay = dateFormat.getLastDayOfYear();
		
		searchContent.setStartDate(firstDay);
		searchContent.setEndDate(lastDay);
		
		log.info("이세은 LogController#log: currentPage={}, searchContent={}", currentPage, searchContent);

		List<LogMngVo> selectedLogs = logService.selectSearchedLogs(searchContent, currentPage);
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

		model.addAttribute("totalDataNumber", totalLogCount);
		model.addAttribute("logs", selectedLogs);



		return "adminLogManage";
	}



	/**
	 * 로그검색 - 검색한 조건에 따른 로그 데이터를 반환한다.
	 * 
	 * @param int currentPage - 현재 페이지 
	 * @param String startDate - 날짜 검색 시작일
	 * @param String endDate - 날짜 검색 종료일
	 * @param LogMngVo logMngVo - 로그검색조건
	 * 
	 * @return data(Map<String, Object>) - 페이징과 검색에 따른 로그 데이터를 저장한 map
	 */
	@PostMapping(value="/admin/log", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getSearchedLogs(
			@RequestParam(value="currentPage", defaultValue="1") int currentPage,
			@ModelAttribute LogMngVo logMngVo
			) {
		System.out.println("LogMngController.getSearchedLogs");
		System.out.println("logMngVo: " + logMngVo);

		List<LogMngVo> logs = logService.selectSearchedLogs(logMngVo, currentPage); //검색에 따른 페이지별 로그 리스트
		int logCount = logService.getSearchedLogsCount(logMngVo); // 검색에 따른 전체 로그리스트 개수

		int totalPage = 0;
		int sharePage = 0;

		//   totalPage 구하기
		if (logs != null && !logs.isEmpty()) {
			totalPage = (int) Math.ceil((double) logCount / 10);
			System.out.println("totalPage: " + totalPage);
		} else {
		}

		// sharePage 구하기
		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		Map<String, Object> data = new HashMap<>();
		data.put("totalPage", totalPage);
		data.put("currentPage", currentPage);
		data.put("sharePage", sharePage);
		data.put("totalDataNumber", logCount);
		data.put("logs", logs);

		return data;
	}
}