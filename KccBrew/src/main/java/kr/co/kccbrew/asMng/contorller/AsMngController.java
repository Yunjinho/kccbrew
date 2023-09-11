package kr.co.kccbrew.asMng.contorller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import lombok.RequiredArgsConstructor;
/**
 * @ClassNmae : AsMngController
 * @Decription : AS 기능을 관리하기 위한 controller
 * 
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-09-05			윤진호				최초생성
 * 2023-09-06			윤진호				AS 조회 구현
 * 2023-09-07			윤진호				AS 접수
 * @author YUNJINHO
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class AsMngController {
	/**
	 * asMngService 변수 선언
	 */
	private final IAsMngService asMngService;
	
	/**
	 * AS List를 보여주는 페이지
	 * @return 
	 */
	@RequestMapping(value="/as-list",method=RequestMethod.GET)
	public String as(Model model,HttpSession session) {
		AsMngVo asMngVo=new AsMngVo();
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
		
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
		list=asMngService.selectLocationCd();
		model.addAttribute("locationCd", list);
		
		list=asMngService.selectASList(asMngVo,1);
		int totalPage = 0;
		int totalCount = asMngService.countASList(asMngVo);

		//   paging처리
		if (list != null && !list.isEmpty()) {
			totalPage = (int)Math.ceil(totalCount/10.0);
		}
		
		int startPage=(0 * 10) + 1;
		int endPage=(0 + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", 1);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("searchContent",asMngVo);
		model.addAttribute("searchContent",asMngVo);
		model.addAttribute("ASList",list);
		return "/asMng/asList";
	}
	
	
	
	
	/**
	 * 
	 * @param asMngVo : select 조회 조건으로 검색한 AS 목록
	 * @return
	 */
	@RequestMapping(value="/searchAsList",method=RequestMethod.GET)
	public String ssearchAsList(@RequestParam(defaultValue = "1") int currentPage,AsMngVo asMngVo,Model model,HttpSession session) {
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
		list=asMngService.selectLocationCd();
		model.addAttribute("locationCd", list);
		
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
		List<AsMngVo> asList=asMngService.selectASList(asMngVo,currentPage);

		int totalPage = 0;
		int totalCount = asMngService.countASList(asMngVo);

		//   paging처리
		if (asList != null && !asList.isEmpty()) {
			totalPage = (int) Math.ceil(totalCount/10.0);
		} 

		int startPage=((int)Math.ceil(currentPage/10) * 10) + 1;
		int endPage=((int)Math.ceil(currentPage/10) + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("searchContent",asMngVo);
		model.addAttribute("ASList",asList);
		
		return "/asMng/asList";
	}
	
	@RequestMapping(value="/as-receipt",method=RequestMethod.GET)
	public String asReceipt(Model model,HttpSession session) {
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		AsMngVo vo=asMngService.selectStrInfo((String)session.getAttribute("userId"));
		model.addAttribute("strInfo", vo);
		
		return "/asMng/asReceipt";
	}

	@RequestMapping(value="/receipt",method=RequestMethod.POST)
	public String asReceipt(@Value("#{serverImgPath['asReceiptPath']}")String path,AsMngVo asMngVo,HttpSession session, HttpServletRequest request) {
		String realPath = request.getServletContext().getRealPath("/");
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setStorageLocation(path);
		asMngService.insertAs(asMngVo);
		return "/asMng/asList";
	}
	
}

