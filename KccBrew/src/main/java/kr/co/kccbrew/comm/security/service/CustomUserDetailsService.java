package kr.co.kccbrew.comm.security.service;

import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import kr.co.kccbrew.strMng.model.StrMngVo;

/**
 * @ClassNmae : CustomUserDetailsService
 * @Decription : spring security에서의 user정보 설정
 * 
 * @   수정일               수정자                      수정내용
 * ============      ==============     ==============
 * 2023-09-14			     이세은	               		     	최초생성
 * @author LEESEEUN
 * @version 1.0
 */

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

		if(userVo.getApproveYn() == null || userVo.getApproveYn().equals("N")) {
			roleName = "ROLE_NOTAPPROVED";
		}
		else {
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
			}
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(roleName));
		return new User(username, password, authorities);
	}

}

