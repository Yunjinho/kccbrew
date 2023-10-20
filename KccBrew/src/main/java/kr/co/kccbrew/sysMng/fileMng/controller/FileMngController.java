package kr.co.kccbrew.sysMng.fileMng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	/* 파일 리스트 */
	@GetMapping("/file")
	public String fileAll(Model model, FileMngVo fileMngVo, HttpSession session) {
		List<FileMngVo> fileList = fileMngService.fileList(fileMngVo, 1);
		List<FileMngVo> fileTypeList = fileMngService.fileTypeList();
		int totalPage = 0;
		int totalCount = fileMngService.getFileCount(fileMngVo);
		double totalCountDouble = (double) totalCount;
		if (fileList != null && !fileList.isEmpty()) {
			totalPage = (int) Math.ceil(totalCountDouble / 10.0);
		} 
		int startPage = ((fileMngVo.getCurrentPage() - 1) / 10) * 10 + 1;
		    int endPage = startPage + 9;
		    if (endPage > totalPage) {
		        endPage = totalPage;
		    }

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", 1);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		model.addAttribute("typeList", fileTypeList);
		model.addAttribute("list", fileList);
		return "adminFileManage";
	}
	
	/* 파일 검색조건 */
	@GetMapping("/file/search")
	public String fileSearch(Model model, FileMngVo fileMngVo, HttpSession session) {
		List<FileMngVo> fileList = fileMngService.fileList(fileMngVo, fileMngVo.getCurrentPage());
		List<FileMngVo> fileTypeList = fileMngService.fileTypeList();
		int totalPage = 0;
		int totalCount = fileMngService.getFileCount(fileMngVo);
		double totalCountDouble = (double) totalCount;
		if (fileList != null && !fileList.isEmpty()) {
			totalPage = (int) Math.ceil(totalCountDouble / 10.0);
		} 

		int startPage = ((fileMngVo.getCurrentPage() - 1) / 10) * 10 + 1;
	    int endPage = startPage + 9;
	    if (endPage > totalPage) {
	        endPage = totalPage;
	    }
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", fileMngVo.getCurrentPage());
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("searchContent", fileMngVo);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("typeList", fileTypeList);
		model.addAttribute("list", fileList);
		return "adminFileManage";
	}

	/* 파일 상세조회 */
	@GetMapping("/file/{fileDtlSeq}")
	public String fileDtl(@PathVariable("fileDtlSeq") int fileDtlSeq, Model model) {
		FileMngVo vo = fileMngService.fileDtl(fileDtlSeq);
		model.addAttribute("vo",vo);
	return "sysMng/fileMng/fileMngDtl";
	}
	
	/** 게시판 - 첨부파일 다운로드 */  
	@RequestMapping(value="/file/download")                 
    public ModelAndView fileDownload(@RequestParam("fileServerNm") String fileNameKey
                                    ,@RequestParam("fileOriginNm") String fileName
                                    ,@RequestParam("storageLocation") String filePath) throws Exception {
          
        /** 첨부파일 정보 조회 */
        Map<String, Object> fileInfo = new HashMap<String, Object>();
        fileInfo.put("fileNameKey", fileNameKey);
        fileInfo.put("fileName", fileName);
        fileInfo.put("filePath", filePath);
     
        return new ModelAndView("fileDownloadUtil", "fileInfo", fileInfo);
    }

}
