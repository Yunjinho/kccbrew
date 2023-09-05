package kr.co.kccbrew.comm.register.controller;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.register.model.LocationListVo;
import kr.co.kccbrew.comm.register.model.MechineListVo;
import kr.co.kccbrew.comm.register.model.StoreListVo;
import kr.co.kccbrew.comm.register.model.UserVo;
import kr.co.kccbrew.comm.register.service.IRegisterService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : RegisterController
 * @Decription : 회원가입 기능을 관리하기 위한 controller
 * 
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-08-24			윤진호				최초생성
 * 2023-08-25			윤진호				회원가입 메서드 생성
 * 2023-08-28			윤진호				회원가입 로직 구현
 * @author YUNJINHO
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class RegisterController {
	/**
	 * RegisterService 변수 선언
	 */
	private final IRegisterService registerService;

	/** 회원가입 페이지로 이동*/
	@RequestMapping(value="/register" , method=RequestMethod.GET)
	public String register(Model model) {
		//장비 목록
		List<MechineListVo> mechineList=registerService.selectMechineCode();
		//지역 코드 목록
		List<LocationListVo> locationList=registerService.selectLocationCd();
		//점포 목록
		List<StoreListVo> storeList=registerService.selectStoreList("",1);
		int storeListCount=registerService.countStoreList("");
		int totalPage = 0;
		if (storeListCount > 0) {
			// 점포 목록을 5개씩 보여줄 때의 총 페이지 수
			totalPage = (int) Math.ceil(storeListCount / 5.0); 
		}
		// 페이지 수을 5개씩 보여줄 때의 총 페이지 블럭 수 ex - (1,2,3,4,5),(6,7,8,9,10) 
		int totalPageBlock = (int) (Math.ceil(totalPage / 5.0)); 
		//현재 페이지 블럭 ex)-1,2,3,4,5
		int nowPageBlock = (int) (Math.ceil(1 / 5.0));
		// 블럭의 시작 번호 ex) 1,6,11
		int startPage = (nowPageBlock-1) * 5 + 1;
		// 끝페이지
		int endPage=0;
		if(totalPage>nowPageBlock*5) {
			endPage=nowPageBlock*5;
		}else {
			endPage=totalPage;
		}
		model.addAttribute("mechineList", mechineList);
		model.addAttribute("locationList", locationList);

		model.addAttribute("totalPageCount", totalPage);
		model.addAttribute("nowPage", 1);
		model.addAttribute("totalPageBlock", totalPageBlock);
		model.addAttribute("nowPageBlock", nowPageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("storeList", storeList);
		model.addAttribute("keyword", "");
		return "/comm/register/register";
	}
	
	/** 점포 검색 */
	@ResponseBody
	@RequestMapping(value="/search-store-list" , method=RequestMethod.GET)
	public JSONArray searchStoreList(String keyword,int page,Model model) {
		HashMap<String, Integer> pageInfo= new HashMap<String, Integer>();
		HashMap<String, StoreListVo> store= new HashMap<String, StoreListVo>();
		HashMap<String, String> searchWord= new HashMap<String, String>();
		
		JSONArray result = new JSONArray();
		
		//점포 목록
		List<StoreListVo> storeList=registerService.selectStoreList(keyword,page);
		int storeListCount=registerService.countStoreList(keyword);
		int totalPage = 0;
		if (storeListCount > 0) {
			// 점포 목록을 5개씩 보여줄 때의 총 페이지 수
			totalPage = (int) Math.ceil(storeListCount / 5.0); 
		}
		// 페이지 수을 5개씩 보여줄 때의 총 페이지 블럭 수 ex - (1,2,3,4,5),(6,7,8,9,10) 
		int totalPageBlock = (int) (Math.ceil(totalPage / 5.0)); 
		//현재 페이지 블럭 ex)-1,2,3,4,5
		int nowPageBlock = (int) (Math.ceil(page / 5.0));
		// 블럭의 시작 번호 ex) 1,6,11
		int startPage = (nowPageBlock-1) * 5 + 1;
		// 끝페이지
		int endPage=0;
		if(totalPage>nowPageBlock*5) {
			endPage=nowPageBlock*5;
		}else {
			endPage=totalPage;
		}
		
		//JSON 형식으로 데이터 삽입
		JSONArray storeJa= new JSONArray();
		for(StoreListVo l: storeList) {
			storeJa.add(l);
		}
		result.add(storeJa);
		
		pageInfo.put("storeListCount", storeListCount);
		pageInfo.put("totalPageCount", totalPage);
		pageInfo.put("nowPage", page);
		pageInfo.put("totalPageBlock", totalPageBlock);
		pageInfo.put("nowPageBlock", nowPageBlock);
		pageInfo.put("startPage", startPage);
		pageInfo.put("endPage", endPage);
		JSONObject pageInfoJo= new JSONObject(pageInfo);
		result.add(pageInfoJo);
		
		searchWord.put("keyword", keyword);
		JSONObject keywordJo= new JSONObject(searchWord);

		result.add(keywordJo);
		return result;
	}
	
	/** 점포 검색 */
	@ResponseBody
	@RequestMapping(value="/search-location-code" , method=RequestMethod.GET)
	public JSONArray searchLocationCode(String locCd) {
		List<LocationListVo> list=registerService.selectLocationDtlCd(locCd);
		JSONArray result = new JSONArray();
		for(LocationListVo l:list) {
			result.add(l);
		}
		return result;
	}
	
	/** 아이디 중복 체크*/
	@ResponseBody
	@RequestMapping(value="/check_user_id" , method=RequestMethod.GET)
	public String checkUserId(String userId) {
		int count=registerService.checkUserId(userId);
		return count+"";
	}
	
	
	/** 회원가입 처리*/
	@RequestMapping(value="/register" , method=RequestMethod.POST)
	public String register(UserVo user) {
		registerService.registerUser(user);
		return "comm/login/login";
	}
	
}
