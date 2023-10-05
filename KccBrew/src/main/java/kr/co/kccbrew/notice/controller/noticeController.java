package kr.co.kccbrew.notice.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import kr.co.kccbrew.notice.model.noticeVo;
import kr.co.kccbrew.notice.service.noticeService;

@Controller
public class noticeController {
	@Autowired
	noticeService noticeService;
	
	/**
	 * 공지사항 목록 조회
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public String showNoticeList(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				List<noticeVo> noticeList = noticeService.showAllNoticeList();
				
				model.addAttribute("noticeList", noticeList);
			}
		}
		return "notice";
	}
	
	/**
	 * 공지 등록 폼으로 이동
	 * @return
	 */
	@RequestMapping(value="/insertnotice", method=RequestMethod.GET)
	public String toInsertNotice() {
		return "adminNoticeInsert";
	}
	
	
	/**
	 * 이미지와 함께 공지 등록
	 * @param model
	 * @param noticeVo
	 * @param localPath
	 * @param path
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/insertnoticeform", method=RequestMethod.POST)
	public String insertNotice(Model model
								,@ModelAttribute noticeVo noticeVo
					            ,@Value("#{serverImgPath['localPath']}")String localPath
								,@Value("#{serverImgPath['userPath']}")String path
								,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String writerId = userDetails.getUsername();
				noticeVo.setWriterId(writerId);
				
				String folderPath=request.getServletContext().getRealPath("")+path;
				File folder = new File(folderPath);
				// 폴더가 존재하지 않으면 폴더를 생성합니다.
				if (!folder.exists()) {
					boolean success = folder.mkdirs(); // 폴더 생성 메소드
				}
				//local 저장 위치 배포할땐 삭제
				File folder2 = new File(localPath+path);
				
				// 폴더가 존재하지 않으면 폴더를 생성합니다.
				if (!folder2.exists()) {
					boolean success = folder2.mkdirs(); // 폴더 생성 메소드
				}
			
				noticeVo.setFileDetailLocation(path);
				noticeVo.setServerSavePath(folderPath);
				noticeVo.setLocalSavePath(localPath+path);
				
				noticeService.insertNotice(noticeVo);
			}
		}
		return "redirect:/notice";
	}
	
	/**
	 * 공지 상세 조회
	 * @param model
	 * @param noticeSeq
	 * @return
	 */
	@RequestMapping(value="/noticedetail/{noticeSeq}", method=RequestMethod.GET)
	public String noticeDetail(Model model, @PathVariable int noticeSeq) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				
				noticeVo noticeVo = noticeService.readNotice(noticeSeq);
				model.addAttribute("noticeVo", noticeVo);
			}
		}
		return "noticeDetail";
	}
	
	@RequestMapping(value="/delete/{noticeSeq}", method=RequestMethod.GET)
	public String deleteNotice(Model model, @PathVariable int noticeSeq) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String userId = userDetails.getUsername();
				
				noticeService.deleteNotice(noticeSeq);
			}
		}
		return "redirect:/noticelist";
	}
}
