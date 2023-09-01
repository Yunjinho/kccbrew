package kr.co.kccbrew.sysMng.cdMng.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-08-29			배수연				최초생성
 * @author BAESOOYEON
 *@version 1.0
 */
@Controller 
@RequiredArgsConstructor
public class CdMngController {
	/**
	 * codeMngService변수 생성
	 */
	private final ICdMngService codeMngService;

	/* 코드 조회 */
	@RequestMapping("/code")
	public String selectAll(@ModelAttribute("searchContent") CdMngVo searchContent,Model model) {
		List<CdMngVo> list = codeMngService.filter(searchContent);
			
			model.addAttribute("list", list);
		return "sysMng/cdMng/cdMngList";
	}

	/* 상세코드정보 */
	@RequestMapping(value = "/code/{cdId}/{cdDtlId}")
	public String selectDetail(Model model, @PathVariable String cdId, @PathVariable String cdDtlId) {
		CdMngVo codeMng = codeMngService.selectDetail(cdId, cdDtlId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/cdMngDtl";
	}

	/* 코드등록 */
	@RequestMapping(value = "/code/insert", method = RequestMethod.GET)
	public String insert(CdMngVo codeMng, Model model) {
		List<CdMngVo> list = codeMngService.selectNm();
		model.addAttribute("List", list);
		model.addAttribute("codeMng", new CdMngVo());
		return "sysMng/cdMng/cdMngIns";
	}

	/* 그룹코드등록 */
	@RequestMapping(value = "/code/insert1", method = RequestMethod.POST)
	public String insert1(CdMngVo codeMng, HttpSession session, Model model, RedirectAttributes redirect) {
		codeMng.setCdRegUser("test");
		codeMng.setCdModUser("test");
		codeMngService.insert1(codeMng);
		model.addAttribute("CodeMng", codeMng);
		return "redirect:/code";
	}

	/* 상세코드등록 */
	@RequestMapping(value = "/code/insert2", method = RequestMethod.POST)
	public String insert2(CdMngVo codeMng, @RequestParam String cdId, Model model, RedirectAttributes redirect) {
		codeMng.setCdDtlRegUser("test");
		codeMng.setCdDtlModUser("test");
		codeMngService.insert2(codeMng);
		model.addAttribute("CodeMng", codeMng);
		return "redirect:/code";
	}

	/* 상세코드수정 */
	@RequestMapping(value = "/code/update/{cdId}/{cdDtlId}", method = RequestMethod.GET)
	public String update(Model model, @PathVariable String cdId, @PathVariable String cdDtlId)  {
		CdMngVo codeMng = codeMngService.selectDetail(cdId, cdDtlId);
		List<CdMngVo> list = codeMngService.selectNm();
		model.addAttribute("List", list);
		model.addAttribute(codeMng);
		return "sysMng/cdMng/cdMngMod";
	}

	/* 상세코드수정 */
	@RequestMapping(value = "/code/update", method = RequestMethod.POST)
	public String update(CdMngVo codeMng, Model model, RedirectAttributes redirectAttrs) {
		codeMng.setCdDtlModUser("test");
		codeMngService.update(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}

	/* 그룹코드정보 */
	@RequestMapping(value = "/code/{cdId}")
	public String selectGrpDetail(Model model, @PathVariable String cdId) {
		CdMngVo codeMng = codeMngService.selectGrpDetail(cdId);
		model.addAttribute("codeMng", codeMng);
		return "sysMng/cdMng/grpCdMngDtl";
	}

	/* 그룹코드수정 */
	@RequestMapping(value = "/code/update/{cdId}", method = RequestMethod.GET)
	public String grpUpdate(Model model, @PathVariable String cdId)  {
		CdMngVo codeMng = codeMngService.selectGrpDetail(cdId);
		model.addAttribute(codeMng);
		return "sysMng/cdMng/grpCdMngMod";
	}

	/* 그룹코드수정 */
	@RequestMapping(value = "/code/grpupdate", method = RequestMethod.POST)
	public String grpUpdate(CdMngVo codeMng, Model model, RedirectAttributes redirectAttrs) {
		codeMng.setCdModUser("test");
		codeMngService.grpUpdate(codeMng);
		model.addAttribute("codeMng", codeMng);
		return "redirect:/code";
	}
	
}
