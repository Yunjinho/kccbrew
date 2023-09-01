package kr.co.kccbrew.comm.register.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.register.model.LocationListVo;
import kr.co.kccbrew.comm.register.model.MechineListVo;
import kr.co.kccbrew.comm.register.model.StoreListVo;
import kr.co.kccbrew.comm.register.model.UserVo;

public interface IRegisterService {
	/**
	 * 검색한 키워드를 통해 운영하고 있는 점포 리스트를 조회한다.
	 * @return 운영중인 점포 리스트
	 */
	public List<StoreListVo> selectStoreList(String keyword,int page);

	/**
	 * @param keyword : 검색 키워드
	 * @return 키워드로 검색된 점포 목록 갯수
	 */
	public int countStoreList(@Param("keyword")String keyword);
	
	/**
	 * 운영하고 있는 장비군 리스트를 조회한다.
	 * @return 운영중인 장비군 리스트
	 */
	public List<MechineListVo> selectMechineCode();
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 입력한 아이디를 조회했을 때 갯수 
	 */
	public int checkUserId(String userId);
	
	/**
	 * 사용자 회원가입
	 * @param user
	 */
	public void registerUser(UserVo user);
	
	/**
	 * 수리기사 회원가입 시 지역 코드 조회
	 * @return 지역코드 목록
	 */
	public List<LocationListVo> selectLocationCd();
	
	/**
	 * 선택한 지역코드의 상세 지역코드 조회
	 * @param locCd : 선택된 코드
	 * @return 선택된 코드의 지역 코드 목록
	 */
	public List<LocationListVo> selectLocationDtlCd(String locCd);
}
