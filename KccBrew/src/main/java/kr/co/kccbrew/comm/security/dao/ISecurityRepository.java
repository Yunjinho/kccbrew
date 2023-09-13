package kr.co.kccbrew.comm.security.dao;

public interface ISecurityRepository {

	/*ID로 사용자타입코드 조회*/
	public String selectAuthorityCode(String userId);
}
