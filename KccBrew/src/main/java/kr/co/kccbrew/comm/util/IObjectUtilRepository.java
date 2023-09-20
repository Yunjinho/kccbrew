package kr.co.kccbrew.comm.util;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IObjectUtilRepository {
	
	public String selectEquipmentName(String codeId);
	public String selectLocationName(String codeId);

}
