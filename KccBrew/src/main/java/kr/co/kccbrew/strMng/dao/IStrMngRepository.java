package kr.co.kccbrew.strMng.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.strMng.model.StrMngVo;


public interface IStrMngRepository {
	List<StrMngVo> storeAll(StrMngVo strMngVo);
	List<StrMngVo> strFilter(Map<String, Object> map);
	int getStrFilterCount(Map<String, Object> map);
	List<StrMngVo> ownerList(int storeSeq);
	List<StrMngVo> locationNm();
	List<StrMngVo> locationNmSeoul();
	List<StrMngVo> search(@Param("keyword") String keyword); 
	StrMngVo storeDetail(int storeSeq);
	void insert(StrMngVo store);
	void update(StrMngVo store);
	StrMngVo selectStoreByName(String storeNm);

}
