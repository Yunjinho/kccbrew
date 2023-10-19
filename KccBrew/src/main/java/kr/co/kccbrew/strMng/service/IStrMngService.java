package kr.co.kccbrew.strMng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.strMng.model.StrMngVo;


public interface IStrMngService {
	List<StrMngVo> storeAll(StrMngVo strMngVo);
	List<StrMngVo> strFilter(StrMngVo strMngVo, int page);
	int getStrFilterCount(StrMngVo strMngVo);
	List<StrMngVo> locationNm();
	List<StrMngVo> locationNmSeoul();
	List<StrMngVo> ownerList(int storeSeq);
	List<StrMngVo> search(@Param("keyword") String keyword); 
	StrMngVo storeDetail(int storeSeq);
	void insert(StrMngVo store);
	void update(StrMngVo store);
	StrMngVo selectStoreByName(String storeNm);

	/**
	 * 내 계정에 등록된 점포 조회
	 * @return
	 */
	List<StrMngVo> selectMyStr(String userId);
	/**
	 * 내 계정에 점포 추가 등록
	 * @param userId
	 * @param storeSeq
	 */
	public void insertStr(@Param("userId")String userId,@Param("storeSeq")String storeSeq);
	/**
	 * 점포 제거
	 * @param userId
	 * @param storeSeq
	 */
	void deleteStr(String userId, String storeSeq);
	
	public List<StrMngVo> strAllFilter(StrMngVo strMngVo);
}
