package kr.co.kccbrew.comm.login.dao;


import kr.co.kccbrew.comm.login.model.LogInVo;

public interface ILogInRepository {
	/**
	 * 로그인
	 * @param vo : 사용자 정보 담고있는 vo
	 * @return : 로그인한 사용자의 정보를 담고있는 Vo
	 */
	public LogInVo logIn(LogInVo vo);
	
	/**
	 * 아이디에 저장되어있는 salt값으 조회한다.
	 * @param userId : 로그인할 사용자 아이디
	 * @return : 사용자 아이디 salt값
	 */
	public String selectUserSalt(String userId);

}
