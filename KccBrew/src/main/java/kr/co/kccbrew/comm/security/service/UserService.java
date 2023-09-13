package kr.co.kccbrew.comm.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.register.model.RegisterVo;
import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;

@Service
public class UserService implements IUserService{
	@Autowired
	private IUserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void registerUser(UserVo userVo) {
		userVo.setUserPwd(passwordEncoder.encode(userVo.getUserPwd()));
		userRepository.registerUser(userVo);
	}
	
 
}
