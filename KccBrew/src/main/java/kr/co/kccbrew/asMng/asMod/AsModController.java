package kr.co.kccbrew.asMng.asMod;

import java.io.File;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AsModController {
	private final IAsModService asModService;
	private final IAsMngService asMngService;

	@RequestMapping(value = "/as-mod", method = RequestMethod.GET)
	public String asMod(@Value("#{serverImgPath['localPath']}") String localPath,
			@Value("#{serverImgPath['asReceiptPath']}") String path, @RequestParam String asInfoSeq, @RequestParam String asAssignSeq, Model model,
			HttpSession session) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq, asAssignSeq);
		vo.setLocalSavePath(localPath + path);
		session.setAttribute("asInfoSeq", asInfoSeq);
		session.setAttribute("fileSeq", vo.getFileSeq());
		model.addAttribute("asInfoSeq", asInfoSeq);
		model.addAttribute("asAssignSeq", asAssignSeq);
		// 접수사진
		if (vo.getFileSeq() != null) {
			List<AsMngVo> list = asMngService.selectAsImg(vo.getFileSeq());
			model.addAttribute("asInfoImgList", list);
			model.addAttribute("fileSeq", vo.getFileSeq());
		}
		model.addAttribute("asDetailInfo", vo);
		List<AsMngVo> list = asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		return "asMng/asMod2";
	}

	@RequestMapping(value = "/as-mod", method = RequestMethod.POST)
	public String asMod(@Value("#{serverImgPath['localPath']}") String localPath,
			@Value("#{serverImgPath['asReceiptPath']}") String path, AsMngVo asMngVo, HttpServletRequest request,
			Principal principal) {

		String folderPath = request.getServletContext().getRealPath("") + path;
		File folder = new File(folderPath);
// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder.exists()) {
			boolean success = folder.mkdirs(); // 폴더 생성 메소드
		}
		String userId = principal.getName();
		
		HttpSession session = request.getSession();
		
		String asInfoSeq = (String) session.getAttribute("asInfoSeq");
		String imgSeq = (String) session.getAttribute("fileSeq");
		asMngVo.setAsInfoSeq(asInfoSeq);
		asMngVo.setUserId(userId);
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		System.out.println("=============================================================");
		System.out.println(folderPath);
		System.out.println(path);
//local 저장 위치 배포할땐 삭제
		File folder2 = new File(localPath + path);
// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder2.exists()) {
			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
		}
		asMngVo.setLocalSavePath(localPath + path);
		asModService.deleteFile(asMngVo, imgSeq);
		asModService.asMod(asMngVo);
		
		
		
		return "redirect:/as-detail?asInfoSeq=" + asInfoSeq + "&asAssignSeq=";
	}
	
	

	@GetMapping("/getAsInfoImages")
	@ResponseBody
	public List<AsMngVo> getAsInfoImages(@RequestParam String asInfoSeq, @RequestParam String asAssignSeq) {
	    // 이미지 정보를 가져와서 List<AsMngVo> 형태로 반환\
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq, asAssignSeq);
		System.out.println(vo.getFileSeq());
		System.out.println("============================");
	    List<AsMngVo> imageList = asMngService.selectAsImg(vo.getFileSeq());
	    return imageList;
	}

}