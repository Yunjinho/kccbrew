package kr.co.kccbrew.sysMng.fileMng.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;
import kr.co.kccbrew.sysMng.fileMng.service.IFileMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : FileMngController
 * @Decription : 파일 관리하기 위한 controller
 * 
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-08-23 배수연 최초생성
 * @author BAESOOYEON
 * @version 1.0
 */

@Controller
@RequiredArgsConstructor
public class FileMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IFileMngService fileMngService;
	
	@GetMapping("/file")
	public String fileAll(Model model, @RequestParam(defaultValue = "1") int currentPage,
			@ModelAttribute("searchContent") FileMngVo searchContent, HttpSession session) {
		List<FileMngVo> fileList = fileMngService.fileList(searchContent, currentPage);
		List<FileMngVo> fileTypeList = fileMngService.fileTypeList();
		int totalPage = 0;
		int totalLogCount = fileMngService.getFileCount(searchContent);
		double totalLogCountDouble = (double) totalLogCount;
		int sharePage = 0;
		if (fileList != null && !fileList.isEmpty()) {
			totalPage = (int) Math.ceil(totalLogCountDouble/10.0);
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

		model.addAttribute("typeList", fileTypeList);
		model.addAttribute("list", fileList);
		return "sysMng/fileMng/fileMngList";
	}
}
