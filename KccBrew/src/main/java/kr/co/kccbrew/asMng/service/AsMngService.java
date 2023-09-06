package kr.co.kccbrew.asMng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.asMng.dao.IAsMngRepository;
import kr.co.kccbrew.asMng.model.AsMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsMngService implements IAsMngService{
	
	/**
	 * asRepository 변수 선언
	 */
	private final IAsMngRepository asRepository;
	
	
	
	/**
	 *  조회한 검색 결과
	 */
	@Override
	public List<AsMngVo> selectASList(AsMngVo asMngVo) {
		return asRepository.selectASList(asMngVo);
	}
	
	/**
	 * 장비 코드 리스트 조회
	 * @return : 장비 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectMachineCd() {
		return asRepository.selectMachineCd();
	}

	/**
	 * AS상태 코드 리스트 조회
	 * @return : AS상태 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectAsStatusCd() {
		// TODO Auto-generated method stub
		return asRepository.selectAsStatusCd();
	}

	/**
	 * 지역 코드 리스트 조회
	 * @return : 지역 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectLocationCd() {
		return asRepository.selectLocationCd();
	}

}
