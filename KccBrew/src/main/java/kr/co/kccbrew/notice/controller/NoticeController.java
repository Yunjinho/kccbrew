package kr.co.kccbrew.notice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import kr.co.kccbrew.comm.security.model.UserVo;
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
									,NoticeVo noticeVo
									,Model model
									,@RequestParam(value="nowPage", required=false)String nowPage
									,@RequestParam(value="cntPerPage", required=false)String cntPerPage
									,@RequestParam(value="searchOption", required=false)String searchOption
									,@RequestParam(value="searchText", required=false)String searchText) {
		
		int total = noticeService.countNoticeWithCon(searchOption,searchText);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		
		vo = new PagingVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));

		List<NoticeVo> list=noticeService.selectNoticeWithCon(vo.getStart(),vo.getEnd(),searchOption,searchText);
		System.out.println(vo.getStart());
		System.out.println("조회 결과의 수는" + list.size());
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", list);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("searchText", searchText);
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
				
				noticeVo.setWriterId(writerId);
				noticeVo.setModUser(writerId);
				
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
	 * 공지 사항 엑셀로 출력
	 * @param flag
	 * @param nowPage
	 * @param searchOption
	 * @param searchText
	 * @param cntPerPage
	 * @param total
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value ="/download-notice-list", method=RequestMethod.POST)
	public void downloadList(String flag
							,@RequestParam(defaultValue = "1")String nowPage
							,@RequestParam(defaultValue = "")String searchOption
							,@RequestParam(defaultValue = "")String searchText
							,@RequestParam(defaultValue = "10")String cntPerPage
							,@RequestParam(defaultValue = "")String total
							,HttpServletRequest request
							,HttpServletResponse response
							) {
		HttpSession session=request.getSession();
		UserVo user=(UserVo)session.getAttribute("user");
		NoticeVo noticeVo =new NoticeVo();
		PagingVo vo = new PagingVo();
		vo = new PagingVo( noticeService.countNoticeWithCon(noticeVo.getSearchOption(), noticeVo.getSearchText()), Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		noticeVo.setSearchOption(searchOption);
		noticeVo.setSearchText(searchText);
		noticeVo.setWriterId(user.getUserId());
	
		List<NoticeVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"순번", "제목", "작성자","작성일","조회수"});
		if(flag.equals("1")) {
			//현재 페이지 저장
			list=noticeService.selectNoticeWithCon(vo.getStart(), vo.getEnd(), noticeVo.getSearchOption(), noticeVo.getSearchText());
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2, 
	        			new Object[] {
	        					list.get(i).getNoticeSeq()
	        					,list.get(i).getNoticeTitle()
	        					,list.get(i).getWriterName()
	        					,list.get(i).getWriteDate()
	        					,list.get(i).getViews()
	        					});
	        }
		}else {
			//전체 페이지 저장
			vo = new PagingVo(noticeService.countNotice(),Integer.parseInt(nowPage), Integer.parseInt(total));
			list=noticeService.selectNotice(vo);
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2, 
	        			new Object[]
	        					{list.get(i).getNoticeSeq()
	        					,list.get(i).getNoticeTitle()
	        					,list.get(i).getWriterName()
	        					,list.get(i).getWriteDate()
	        					,list.get(i).getViews()
	        					});
	        }
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
	   // 빈 Sheet를 생성
	   XSSFSheet sheet = workbook.createSheet("공지사항 목록");
	
	   // Sheet를 채우기 위한 데이터들을 Map에 저장
	
	   // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
	   Set<Integer> keyset = data.keySet();
	   int rownum = 0;
	   	
	   for (Integer key : keyset) {
	       Row row = sheet.createRow(rownum++);
	       Object[] objArr = data.get(key);
	       int cellnum = 0;
	       for (Object obj : objArr) {
	           Cell cell = row.createCell(cellnum++);
	           if (obj instanceof String) {
	               cell.setCellValue((String)obj);
	           } else if (obj instanceof Integer) {
	               cell.setCellValue((Integer)obj);
	           } else if (obj instanceof Date) {
	        	   cell.setCellValue((Date)obj);
	        	   
	        	   CellStyle dateStyle = workbook.createCellStyle();
	               CreationHelper createHelper = workbook.getCreationHelper();
	               dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
	               cell.setCellStyle(dateStyle);
	           }
	       }
	   }
	   //cell 크기  조절
	   for (int i = 0; i < 5; i++) { 
		    sheet.autoSizeColumn(i);
		}
	
	   try {
	   	String folderPath="C:\\kccbrew";
			File folder = new File(folderPath);
	       // 폴더가 존재하지 않으면 폴더를 생성합니다.
	       if (!folder.exists()) {
	           folder.mkdirs(); // 폴더 생성 메소드
	       }
	       // 현재 날짜 구하기
	       LocalDateTime now = LocalDateTime.now();
	       // 포맷 정의
	       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	       // 포맷 적용
	       String formatedNow = now.format(formatter);
	
	       FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" , noticeVo.getWriterId()+"_"+formatedNow+"_notice_list.xlsx"));
	       workbook.write(out);
	       
	       out.close();
	   } catch (Exception e) {
	       e.printStackTrace();
	   }

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
		if(noticeVo.getFileSeq()!=null) {
			List<NoticeVo> noticeImg = noticeService.noticeImageList(noticeVo.getFileSeq());
			model.addAttribute("imgList", noticeImg);
		}
		model.addAttribute("noticeVo", noticeVo);

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
		if(noticeVo.getFileSeq()!=null) {
			List<NoticeVo> imgList=noticeService.noticeImageList(noticeVo.getFileSeq());
			model.addAttribute("imgList",imgList);
		}
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
	@RequestMapping(value="/notice-update", method=RequestMethod.POST)
	public String updateNotice(Model model
								,@ModelAttribute NoticeVo noticeVo
					            ,@Value("#{serverImgPath['localPath']}")String localPath
								,@Value("#{serverImgPath['noticePath']}")String path
								,HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		int noticeSeq = noticeVo.getNoticeSeq();
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
		return "redirect:/noticedetail/" + noticeSeq;
	}
	
}