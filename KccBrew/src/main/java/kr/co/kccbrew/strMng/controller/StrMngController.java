package kr.co.kccbrew.strMng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kccbrew.strMng.model.StrMngVo;
import kr.co.kccbrew.strMng.service.IStrMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : StrMngController
 * @Decription : 코드 관리하기 위한 controller
 * 
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-08-23 배수연 최초생성
 * @author BAESOOYEON
 * @version 1.0
 */



//기본 CRUD (조회 시 검색조건, 페이징 처리 전 -> 프레임, 페이징  마스터에 머지 된 후 수정 예정)

@Controller
@RequiredArgsConstructor
public class StrMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IStrMngService storeService;

	/* 점포조회 */
	/*@RequestMapping("/store")
	public String storeAll(Model model) {
		List<StrMngVo> list = storeService.storeAll(); // 점포목록
		model.addAttribute("list", list);
		return "strMng/strMngList"; // strMngList.jsp로 반환
	}*/
	
	/* 점포조회 */
	@GetMapping("/store")
	public String store(Model model,@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") StrMngVo searchContent, HttpSession session) {
		List<StrMngVo> list = storeService.strFilter(searchContent, currentPage); // 점포목록
		List<StrMngVo> List = storeService.locationNm(); // 지역코드리스트
		List<StrMngVo> seoulList = storeService.locationNmSeoul();// 상세지역코드리스트
		System.out.println("searchContent: " + searchContent);
		System.out.println("dfsssssssssssssssssssssssssssssssssssssssssssssss ");
		int totalPage = 0;
		int totalLogCount = storeService.getStrFilterCount(searchContent);
		int sharePage = 0;
		if (list != null && !list.isEmpty()) {
			totalPage = (int) Math.ceil(totalLogCount/10);
		} else {
		}

		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage-1) / 10;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalLog", totalLogCount);

		model.addAttribute("list", list);
		model.addAttribute("List", List);
		model.addAttribute("seoulList", seoulList);
		return "strMng/strMngList"; // strMngList.jsp로 반환
	}

	/* 점포정보 */
	@RequestMapping("/store/view/{storeSeq}")
	public String storeDetail(@PathVariable int storeSeq, Model model) {
		StrMngVo store = storeService.storeDetail(storeSeq); // 점포 Dtl
		List<StrMngVo> list = storeService.ownerList(storeSeq); // 점주리스트
		model.addAttribute("list", list);
		model.addAttribute("store", store);
		return "strMng/strMngDtl";
	}

	/* 점포등록 */
	@RequestMapping(value = "/store/insert", method = RequestMethod.GET)
	public String insert(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId"); //세션에서 아이디
		String userTypeCd = (String) session.getAttribute("userTypeCd"); //로그인 구현 완료 이후에 주석 해제
		System.out.println(userId);
		//로그인
		if (userId != null && !userId.equals("")) { 
			model.addAttribute("userId", userId);
			List<StrMngVo> list = storeService.locationNm(); // 지역코드리스트
			List<StrMngVo> seoullist = storeService.locationNmSeoul();// 상세지역코드리스트
			model.addAttribute("list", list);
			model.addAttribute("store", new StrMngVo());
			model.addAttribute("seoullist", seoullist);
			return "strMng/strMngIns";
			//로그아웃 로그인페이지로 이동
		} else {			
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "loginpage";

		}
	}

	/* 점포등록 */
	@RequestMapping(value = "/store/insert", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	public String insert(StrMngVo store, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		store.setRegUser(userId);
		store.setModUser(userId);
		storeService.insert(store);
		model.addAttribute("store", store);
		return "redirect:/store";
	}

	/* 점포수정 */
	@RequestMapping(value = "/store/update/{storeSeq}", method = RequestMethod.GET)
	public String update(@PathVariable int storeSeq, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		//String userTypeCd = (String) session.getAttribute("userTypeCd"); 로그인 구현 완료 이후에 주석 해제
		//로그인
		if (userId != null && !userId.equals("")) {
			model.addAttribute("userId", userId);
			StrMngVo store = storeService.storeDetail(storeSeq);
			List<StrMngVo> list = storeService.locationNm();
			List<StrMngVo> seoullist = storeService.locationNmSeoul();// 지역상세리스트
			model.addAttribute("seoullist", seoullist);
			model.addAttribute("list", list);
			model.addAttribute("store", store);
			return "strMng/strMngMod";
		} else {
			//로그아웃  로그인 페이지로 이동
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "home";
		}
	}

	/* 점포수정 */
	@RequestMapping(value = "/store/update", method = RequestMethod.POST)
	public String update(Model model, StrMngVo store, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		store.setModUser(userId);
		// storeNm에서 ',' 제거
		String storeNmWithoutComma = store.getStoreNm().replaceAll(",", "");
		store.setStoreNm(storeNmWithoutComma);
		storeService.update(store);
		model.addAttribute("store", store);
		return "redirect:/store";
	}

	/* 점포명중복체크 */
	@RequestMapping(value = "/api/namecheck", method = RequestMethod.POST)
	@ResponseBody
	public String nameCheck(@RequestBody String storeNm) {
		storeNm = storeNm.replaceAll("\"", "");
		String check;
		// 데이터베이스에서 이미 존재하는지 확인
		StrMngVo existingStore = storeService.selectStoreByName(storeNm);
		if (existingStore == null) {
			check = "1";
		} else {
			check = "2";
		}
		return check;
	}

	/* 점포검색 */
	@RequestMapping(value = "/store/search")
	public String search(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
		List<StrMngVo> list = storeService.search(keyword);
		model.addAttribute("list", list);
		return "strMng/strMngSearch";
	}

}
