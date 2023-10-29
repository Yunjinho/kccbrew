package kr.co.kccbrew.asMng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.asMng.model.AsMngVo;


public interface IAsMngService {
	/**
	 * @param asMngVo : 검색 조건 들어있는 Vo
	 * @return : 조건으로 검색한 as 리스트
	 */
	public List<AsMngVo>selectASList(AsMngVo asMngVo,int page);
	
	/**
	 *  조건에 맞는 AS 전체 조회
	 */
	public List<AsMngVo>selectAllASList(AsMngVo asMngVo);
	/** 
	 * @return AS 리스트의 총 수
	 */
	public int countASList(AsMngVo asMngVo);
	
	/**
	 * 코드 리스트 조회
	 * @return : 코드 리스트
	 */
	public List<AsMngVo> selectCd(String code);
	
	/**
	 * 점포 정보 조회
	 * @return 로그인한 아이디로 매핑된 점포 정보 
	 */
	public List<AsMngVo> selectStrInfo(String userId);
	
	/**
	 * AS 등록
	 * @param asMngVo AS 정보가 담겨있는 vo
	 */
	public void insertAs(AsMngVo asMngVo);
	/**
	 * AS detail 조회
	 * @param asInfoSeq : seq 번호
	 * @return
	 */
	public AsMngVo selectAsInfoDetail(String asInfoSeq,String asAssignSeq,String storeSeq);
	
	/**
	 * AS건에 등록한 파일 정보 조회
	 * @param fileDtlId
	 * @return
	 */
	public List<AsMngVo> selectAsImg(String fileDtlId);
	
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
	public int checkStrSchedule(String date,String storeSeq);
	
	/**
	 * 
	 * @param date 방문 선택 날짜
	 * @param locationCd 지역 코드
	 * @return
	 */
	public List<AsMngVo> selectMechList(String date,String locationCd,String machineCd);
	
	/**
	 * 기사 배정
	 * @param asMngVo
	 */
	public AsMngVo insertAsAssign(AsMngVo asMngVo);
	
	/**
	 * 반려 등록
	 */
	public void updateInfoReject(String seq,String content,String userId);
	public void updateAssignReject(String seq,String content,String userId);
	
	/**
	 * 기사의 배정 반려건에 대한 처리
	 */
	public void updateRejectConfirm(AsMngVo asMngVo,String flag);
	/**
	 * AS 결과 입력
	 */
	public void insertAsResult(AsMngVo asMngVo);

	/**
	 * 점포점주 AS 결과입력
	 */
	public void updateResultMng(AsMngVo asMngVo);
	
	
	
	public void asMod(AsMngVo asMngVo);
	public void deleteFile(AsMngVo asMngVo, String imgSeq);
	
	/**
	 * AS 접수 취소
	 * @param asInfoSeq
	 */
	public void deleteAs(@Param("asInfoSeq")String asInfoSeq);

	public List<AsMngVo> getAsHistory(String asInfo, String userTypeCd);
	
}
