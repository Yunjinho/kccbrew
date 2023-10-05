package kr.co.kccbrew.sysMng.statistics.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.sysMng.statistics.model.StatisticsVo;

public interface IStatisticsRepository {
	/**
	 * 연도별 장비별 AS 건수 조회
	 * @param date : 해당 달
	 * @return
	 */
	public List<StatisticsVo> machineByMonth(@Param("start")String start,@Param("end")String end);
	
	/**
	 * 연도별  평점 상위/하위 기사
	 * @param date
	 * @return
	 */
	public List<StatisticsVo> highRankMecha(@Param("start")String start,@Param("end")String end);
	public List<StatisticsVo> lowRankMecha(@Param("start")String start,@Param("end")String end);
	
	/**
	 * 장비별 월별 재접수율
	 * @param start
	 * @param end
	 * @return
	 */
	public List<StatisticsVo> reapplyRateByMachine(@Param("start")String start,@Param("end")String end);
}