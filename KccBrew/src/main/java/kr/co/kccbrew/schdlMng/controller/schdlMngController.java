package kr.co.kccbrew.schdlMng.controller;

import java.util.List; 

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class schdlMngController {
	
	/* 스케줄조회 */
	@GetMapping("/schedule/{id}")
	public String getLogs(
			@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") SchdlMngVo searchContent,
			@PathVariable("id") String userId,
			Model model,
			HttpSession session
			) {
		

		/* 리스트 페이징 처리 */
		List<SchdlMngVo> schedules = null;
		int totalPage = 0;
		int totalLog = 0;
		int sharePage = 0;


		if (schedules != null && !schedules.isEmpty()) {
			log.info("검색된 첫번째 로그 logs={}", schedules.get(0));
			totalLog = schedules.size();
			int partitionSize = 10;
			List<List<SchdlMngVo>> partitionedList = Lists.partition(schedules, partitionSize);
			totalPage = partitionedList.size();
			schedules = partitionedList.get(currentPage-1);
		} else {
			log.info("logs={}", schedules);
		}

		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);


		return "schdl/schdlMngList";
	}
	
}
