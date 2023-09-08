package kr.co.kccbrew.schdlMng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.schdlMng.model.SchdlMngVo;
import kr.co.kccbrew.schdlMng.model.SchdlMngVo2;

@Repository
@Mapper
public interface ISchdlMngRepository {
	public List<SchdlMngVo> selectMechaSchedules(Map<String, Object> map);
	
	/*스케줄 리스트 조회*/
	public List<SchdlMngVo2> selectSchedules2(Map<String, Object> map);
	public Integer selectSchedule2Count(Map<String, Object> map);
	
	/*회원 캘린더 조회 */
	public List<SchdlMngVo2> selectCalendarSchedule(SchdlMngVo2 schdlMngVo);
}
