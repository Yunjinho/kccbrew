package kr.co.kccbrew.comm.login.service;

import kr.co.kccbrew.comm.login.model.LogInVo;

public interface ILogInService {
	/**
	 * 사용자 로그인
	 * @param vo : 사용자가 입력한 정보가 담겨있는 vo
	 * @return : 로그인 성공 실패
	 */
	public LogInVo logIn(LogInVo vo);
}
