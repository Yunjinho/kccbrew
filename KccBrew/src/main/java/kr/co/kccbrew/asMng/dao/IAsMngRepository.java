package kr.co.kccbrew.asMng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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
	
	/**
	 * 점포 정보 조회
	 * @return 로그인한 아이디로 매핑된 점포 정보 
	 */
	public AsMngVo selectStrInfo(@Param("userId")String userId);
	
	/**
	 * AS 이미지 파일 등록
	 * @param asMngVo 파일 시퀀스 번호 받기위한 Vo
	 */
	public void insertFile(AsMngVo asMngVo);
	
	/**
	 * AS 이미지 파일 등록
	 * @param asMngVo 파일 정보 들어가있음
	 */
	public void insertFileDtl(AsMngVo asMngVo);
	
	public void insertAs(AsMngVo asMngVo);
	
}
