package kr.co.kccbrew.sysMng.logMng.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;

@Repository
@Mapper
public interface ILogMngRepository {
	public void insertLog(LogMngVo log);
	
	/*전체 로그리스트 가져오기*/
	public List<LogMngVo> selectAllLogs();

	/*조건에 맞는 로그리스트 가져오기*/
	/*public List<LogMngVo> selectRetrievedLogs(LogMngVo logVO);*/
	public List<LogMngVo> selectSearchedLogs(Map<String, Object> map);
	public int getSearchedLogsCount(LogMngVo log);
}
