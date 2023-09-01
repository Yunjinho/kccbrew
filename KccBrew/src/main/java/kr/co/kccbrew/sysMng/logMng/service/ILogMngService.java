package kr.co.kccbrew.sysMng.logMng.service;


import java.util.List;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;


public interface ILogMngService {
	public List<LogMngVo> getRetrievedLogs(LogMngVo logVO);
	public void insertlogVO(LogMngVo logVO);
	public List<LogMngVo> getAllLogs();
}