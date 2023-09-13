package kr.co.kccbrew.comm.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.comm.security.model.UserVo;

@Mapper
@Repository
public interface IUserRepository {
    
	/*회원가입*/
    public void registerUser(UserVo userVo);
    
    /*ID로 회원정보 조회*/
    public UserVo getUserById(String userId);
}
