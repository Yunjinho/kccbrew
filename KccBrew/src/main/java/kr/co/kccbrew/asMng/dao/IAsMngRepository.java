package kr.co.kccbrew.asMng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

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
	 *  AS 전체 조회
	 */
	public List<AsMngVo>selectAllASList(AsMngVo asMngVo); 
	
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
	/**
	 * AS 등록
	 * @param asMngVo
	 */
	public void insertAs(AsMngVo asMngVo);
	
	/**
	 * AS detail 조회
	 * @param asInfoSeq : seq 번호
	 * @return
	 */
	public AsMngVo selectAsInfoDetail(@Param("asInfoSeq")String asInfoSeq,@Param("asAssignSeq")String asAssignSeq);
	
	/**
	 * AS건에 등록한 파일 정보 조회
	 * @param fileDtlId
	 * @return
	 */
	public List<AsMngVo> selectAsImg(@Param("asImgSeq")String fileDtlId);
	
	/**
	 * 지역코드 조회
	 * @param locationCd
	 * @return
	 */
	public List<AsMngVo> selectLocationCd();
	
	/**
	 * 지역 상세코드 조회
	 * @param locationCd
	 * @return
	 */
	public List<AsMngVo> selectLocationDtlCd(@Param("locationCd")String locationCd);
	
	/**
	 * 선택한 날짜에 점포 휴일 체크
	 * @param date 선택 날짜
	 * @param userId AS 신청인
	 * @return
	 */
	public int checkStrSchedule(@Param("date")String date,@Param("userId")String userId);
	
	/**
	 * 
	 * @param date 방문 선택 날짜
	 * @param locationCd 지역 코드
	 * @return
	 */
	public List<AsMngVo> selectMechList(@Param("date")String date,@Param("locationCd")String locationCd,@Param("machineCd")String machineCd);
	
	/**
	 * 기사 배정
	 * @param asMngVo
	 */
	public void insertAsAssign(AsMngVo asMngVo);
	
	/**
	 * AS status 상태 변경
	 * @param asMngVo
	 */
	public void updateAsInfoStatus(AsMngVo asMngVo);
	
	/**
	 * 반려 등록
	 */
	public void updateInfoReject(@Param("seq")String seq,@Param("content")String content,@Param("userId")String userId);
	public void updateAssignReject(@Param("seq")String seq,@Param("content")String content,@Param("userId")String userId);
	
	/**
	 * 기사의 배정 반려건에 대한 처리
	 */
	public void updateRejectConfirm(Map<String, Object> map);
	
	/**
	 * 수리기사 AS 결과 입력 
	 */
	public void insertResult(AsMngVo asMngVo);
	
	/**
	 * 점포점주 AS 결과입력
	 */
	public void updateResultMng(AsMngVo asMngVo);
	/**
	 * 재배정 여부
	 * @param asMngVo
	 */
	public void updateAsResultConfirm(AsMngVo asMngVo);
}
