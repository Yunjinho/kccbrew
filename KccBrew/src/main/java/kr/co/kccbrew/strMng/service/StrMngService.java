package kr.co.kccbrew.strMng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.strMng.dao.IStrMngRepository;
import kr.co.kccbrew.strMng.model.StrMngVo;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StrMngService implements IStrMngService {
	/**
	 * 점포관리를 위한 서비스
	 * 
	 * @author BAESOOYEON
	 */
	private final IStrMngRepository storeRepository;
	
	@Override
	public List<StrMngVo> storeAll() {
		return storeRepository.storeAll();
	}
	
	@Override
	public StrMngVo storeDetail(int storeSeq) {
		return storeRepository.storeDetail(storeSeq); 
	}

	/* 점포의 점주 */
	@Override
	public List<StrMngVo> ownerList(int storeSeq) {
		return storeRepository.ownerList(storeSeq);
	}
	
	@Override
	public void insert(StrMngVo store) {
		storeRepository.insert(store);
	};
	
	/* 지역리스트 */
	@Override
	public List<StrMngVo> locationNm(){
		return storeRepository.locationNm();
	}
	
	/* 지역상세리스트 */
	@Override
	public List<StrMngVo> locationNmSeoul(){
		return storeRepository.locationNmSeoul();
	}
	@Override
	public void update(StrMngVo store) {
		storeRepository.update(store);
	};
	@Override
	
	/* 점포명중복확인 */
	public StrMngVo selectStoreByName(String storeNm) {
		return storeRepository.selectStoreByName(storeNm);
		
	};
	
	/* 점포검색 */
	@Override
	public List<StrMngVo> search(String keyword){
	return storeRepository.search(keyword);	
	}
}
