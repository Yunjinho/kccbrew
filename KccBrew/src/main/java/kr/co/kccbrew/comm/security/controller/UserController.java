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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;


@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String requestMethod(Model model) {
		return "security/loginform";
	}

	@GetMapping("/security-test")
	public String requestMethod2(Model model) {
		return  "security/security-tag-test";
	}	


	/*회원가입*/
	@GetMapping("/signup")
	public String showRegistrationForm(Model model) {
		//장비 목록
		List<UserVo> mechineList=userService.selectMechineCode();
		//지역 코드 목록
		List<UserVo> locationList=userService.selectLocationCd();
		//점포 목록
		List<UserVo> storeList=userService.selectStoreList("",1);
		int storeListCount=userService.countStoreList("");
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
		return "security/register";
	}



	@PostMapping("/register")
	public String register(UserVo userVo,@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['userPath']}")String path,HttpServletRequest request) {

		/*데이터확인*/
		System.out.println("userVo: " + userVo);

		String folderPath=request.getServletContext().getRealPath("")+path;
		File folder = new File(folderPath);
		// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder.exists()) {
			boolean success = folder.mkdirs(); // 폴더 생성 메소드
		}
		userVo.setStorageLocation(path);
		userVo.setServerSavePath(folderPath);

		//local 저장 위치 배포할땐 삭제
		File folder2 = new File(localPath+path);
		// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder2.exists()) {
			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
		}
		userVo.setLocalSavePath(localPath+path);

		userService.registerUser(userVo);

		return "redirect:/login";
	}

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
		List<UserVo> list=userService.selectLocationDtlCd(locCd);
		JSONArray result = new JSONArray();
		for(UserVo l:list) {
			result.add(l);
		}
		return result;
	}

	/** 아이디 중복 체크*/
	@ResponseBody
	@GetMapping("/check_user_id")
	public String checkUserId(@RequestParam("userId") String userId) {
		System.out.println("UserService.checkUserId - userId: " + userId);

		int count=userService.checkUserId(userId);
		System.out.println("UserService.checkUserId - count: " + count);
		return count + "";
	}

}
