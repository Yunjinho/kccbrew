package kr.co.kccbrew.asMng.dao;

import java.util.List;

import kr.co.kccbrew.asMng.model.AsMngVo;

public interface IAsMngRepository {
	/**
	 * 
	 * @param asInfoSeq : 접수 번호
	 * @param startDate : 시작 일자
	 * @param endDate : 끝난 일자
	 * @param userId : 사용자 아이디
	 * @param strNm : 점포 명
	 * @param strAddr : 점포 주소
	 * @param epmCd : 장비코드
	 * @param asStatus : as상태 
	 * @return 조회한 AS 리스트
	 */
	public List<AsMngVo>selectASList(AsMngVo asMngVo);
	
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
