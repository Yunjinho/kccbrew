package kr.co.kccbrew.sysMng.logMng.service;


import java.sql.Date;
import java.util.List;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;


public interface ILogMngService {
	public void insertlogVO(LogMngVo logVO);
	public List<LogMngVo> getAllLogs();
	
	/*조건에 따른 로그리스트 가져오기*/
	/*public List<LogMngVo> getRetrievedLogs(LogMngVo logVO);*/
	public List<LogMngVo>selectSearchedLogs(LogMngVo logVO, int currentPage);
	public int getSearchedLogsCount(LogMngVo logVO);
}