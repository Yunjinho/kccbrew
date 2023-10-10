package kr.co.kccbrew.sysMng.cdMng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.sysMng.cdMng.model.CdMngVo;



 
public interface ICdMngService {
	
	/* 그룹코드 중복제거 리스트 */
	List<CdMngVo> selectNm();
	/* 상세코드 상세조회 */
	CdMngVo selectCd(String cdId, String cdDtlId);
	/* 그룹코드 상세조회 */
	CdMngVo selectGrpDetail(String cdId);
	/* 그룹코드 등록 */
	void insert1(CdMngVo codeMng);
	/* 상세코드 등록 */
	void insert2(CdMngVo codeMng);
	/* 상세코드 수정 */
	void cdMod(CdMngVo codeMng);
	/* 그룹코드 수정 */
	void grpUpdate(CdMngVo codeMng);
	/* 그룹코드 리스트 */
	List<CdMngVo> grcdList();
	/* 상세코드 리스트 */
	List<CdMngVo> grcdDtlList(String cdId);
 
}