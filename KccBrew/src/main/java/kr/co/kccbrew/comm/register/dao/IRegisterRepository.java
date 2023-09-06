package kr.co.kccbrew.comm.register.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.comm.register.model.RegisterVo;

public interface IRegisterRepository {
	/**
	 * 운영중인 점포 리스트 조회
	 * @param keyword : 검색 키워드
	 * @param start : 시작 번호
	 * @param end : 끝 번호
	 * @return 운영중인 점포 리스트
	 */
	public List<RegisterVo> selectStoreList(@Param("keyword")String keyword,@Param("start")int start,@Param("end")int end);
	
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
	public List<RegisterVo> selectMechineCode();
	
	/**
	 * 아이디 중복 체크
	 * @param userId
	 * @return 입력한 아이디를 조회했을 때 갯수 
	 */
	public int checkUserId(@Param("userId")String userId);
	
	/**
	 * 사용자 회원가입
	 * @param user : 회원가입 정보가 담긴 Vo
	 */
	public void registerUser(RegisterVo user);
	
	/**
	 * 지역코드 목록 조회
	 * @return 지역 코드 목록
	 */
	public List<RegisterVo> selectLocationCd();
	
	/**
	 * 지역코드 상세 목록 조회
	 * @return 지역코드 상세
	 */
	public List<RegisterVo> selectLocationDtlCd(@Param("locationCd")String locationCd);
	
	/**
	 * 사용자 사진 파일 기본 정보 등록
	 * @param vo : 사용자 사진 정보를 담고있는 vo
	 */
	public void insertFileInfo(RegisterVo vo);
	
	/**
	 * 사용자 사진 파일 상세 정보 등록
	 * @param vo : 사용자 사진 정보를 담고있는 vo
	 */
	public void insertFileDtlInfo(RegisterVo vo);
	
	/**
	 * 점주와 점포 mapper 테이블 등록
	 * @param userId : 회원가입한 사용자 아이디
	 * @param StoreSeq : 선택한 점포 
	 */
	public void insertStoreUserMap(@Param("userId")String userId,@Param("storeId")int StoreSeq);
}
