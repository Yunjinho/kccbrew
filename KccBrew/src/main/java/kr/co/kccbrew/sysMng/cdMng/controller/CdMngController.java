package kr.co.kccbrew.sysMng.cdMng.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.kccbrew.sysMng.cdMng.model.CdMngVo;
import kr.co.kccbrew.sysMng.cdMng.service.ICdMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : CodeMngController
 * @Decription : 코드 관리하기 위한 controller
 * 
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-08-29 배수연 최초생성
 * @author BAESOOYEON
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class CdMngController {
	/**
	 * cdMngService변수 생성
	 */
	private final ICdMngService cdMngService;

	/* 코드 조회 */
	@GetMapping("/code")
	public String selectAll(@RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") CdMngVo searchContent, Model model, HttpSession session) {
		List<CdMngVo> List = cdMngService.selectNm();
		List<CdMngVo> list = cdMngService.filter(searchContent, currentPage);
		System.out.println(searchContent);
		int totalPage = 0;
		int totalLogCount = cdMngService.getCdFilterCount(searchContent);
		int sharePage = 0;
		if (list != null && !list.isEmpty()) {
			totalPage = (int) Math.ceil((double) totalLogCount / 10);
		} else {
		}

		if (currentPage == 1) {
			sharePage = 0;
		} else {
			sharePage = (currentPage - 1) / 10;
		}
		model.addAttribute("List", List);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("sharePage", sharePage);

		model.addAttribute("totalLog", totalLogCount);

		model.addAttribute("list", list);
		return "sysMng/cdMng/cdMngList";
	}

	/* 상세코드정보 */
	@RequestMapping(value = "/code/{cdId}/{cdDtlId}", method = RequestMethod.GET)
	public String selectCd(Model model, @PathVariable("cdId") String cdId, @PathVariable("cdDtlId") String cdDtlId) {
		CdMngVo codeMng = cdMngService.selectCd(cdId, cdDtlId);
		model.addAttribute("codeMng", codeMng);
		System.out.println(codeMng);
		System.out.println("---------------------------------");
		return "sysMng/cdMng/cdMngDtl";
	}

	/* 코드등록 */
	@RequestMapping(value = "/code/insert", method = RequestMethod.GET)
	public String insert(CdMngVo codeMng, Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId"); // 세션에서 아이디
		String userTypeCd = (String) session.getAttribute("userTypeCd");
		if (userId != null && !userId.equals("")) {
			model.addAttribute("userId", userId);
			List<CdMngVo> list = cdMngService.selectNm();
			model.addAttribute("List", list);
			model.addAttribute("codeMng", new CdMngVo());
			return "sysMng/cdMng/cdMngIns";
		} else {
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "loginpage";
		}
	}

	/* 그룹코드등록 */
	@RequestMapping(value = "/code/insert1", method = RequestMethod.POST)
	public String insert1(CdMngVo codeMng, HttpSession session, Model model, RedirectAttributes redirect) {
		String userId = (String) session.getAttribute("userId");
		codeMng.setCdRegUser(userId);
		codeMng.setCdModUser(userId);
		cdMngService.insert1(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}

	/* 상세코드등록 */
	@RequestMapping(value = "/code/insert2", method = RequestMethod.POST)
	public String insert2(CdMngVo codeMng, @RequestParam String cdId, Model model, RedirectAttributes redirect,  HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		codeMng.setCdDtlRegUser(userId);
		codeMng.setCdDtlModUser(userId);
		cdMngService.insert2(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}

	/* 상세코드수정 */
	@RequestMapping(value = "/code/update/{cdId}/{cdDtlId}", method = RequestMethod.GET)
	public String cdMod(Model model, HttpSession session, @PathVariable("cdId") String cdId, @PathVariable("cdDtlId") String cdDtlId) {
		String userId = (String) session.getAttribute("userId");
		//String userTypeCd = (String) session.getAttribute("userTypeCd"); 로그인 구현 완료 이후에 주석 해제
		//로그인
		if (userId != null && !userId.equals("")) {
		CdMngVo codeMng = cdMngService.selectCd(cdId, cdDtlId);
		List<CdMngVo> list = cdMngService.selectNm();
		model.addAttribute("List", list);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/cdMngMod";}
		else {
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "loginpage";
		}
	}

	/* 상세코드수정 */
	@RequestMapping(value = "/code/update", method = RequestMethod.POST)
	public String cdMod(CdMngVo codeMng, Model model, RedirectAttributes redirectAttrs, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		codeMng.setCdDtlModUser(userId);
		cdMngService.cdMod(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}

	/* 그룹코드정보 */
	@RequestMapping(value = "/code/{cdId}")
	public String selectGrpDetail(Model model, @PathVariable String cdId) {
		CdMngVo codeMng = cdMngService.selectGrpDetail(cdId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/grpCdMngDtl";
	}

	/* 그룹코드수정 */
	@RequestMapping(value = "/code/update/{cdId}", method = RequestMethod.GET)
	public String grpUpdate(HttpSession session, Model model, @PathVariable String cdId) {
		String userId = (String) session.getAttribute("userId");
		//String userTypeCd = (String) session.getAttribute("userTypeCd"); 로그인 구현 완료 이후에 주석 해제
		//로그인
		if (userId != null && !userId.equals("")) {
		CdMngVo codeMng = cdMngService.selectGrpDetail(cdId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/grpCdMngMod";}
		else {
			//로그아웃  로그인 페이지로 이동
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "loginpage";
		}
	}

	/* 그룹코드수정 */
	@RequestMapping(value = "/code/grpupdate", method = RequestMethod.POST)
	public String grpUpdate(HttpSession session, CdMngVo codeMng, Model model, RedirectAttributes redirectAttrs) {
		String userId = (String) session.getAttribute("userId");
		codeMng.setCdModUser(userId);
		cdMngService.grpUpdate(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}

}
