package kr.co.kccbrew.asMng.asMod;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String asMod(@PathVariable(value="asInfoSeq", required=false) String asInfoSeq, Model model,HttpSession session) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq);
		session.setAttribute("asInfoSeq", asInfoSeq);
		if(vo.getFileSeq()!=null) {
			List<AsMngVo> list=asMngService.selectAsInfoImg(vo.getFileSeq());
			model.addAttribute("asInfoImgList", list);
		}
		model.addAttribute("asDetailInfo", vo);
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		return "asMng/asMod";
	}
	
	@RequestMapping(value="/as-mod",method=RequestMethod.POST)
	public String asMod(@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['asReceiptPath']}")String path,AsMngVo asMngVo,HttpServletRequest request) {
//		String folderPath=request.getServletContext().getRealPath("")+path;
//		File folder = new File(folderPath);
//        // 폴더가 존재하지 않으면 폴더를 생성합니다.
//        if (!folder.exists()) {
//            boolean success = folder.mkdirs(); // 폴더 생성 메소드
//        }
		HttpSession session=request.getSession();
		asMngVo.setUserId((String)session.getAttribute("userId"));
		String asInfoSeq = (String)session.getAttribute("asInfoSeq");
		System.out.println("==========================================");
		System.out.println(asInfoSeq);
		asMngVo.setAsInfoSeq(asInfoSeq);
//		asMngVo.setStorageLocation(path);
//		asMngVo.setServerSavePath(folderPath);
//		//local 저장 위치 배포할땐 삭제
//		File folder2 = new File(localPath+path);
//		// 폴더가 존재하지 않으면 폴더를 생성합니다.
//		if (!folder2.exists()) {
//			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
//		}
//		asMngVo.setLocalSavePath(localPath+path);
		asModService.asMod(asMngVo);
		return "redirect:/as-detail?asInfoSeq=" + asInfoSeq;
	}

}
