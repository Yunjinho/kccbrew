package kr.co.kccbrew.comm.util;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

@Service
public class ObjectUtilService implements IObjectUtilService {
	@Autowired
	private IObjectUtilRepository objectUtilRepository;

	@Override
	public String getEquipmentName(String codeId) {
		return objectUtilRepository.selectEquipmentName(codeId);
	}

	@Override
	public String getLocationName(String codeId) {
		return objectUtilRepository.selectLocationName(codeId);
	}
	
	/**
	 * 사용자타입코드에 따른 사용자타입명 조회
	 * @param codeId: 사용자타입코드
	 * @return String: 사용자타입명
	 */
	@Override
	public String getUserTypeName(String codeId) {
		return objectUtilRepository.selectUserTypeName(codeId);
	}

}
