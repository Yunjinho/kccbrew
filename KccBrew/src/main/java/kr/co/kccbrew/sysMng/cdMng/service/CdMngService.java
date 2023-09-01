package kr.co.kccbrew.sysMng.cdMng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private final ICdMngRepository codeMngRepository;

	/* 그룹코드 중복제거 리스트 */
	@Override
	public List<CdMngVo> selectNm() {
		return codeMngRepository.selectNm();
	}

	/* 조건검색가능한 코드리스트 */
	@Override
	public List<CdMngVo> filter(CdMngVo codeMng) {
		return codeMngRepository.filter(codeMng);
	}
	
	/* 상세코드정보 */
	/**
	 * 
	 * cdId : 그룹코드아이디
	 * cdDtlId : 상세코드아이디
	 */
	@Override
	public CdMngVo selectDetail(String cdId, String cdDtlId) {
		return codeMngRepository.selectDetail(cdId, cdDtlId);
	}

	/* 그룹코드정보 */
	/**
	 * 
	 * cdId : 그룹코드아이디
	 */
	@Override
	public CdMngVo selectGrpDetail(String cdId) {
		return codeMngRepository.selectGrpDetail(cdId);
	}

	/* 그룹코드등록 */
	@Override
	public void insert1(CdMngVo codeMng) {
		codeMngRepository.insert1(codeMng);
	}

	/* 상세코드등록 */
	@Override
	public void insert2(CdMngVo codeMng) {
		codeMngRepository.insert2(codeMng);
	}

	/* 상세코드수정 */
	@Override
	public void update(CdMngVo codeMng) {
		codeMngRepository.update(codeMng);
	}

	/* 그룹코드수정 */
	@Override
	public void grpUpdate(CdMngVo codeMng) {
		codeMngRepository.grpUpdate1(codeMng);
		codeMngRepository.grpUpdate2(codeMng);
	}


}
