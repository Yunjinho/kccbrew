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
	
	public List<SchdlMngVo2> selectMechaSchedules2(Map<String, Object> map);
}
