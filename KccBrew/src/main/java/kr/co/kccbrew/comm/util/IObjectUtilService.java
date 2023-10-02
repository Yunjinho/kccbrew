package kr.co.kccbrew.comm.util;

public interface IObjectUtilService {
	public  String getEquipmentName(String codeId);
	public  String getLocationName(String codeId);
	
	/**
	 * 사용자타입코드에 따른 사용자타입명 조회
	 * @param codeId: 사용자타입코드
	 * @return String: 사용자타입명
	 */
	public String getUserTypeName(String codeId);
}
