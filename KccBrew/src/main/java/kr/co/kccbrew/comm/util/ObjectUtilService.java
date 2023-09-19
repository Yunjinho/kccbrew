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
	
	

}
