package kr.co.kccbrew.sysMng.logMng.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;

public interface ILogMngRepository {
	public void insertLog(LogMngVo log);
	public List<LogMngVo> selectAllLogs();
	public List<LogMngVo> selectRetrievedLogs(LogMngVo logVO);

}
