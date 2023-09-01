package kr.co.kccbrew.strMng.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.kccbrew.strMng.model.StrMngVo;


public interface IStrMngService {
	List<StrMngVo> storeAll();
	List<StrMngVo> locationNm();
	List<StrMngVo> locationNmSeoul();
	List<StrMngVo> ownerList(int storeSeq);
	List<StrMngVo> search(@Param("keyword") String keyword); 
	StrMngVo storeDetail(int storeSeq);
	void insert(StrMngVo store);
	void update(StrMngVo store);
	StrMngVo selectStoreByName(String storeNm);

}
