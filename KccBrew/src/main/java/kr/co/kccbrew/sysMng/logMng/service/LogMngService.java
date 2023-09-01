package kr.co.kccbrew.sysMng.logMng.service;


import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.logMng.dao.ILogMngRepository;
import kr.co.kccbrew.sysMng.logMng.model.LogMngVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogMngService implements ILogMngService{
	private final ILogMngRepository logRepository;

	@Override
	public void insertlogVO(LogMngVo logVO) {
//		switch(logVO.getStatusCode()) {
//		case "200": 
//			logVO.setStatusCode("200 OK");
//			break;
//		case "201":
//			logVO.setStatusCode("201 Created");
//			break;
//		case "400":
//			logVO.setStatusCode("400 Bad Request");
//			break;
//		case "403":
//			logVO.setStatusCode("403 Forbidden");
//			break;
//		case "404":
//			logVO.setStatusCode("404 Not Found");
//			break;
//		case "500":
//			logVO.setStatusCode("500 Internal Server Error");
//			break;
//		}
		
		if (logVO.getUserId() == null || logVO.getUserId().isEmpty()) {
			logVO.setUserId("non-member");
		}
		if(logVO.getUserType() == null || logVO.getUserType().isEmpty()) {
			logVO.setUserType("non-member");
		}
		if(logVO.getView() == null || logVO.getView().isEmpty()) {
			logVO.setView(" ");
		}
		log.info("Service에서의 logVO객체:{}", logVO);
		logRepository.insertLog(logVO);
	}
	
	@Override
	public List<LogMngVo> getAllLogs() {
		return logRepository.selectAllLogs();
	}

	@Override
	public List<LogMngVo> getRetrievedLogs(LogMngVo logVO) {
		// TODO Auto-generated method stub
		return logRepository.selectRetrievedLogs(logVO);
	}
	
	
	
	
	
}