package kr.co.kccbrew.sysMng.cdMng.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		List<CdMngVo> list = cdMngService.grcdList();
		
		model.addAttribute("List", List);
		
System.out.println(List);
System.out.println(list);
		model.addAttribute("list", list);
		return "adminCodeManage";
	}

	@RequestMapping(value = "/code/dtl/{cdId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<CdMngVo> selectCd(@PathVariable("cdId") String cdId) {
		System.out.println(cdId);
		List<CdMngVo> list = cdMngService.grcdDtlList(cdId);
		return list;
	}

	/* 상세코드정보 */
	@RequestMapping(value = "/code/{cdId}/{cdDtlId}", method = RequestMethod.GET)
	public String selectCd(Model model, @PathVariable("cdId") String cdId, @PathVariable("cdDtlId") String cdDtlId) {
		CdMngVo codeMng = cdMngService.selectCd(cdId, cdDtlId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/cdMngDtl";
	}

	/* 그룹코드등록 */
	@RequestMapping(value = "/code/insert1", method = RequestMethod.POST)
	public String insert1(CdMngVo codeMng, HttpSession session, Model model, RedirectAttributes redirect,
			Principal principal) {
		String userId = principal.getName();
		codeMng.setCdRegUser(userId);
		codeMng.setCdModUser(userId);
		cdMngService.insert1(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/grpCdMngDtl";
	}

	/* 상세코드등록 */
	@RequestMapping(value = "/code/insert2", method = RequestMethod.POST)
	public String insert2(@RequestParam("cdId") String cdId, @RequestParam("cdDtlNm") String cdDtlNm,
			@RequestParam("cdDtlId") String cdDtlId, Model model, RedirectAttributes redirect, Principal principal) {
		String userId = principal.getName();

		// CdMngVo 객체에 값을 설정
		CdMngVo codeMng = new CdMngVo();
		codeMng.setCdId(cdId);
		codeMng.setCdDtlNm(cdDtlNm);
		codeMng.setCdDtlId(cdDtlId);
		codeMng.setCdDtlRegUser(userId);
		codeMng.setCdDtlModUser(userId);
		cdMngService.insert2(codeMng);

		// 성공 응답
		return "sysMng/cdMng/cdMngDtl";

	}

	/* 상세코드수정 */
	@RequestMapping(value = "/code/update", method = RequestMethod.POST)
	public String cdMod(CdMngVo codeMng, Model model, RedirectAttributes redirectAttrs, HttpSession session,
			Principal principal) {
		String userId = principal.getName();
		codeMng.setCdDtlModUser(userId);
		cdMngService.cdMod(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/cdMngDtl";
	}

	/* 그룹코드정보 */
	@RequestMapping(value = "/code/{cdId}", method = RequestMethod.GET)
	public String selectGrpDetail(Model model, @PathVariable String cdId) {
		CdMngVo codeMng = cdMngService.selectGrpDetail(cdId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/grpCdMngDtl";
	}

	/* 그룹코드수정 */
	@RequestMapping(value = "/code/grpupdate", method = RequestMethod.POST)
	public String grpUpdate(HttpSession session, Principal principal, CdMngVo codeMng, Model model,
			RedirectAttributes redirectAttrs) {
		String userId = principal.getName();
		codeMng.setCdModUser(userId);
		cdMngService.grpUpdate(codeMng);
		System.out.println(codeMng);
		System.out.println("+_+++++++++++++++++++++_+_+_+_+");
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/cdMngDtl";
	}

}
