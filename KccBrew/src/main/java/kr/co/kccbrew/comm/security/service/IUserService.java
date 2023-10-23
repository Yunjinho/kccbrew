package kr.co.kccbrew.comm.security.service;

import java.util.List; 

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.strMng.model.StrMngVo;

public interface IUserService {
	public void registerUser(UserVo userVo);
	/*public void insertStoreUserMap(String userId, int storeId);*/
	public UserVo insertUserImg(UserVo user);
	
	public UserVo getUserById(String userId);
    public List<StrMngVo> getStoreById(String userId);
    
    
	/**
	 * 검색한 키워드를 통해 운영하고 있는 점포 리스트를 조회한다.
	 * @return 운영중인 점포 리스트
	 */
	public List<UserVo> selectStoreList(String keyword,int page);

	/**
	 * @param keyword : 검색 키워드
	 * @return 키워드로 검색된 점포 목록 갯수
	 */
	public int countStoreList(@Param("keyword")String keyword);
	
	/**
	 * 운영하고 있는 장비군 리스트를 조회한다.
	 * @return 운영중인 장비군 리스트
	 */
	public List<UserVo> selectMechineCode();
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 입력한 아이디를 조회했을 때 갯수 
	 */
	public int checkUserId(String userId);
	
	/**
	 * 수리기사 회원가입 시 지역 코드 조회
	 * @return 지역코드 목록
	 */
	public List<UserVo> selectLocationCd();
	
	/**
	 * 선택한 지역코드의 상세 지역코드 조회
	 * @param locCd : 선택된 코드
	 * @return 선택된 코드의 지역 코드 목록
	 */
	public List<UserVo> selectLocationDtlCd(String locCd);
	
	/**
	 * 회원ID에 따른 AS신청(배정전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *  @return List<AsMngVo>: AS신청(배정전) 리스트
	 */
	public List<AsMngVo>getAsAccepting(String userId);
	/**
	 * 회원ID에 따른 AS배정(처리전) 리스트 조회
	 * @param userId : 사용자 아이디
	 *@return List<AsMngVo>: AS배정(처리전) 리스트
	 */
	public List<AsMngVo>getAsSubmissionCompleted(String userId);
	/**
	 * 회원ID에 따른 AS처리완료 리스트 조회
	 * @param userId : 사용자 아이디@return
	 * @return List<AsMngVo>:  AS처리완료 리스트
	 */
	public List<AsMngVo>getAsProcessingCompleted(String userId);
	
	
}
