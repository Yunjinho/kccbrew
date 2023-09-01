package kr.co.kccbrew.strMng.controller;


import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.strMng.model.StrMngVo;
import kr.co.kccbrew.strMng.service.IStrMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : StoreController
 * @Decription : 코드 관리하기 위한 controller
 * 
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-08-28			배수연				최초생성
 * @author BAESOOYEON
 *@version 1.0
 */
@Controller
@RequiredArgsConstructor
public class StrMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IStrMngService storeService;
	
	/* 점포조회 */
	@RequestMapping("/store")
	public String storeAll(Model model) {
		List<StrMngVo> list = storeService.storeAll();
		model.addAttribute("list", list);
		return "strMng/strMngList";
	}
	
	/* 점포정보 */
	@RequestMapping("/store/view/{storeSeq}")
	public String storeDetail(@PathVariable int storeSeq, Model model) {
		StrMngVo store = storeService.storeDetail(storeSeq);
		List<StrMngVo> list = storeService.ownerList(storeSeq);
		model.addAttribute("list",list);
		model.addAttribute(store);
		return "strMng/strMngDtl";
	}
	
	/* 점포등록 */
	@RequestMapping(value="/store/insert", method= RequestMethod.GET)
	public String insert(Model model){
		List<StrMngVo> list = storeService.locationNm();
		List<StrMngVo> seoullist = storeService.locationNmSeoul();//점포상세리스트
		model.addAttribute("list",list);
		model.addAttribute("store", new StrMngVo());
		model.addAttribute("seoullist",seoullist);
		return "strMng/strMngIns";
	}
	
	/* 점포등록 */
	@RequestMapping(value="/store/insert", method= RequestMethod.POST)
	public String insert(StrMngVo store, Model model){
		store.setRegUser("test");
		store.setModUser("test");
		storeService.insert(store);
		model.addAttribute(store);
		return "redirect:/store";
	}
	
	/* 점포수정 */
	@RequestMapping(value="/store/update/{storeSeq}",  method= RequestMethod.GET)
	public String update(@PathVariable int storeSeq, Model model) {
		StrMngVo store = storeService.storeDetail(storeSeq);
		List<StrMngVo> list = storeService.locationNm();
		List<StrMngVo> seoullist = storeService.locationNmSeoul();//지역상세리스트
		model.addAttribute("seoullist",seoullist);
		model.addAttribute("list",list);
		model.addAttribute(store);
		return "strMng/strMngMod";
	}
	
	/* 점포수정 */
	@RequestMapping(value="/store/update",  method= RequestMethod.POST)
	public String update(Model model, StrMngVo store) {
		store.setModUser("test");
		storeService.update(store);
		model.addAttribute(store);
		return "redirect:/store";
	}
	
	/* 점포명중복체크 */
	@RequestMapping(value = "/api/namecheck", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> nameCheck(@RequestBody HashMap<String, Object> store) {
		String storeNm = (String) store.get("storetest");

		// 데이터베이스에서 이미 존재하는지 확인
		StrMngVo existingStore = storeService.selectStoreByName(storeNm);
		if (existingStore != null) {
			// 이미 존재하는 에러 응답을 반환
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 존재하는 이름입니다.");
			return null;
		}

		logger.info("storeNm: " + store.get("storeNm"));
//		return ResponseEntity.status(HttpStatus.OK).body("사용가능한 점포명입니다.");
		return null;
	}
	
	/* 점포검색 */
	@RequestMapping(value= "/store/search")
	public String search(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
		List<StrMngVo> list = storeService.search(keyword);
		model.addAttribute("list", list);
		return "strMng/strMngSearch";
	}

}
