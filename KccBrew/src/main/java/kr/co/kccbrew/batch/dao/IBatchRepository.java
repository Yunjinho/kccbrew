package kr.co.kccbrew.batch.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.batch.AsLogVo;

@Mapper
@Repository
public interface IBatchRepository {
	
	public AsLogVo selectRecentAsLog();
	public void updateOmissionResult();

}
