package kr.co.kccbrew.comm.util;

import org.apache.ibatis.annotations.Mapper; 
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IObjectUtilRepository {
	
	/**
	 * 수리기사 장비코드에 따른 장비이름 조회
	 * @param codeId: 장비코드
	 * @return String: 장비명
	 */
	public String selectEquipmentName(String codeId);
	
	/**
	 * 지역코드에 따른 지역이름 조회
	 * @param codeId: 지역코드
	 * @return String: 지역명
	 */
	public String selectLocationName(String codeId);
	
	/**
	 * 사용자타입코드에 따른 사용자타입명 조회
	 * @param codeId: 사용자타입코드
	 * @return String: 사용자타입명
	 */
	public String selectUserTypeName(String codeId);

}
