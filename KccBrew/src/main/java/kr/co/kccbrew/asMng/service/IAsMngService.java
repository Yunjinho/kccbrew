package kr.co.kccbrew.asMng.service;

import java.util.List;

import kr.co.kccbrew.asMng.model.AsMngVo;

public interface IAsMngService {
	/**
	 * @param asMngVo : 검색 조건 들어있는 Vo
	 * @return : 조건으로 검색한 as 리스트
	 */
	public List<AsMngVo>selectASList(AsMngVo asMngVo,int page);
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
	
	/**
	 * 점포 정보 조회
	 * @return 로그인한 아이디로 매핑된 점포 정보 
	 */
	public AsMngVo selectStrInfo(String userId);
	
	/**
	 * AS 등록
	 * @param asMngVo AS 정보가 담겨있는 vo
	 */
	public void insertAs(AsMngVo asMngVo);
}
