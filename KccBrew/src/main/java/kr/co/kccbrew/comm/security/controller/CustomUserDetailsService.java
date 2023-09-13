package kr.co.kccbrew.comm.security.controller;

import java.util.ArrayList; 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.comm.security.dao.IUserRepository;
import kr.co.kccbrew.comm.security.model.UserVo;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVo userVo = userRepository.getUserById(username);
		String password = userVo.getUserPwd();
		String authorityCode = userVo.getUserTypeCd();
		String roleName=null;

		switch (authorityCode) {
		case "01":
			roleName = "ROLE_ADMIN";
			break;
		case "02":
			roleName = "ROLE_MANAGER";
			break;
		case "03":
			roleName = "ROLE_MECHA";
			break;
		default:
			roleName = "ROLE_USER"; 
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority(roleName));

		return new User(username, password, authorities);

	}
}
