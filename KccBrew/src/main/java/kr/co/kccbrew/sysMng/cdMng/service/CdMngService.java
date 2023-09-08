package kr.co.kccbrew.sysMng.cdMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.strMng.model.StrMngVo;
import kr.co.kccbrew.sysMng.cdMng.dao.ICdMngRepository;
import kr.co.kccbrew.sysMng.cdMng.model.CdMngVo;
import lombok.RequiredArgsConstructor;
/**
 * 코드관리를 위한 서비스
 * 
 * @author BAESOOYEON
 */
@Service
@RequiredArgsConstructor
public class CdMngService implements ICdMngService {

	private final ICdMngRepository cdMngRepository;

	/* 그룹코드 중복제거 리스트 */
	@Override
	public List<CdMngVo> selectNm() {
		return cdMngRepository.selectNm();
	}

	/* 조건검색가능한 코드리스트 */
	@Override
	public List<CdMngVo> filter(CdMngVo codeMng, int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CdMngVo", codeMng);
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);
		return cdMngRepository.filter(map);
	}
	
	@Override
	public int getCdFilterCount(CdMngVo codeMng) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CdMngVo", codeMng);
		return cdMngRepository.getCdFilterCount(map);
	}
	
	/* 상세코드정보 */
	/**
	 * 
	 * cdId : 그룹코드아이디
	 * cdDtlId : 상세코드아이디
	 */
	@Override
	public CdMngVo selectCd(String cdId, String cdDtlId) {
		return cdMngRepository.selectCd(cdId, cdDtlId);
	}

	/* 그룹코드정보 */
	/**
	 * 
	 * cdId : 그룹코드아이디
	 */
	@Override
	public CdMngVo selectGrpDetail(String cdId) {
		return cdMngRepository.selectGrpDetail(cdId);
	}

	/* 그룹코드등록 */
	@Override
	public void insert1(CdMngVo codeMng) {
		cdMngRepository.insert1(codeMng);
	}

	/* 상세코드등록 */
	@Override
	public void insert2(CdMngVo codeMng) {
		cdMngRepository.insert2(codeMng);
	}

	/* 상세코드수정 */
	@Override
	public void cdMod(CdMngVo codeMng) {
		cdMngRepository.cdMod(codeMng);
	}

	/* 그룹코드수정 */
	@Override
	public void grpUpdate(CdMngVo codeMng) {
		cdMngRepository.grpUpdate1(codeMng);
		cdMngRepository.grpUpdate2(codeMng);
	}


}
