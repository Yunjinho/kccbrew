package kr.co.kccbrew.sysMng.cdMng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.sysMng.cdMng.model.CdMngVo;



public interface ICdMngRepository { 
	List<CdMngVo> selectNm();
	List<CdMngVo> filter(CdMngVo codeMng);
	CdMngVo selectCd(@Param("cdId")String cdId, @Param("cdDtlId")String cdDtlId);
	CdMngVo selectGrpDetail(String cdId);
	void insert1(CdMngVo codeMng);
	void insert2(CdMngVo codeMng);
	void cdMod(CdMngVo codeMng);
	void grpUpdate1(CdMngVo codeMng);
	void grpUpdate2(CdMngVo codeMng);

}
