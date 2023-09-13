package kr.co.kccbrew.comm.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String authorityCode = null;
		String roleName;

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
			roleName = "ROLE_USER"; // 기본 권한 설정
		}

		// 사용자 정보를 생성하고 권한을 설정
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(roleName));

		return new User(username, "password", authorities);

	}
}
