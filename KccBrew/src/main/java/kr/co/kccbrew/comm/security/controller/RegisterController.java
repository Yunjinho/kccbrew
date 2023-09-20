package kr.co.kccbrew.comm.security.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;
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
	@Autowired
	private UserService userService;

	/** 점포 검색 */
	@ResponseBody
	@RequestMapping(value="/search-store-list" , method=RequestMethod.GET)
	public JSONArray searchStoreList(String keyword,int page,Model model) {
		HashMap<String, Integer> pageInfo= new HashMap<String, Integer>();
		HashMap<String, UserVo> store= new HashMap<String, UserVo>();
		HashMap<String, String> searchWord= new HashMap<String, String>();

		JSONArray result = new JSONArray();

		//점포 목록
		List<UserVo> storeList=userService.selectStoreList(keyword,page);
		int storeListCount=userService.countStoreList(keyword);
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
		for(UserVo l: storeList) {
			storeJa.add(l);
		}
		result.add(storeJa);

		pageInfo.put("productCount", storeListCount);
		pageInfo.put("totalPageCount", totalPage);
		pageInfo.put("currentPage", page);
		pageInfo.put("totalPage", totalPage);
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
		JSONArray result = new JSONArray();

		if (locCd == null || locCd.equals("")) {
			return result;
			
		} else {
			List<UserVo> list=userService.selectLocationDtlCd(locCd);
			for(UserVo l:list) {
				result.add(l);
			}
			return result;
		}
	}

	/** 아이디 중복 체크*/
	@ResponseBody
	@RequestMapping(value="/check_user_id" , method=RequestMethod.GET)
	public String checkUserId(String userId) {
		int count=userService.checkUserId(userId);
		return count+"";
	}

}
