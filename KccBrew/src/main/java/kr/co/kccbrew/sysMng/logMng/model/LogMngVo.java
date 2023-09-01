package kr.co.kccbrew.sysMng.logMng.model;


import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.Data;


@Data
@Component
public class LogMngVo {
	private Integer logSeq;
	private LocalDateTime date; 
	private String uri;
	private String view;
	private String userId;
	private String userType;
	private String ip;
	private String statusCode;

	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;

	/*
	 * @ModelAttribute("startYr") public int getStartYr() { return startYr; }
	 * 
	 * @ModelAttribute("startMn") public int getStartMn() { return startMn; }
	 * 
	 * @ModelAttribute("endYr") public int getEndYr() { return endYr; }
	 * 
	 * @ModelAttribute("endMn") public int getEndMn() { return endMn; }
	 */
}
