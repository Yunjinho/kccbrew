package kr.co.kccbrew.comm.security.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.strMng.model.StrMngVo;

@Mapper
@Repository
public interface IUserRepository {
    
	/*회원가입*/
    public void registerUser(UserVo userVo);
    
    /*ID로 회원정보 조회*/
    public UserVo getUserById(String userId);
    
    /*점주ID로 지점정보 조회*/
    public List<StrMngVo> getStoreById(String userId);
    
    /**
	 * 운영중인 점포 리스트 조회
	 * @param keyword : 검색 키워드
	 * @param start : 시작 번호
	 * @param end : 끝 번호
	 * @return 운영중인 점포 리스트
	 */
	public List<UserVo> selectStoreList(@Param("keyword")String keyword,@Param("start")int start,@Param("end")int end);
	
	/**
	 * 
	 * @param keyword : 검색 키워드
	 * @return 키워드로 검색된 점포 목록 갯수
	 */
	public int countStoreList(@Param("keyword")String keyword);
	
	/**
	 * 사용중인 장비군 목록 조회
	 * @return 사용중인 장비군 목록 리스트
	 */
	public List<UserVo> selectMechineCode();
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 입력한 아이디를 조회했을 때 갯수 
	 */
	public int checkUserId(@Param("userId")String userId);
	
	/**
	 * 지역코드 목록 조회
	 * @return 지역 코드 목록
	 */
	public List<UserVo> selectLocationCd();
	
	/**
	 * 지역코드 상세 목록 조회
	 * @return 지역코드 상세
	 */
	public List<UserVo> selectLocationDtlCd(@Param("locationCd")String locationCd);
	
	/**
	 * 사용자 사진 파일 기본 정보 등록
	 * @param vo : 사용자 사진 정보를 담고있는 vo
	 */
	/*public void insertFileInfo(RegisterVo vo);*/
	public void insertFileInfo(UserVo vo);
	
	/**
	 * 사용자 사진 파일 상세 정보 등록
	 * @param vo : 사용자 사진 정보를 담고있는 vo
	 */
	public void insertFileDtlInfo(UserVo vo);
	
	/**
	 * 점주와 점포 mapper 테이블 등록
	 * @param userId : 회원가입한 사용자 아이디
	 * @param StoreSeq : 선택한 점포 
	 */
	public void insertStoreUserMap(@Param("userId")String userId,@Param("storeId")int StoreSeq);
	
	/**
	 * 회원ID에 따른 AS신청(배정전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *  @return List<AsMngVo>: AS신청(배정전) 리스트
	 */
	public List<AsMngVo>selectAsAccepting(String userId);
	/**
	 * 회원ID에 따른 AS배정(처리전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *@return List<AsMngVo>: AS배정(처리전) 리스트
	 */
	public List<AsMngVo>selectAsSubmissionCompleted(String userId);
	/**
	 * 회원ID에 따른 AS처리완료 리스트 조회
	 * @param userId : 사용자 아이디@return
	 * @return List<AsMngVo>:  AS처리완료 리스트
	 */
	public List<AsMngVo>selectAsProcessingCompleted(String userId);
    
}
