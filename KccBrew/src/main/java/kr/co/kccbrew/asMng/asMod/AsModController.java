package kr.co.kccbrew.asMng.asMod;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AsModController {
	private final IAsModService asModService;
	private final IAsMngService asMngService;
	
	@RequestMapping(value="/as-mod/{asInfoSeq}",method=RequestMethod.GET)
	public String asMod(@RequestParam String asInfoSeq, Model model,HttpServletRequest request) {
//		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq);
//		if(vo.getFileSeq()!=null) {
//			List<AsMngVo> list=asMngService.selectAsInfoImg(vo.getFileSeq());
//			model.addAttribute("asInfoImgList", list);
//		}
//		model.addAttribute("asDetailInfo", vo);
//		List<AsMngVo> list=asMngService.selectLocationCd();
//		model.addAttribute("locationCd", list);
		return "asDetail";
	}

}
