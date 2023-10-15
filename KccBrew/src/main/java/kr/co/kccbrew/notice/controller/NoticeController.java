package kr.co.kccbrew.notice.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import kr.co.kccbrew.notice.dao.INoticeRepository;
import kr.co.kccbrew.notice.model.NoticeVo;
import kr.co.kccbrew.notice.model.PagingVo;
import kr.co.kccbrew.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	INoticeRepository noticeRepository;
	
	/**
	 * 공지 목록 조회 - 페이징 처리, 검색 조건 없음
	 * @param vo
	 * @param model
	 * @param nowPage
	 * @param cntPerPage
	 * @return
	 */
	@RequestMapping(value = "/noticelist", method = RequestMethod.GET)
	public String noticeList(PagingVo vo
							,Model model
							,@RequestParam(value="nowPage", required=false)String nowPage
							,@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = noticeService.countNotice();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		vo = new PagingVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", noticeService.selectNotice(vo));
		return "notice";
	}
	
	/**
	 * 공지 목록 조회 - 페이징 처리, 검색 조건 설정한 결과만 출력
	 * @param vo
	 * @param searchOption
	 * @param searchText
	 * @param model
	 * @param nowPage
	 * @param cntPerPage
	 * @return
	 */
	@RequestMapping(value = "/noticelistwithcon", method = RequestMethod.GET)
	public String noticeListWithCon(PagingVo vo
									,Model model	
									,@RequestParam(value="searchOption", required=false)String searchOption
									,@RequestParam(value="searchText", required=false)String searchText
									,@RequestParam(value="nowPage", required=false)String nowPage
									,@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = noticeService.countNoticeWithCon(searchOption,searchText);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
//		List<NoticeVo> list=noticeService.selectNoticeWithCon(vo.getStartPage(),vo.getEndPage(),searchOption,searchText);
		
		vo = new PagingVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<NoticeVo> list=noticeService.selectNoticeWithCon(1,10,searchOption,searchText);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", list);
		return "notice";
	}
	
	/**
	 * 공지 등록 페이지로 이동
	 * @return
	 */
	@RequestMapping(value="/insertnotice", method=RequestMethod.GET)
	public String toInsertNotice() {
		return "adminNoticeInsert";
	}
	
	
	/**
	 * 공지 등록
	 * @param model
	 * @param noticeVo
	 * @param localPath
	 * @param path
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/insertnoticeform", method=RequestMethod.POST)
	public String insertNotice(Model model
								,@ModelAttribute NoticeVo noticeVo
					            ,@Value("#{serverImgPath['localPath']}")String localPath
								,@Value("#{serverImgPath['noticePath']}")String path
								,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				
				String writerId = userDetails.getUsername();
				String writerName = userDetails.getUsername();
				
				noticeVo.setWriterId(writerId);
				noticeVo.setModUser(writerId);
				noticeVo.setWriterName(writerName);
				
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
		return "redirect:/noticelist";
	}
	
	/**
	 * 공지 상세 조회
	 * @param model
	 * @param noticeSeq
	 * @return
	 */
	@RequestMapping(value="/noticedetail/{noticeSeq}", method=RequestMethod.GET)
	public String noticeDetail(Model model, @PathVariable int noticeSeq) {
		NoticeVo noticeVo = noticeService.readNotice(noticeSeq);
		List<NoticeVo> noticeImg = noticeService.noticeImageList(noticeVo.getFileSeq());
		model.addAttribute("noticeVo", noticeVo);
		model.addAttribute("imgList", noticeImg);

		return "noticeDetail";
	}
	
	/**
	 * 공지 삭제
	 * @param model
	 * @param noticeSeq
	 * @return
	 */
	@RequestMapping(value="/delete/{noticeSeq}", method=RequestMethod.GET)
	public String deleteNotice(Model model, @PathVariable int noticeSeq) {
		noticeService.deleteNotice(noticeSeq);

		return "redirect:/noticelist";
	}
	
	/**
	 * 공지 수정 페이지로 이동
	 * @return
	 */
	@RequestMapping(value="/notice/update/{noticeSeq}", method=RequestMethod.GET)
	public String toUpdateNotice(Model model, 
								@PathVariable int noticeSeq) {
		NoticeVo noticeVo = noticeService.readNotice(noticeSeq);
		model.addAttribute("noticeVo", noticeVo);

		return "adminNoticeUpdate";
	}
	
	
	/**
	 * 공지 수정 
	 * @param model
	 * @param noticeVo
	 * @param localPath
	 * @param path
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/notice/update/{noticeSeq}", method=RequestMethod.POST)
	public String updateNotice(Model model
								,@PathVariable int noticeSeq
								,@ModelAttribute NoticeVo noticeVo
					            ,@Value("#{serverImgPath['localPath']}")String localPath
								,@Value("#{serverImgPath['noticePath']}")String path
								,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null) {
			Object principal = authentication.getPrincipal();
			if(principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				
				String writerId = userDetails.getUsername();
				noticeVo.setModUser(writerId);
				
				List<NoticeVo> noticeImg = noticeService.noticeImageList(noticeVo.getFileSeq());
				
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
				model.addAttribute("imgList",noticeImg);
				
				noticeService.updateNotice(noticeVo);
			}
		}
//		return "/noticedetail/" + noticeSeq;
		return "notice";
	}
	
}