package kr.co.kccbrew.sysMng.statistics.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.logMng.dao.ILogMngRepository;
import kr.co.kccbrew.sysMng.logMng.service.LogMngService;
import kr.co.kccbrew.sysMng.statistics.dao.IStatisticsRepository;
import kr.co.kccbrew.sysMng.statistics.model.StatisticsVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsService implements IStatisticsService{
	/**
	 * IStatisticsRepository 변수 선언
	 */
	private final IStatisticsRepository repository;
	
	/**
	 * 장비별 AS 건수 조회
	 * @param date : 해당 달
	 * @return
	 */
	@Override
	public List<StatisticsVo> machineByMonth(String date) {
		return repository.machineByMonth(date+"/01",date+"/12");
	}
	/**
	 * 평점 상위/하위 기사
	 * @param date
	 * @return
	 */
	@Override
	public List<StatisticsVo> highRankMecha(String date) {
		return repository.highRankMecha(date+"/01",date+"/12");
	}
	@Override
	public List<StatisticsVo> lowRankMecha(String date) {
		return repository.lowRankMecha(date+"/01",date+"/12");
	}
	
	/**
	 * 장비별 월별 재접수율
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public List<StatisticsVo> reapplyRateByMachine(String date) {
		return repository.reapplyRateByMachine(date+"/01",date+"/12");
	}
	

}
