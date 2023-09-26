package kr.co.kccbrew.sysMng.cdMng.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.sysMng.cdMng.model.CdMngVo;



public interface ICdMngRepository { 
	List<CdMngVo> selectNm();
	List<CdMngVo> grcdList();
	List<CdMngVo> grcdDtlList(@Param("cdId")String cdId);
	List<CdMngVo> filter(Map<String, Object> map);
	int getCdFilterCount(Map<String, Object> map);
	CdMngVo selectCd(@Param("cdId")String cdId, @Param("cdDtlId")String cdDtlId);
	CdMngVo selectGrpDetail(String cdId);
	void insert1(CdMngVo codeMng);
	void insert2(CdMngVo codeMng);
	void cdMod(CdMngVo codeMng);
	void grpUpdate1(CdMngVo codeMng);
	void grpUpdate2(CdMngVo codeMng);

}
