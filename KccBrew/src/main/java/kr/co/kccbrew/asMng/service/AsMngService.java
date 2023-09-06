package kr.co.kccbrew.asMng.service;

import java.util.HashMap;
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
	public List<AsMngVo> selectASList(AsMngVo asMngVo,int page) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("asVo", asMngVo);
		map.put("start", page);
		map.put("end", page+9);
		return asRepository.selectASList(map);
	}
	
	/**
	 * 조회할 AS 리스트의 수
	 */
	@Override
	public int countASList(AsMngVo asMngVo) {
		return asRepository.countASList(asMngVo);
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
