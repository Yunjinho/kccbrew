package kr.co.kccbrew.asMng.dao;

import java.util.List;
import java.util.Map;

import kr.co.kccbrew.asMng.model.AsMngVo;

public interface IAsMngRepository {
	/**
	 * @return 조회한 AS 리스트
	 */
	public List<AsMngVo>selectASList(Map<String, Object> map);
	/**
	 * @return AS 리스트의 총 수
	 */
	public int countASList(AsMngVo asMngVo);
	
	/**
	 * 장비 코드 리스트 조회
	 * @return : 장비 코드 리스트
	 */
	public List<AsMngVo> selectMachineCd();
	/**
	 * AS상태 코드 리스트 조회
	 * @return : AS상태 코드 리스트
	 */
	public List<AsMngVo> selectAsStatusCd();
	/**
	 * 지역 코드 리스트 조회
	 * @return : 지역 코드 리스트
	 */
	public List<AsMngVo> selectLocationCd();
}
