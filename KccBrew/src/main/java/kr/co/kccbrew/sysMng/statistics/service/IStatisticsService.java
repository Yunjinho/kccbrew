package kr.co.kccbrew.sysMng.statistics.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.sysMng.statistics.model.StatisticsVo;

public interface IStatisticsService {
	/**
	 * 월별 장비별 AS 건수 조회
	 * @param date : 해당 달
	 * @return
	 */
	public List<StatisticsVo> machineByMonth(@Param("date")String date);
	/**
	 * 평점 상위/하위 기사
	 * @param date
	 * @return
	 */
	public List<StatisticsVo> highRankMecha(@Param("date")String date);
	public List<StatisticsVo> lowRankMecha(@Param("date")String date);
	
	/**
	 * 장비별 월별 재접수율
	 * @param start
	 * @param end
	 * @return
	 */
	public List<StatisticsVo> reapplyRateByMachine(String date);
}
