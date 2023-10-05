package kr.co.kccbrew.asMng.contorller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import kr.co.kccbrew.comm.security.model.UserVo;
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
 * 2023-09-13			윤진호				AS 기사 배정
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
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());
		System.out.println(asMngVo);
		
		
		List<AsMngVo> list=asMngService.selectCd("M");
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectCd("S");
		model.addAttribute("asStatusCd", list);

		asMngVo.setUserTypeCd(userVo.getUserTypeCd());
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
		return "asList";
	}
	
	
	
	
	/**
	 * 
	 * @param asMngVo : select 조회 조건으로 검색한 AS 목록
	 * @return
	 */
	@RequestMapping(value="/searchAsList",method=RequestMethod.GET)
	public String ssearchAsList(AsMngVo asMngVo,Model model,HttpSession session) {
		List<AsMngVo> list=asMngService.selectCd("M");
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectCd("S");
		model.addAttribute("asStatusCd", list);
		
		list=asMngService.selectCd("L");
		model.addAttribute("locationCd", list);
		
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());

		asMngVo.setUserTypeCd(userVo.getUserTypeCd());
		List<AsMngVo> asList=asMngService.selectASList(asMngVo,asMngVo.getCurrentPage());

		int totalPage = 0;
		int totalCount = asMngService.countASList(asMngVo);

		//   paging처리
		if (asList != null && !asList.isEmpty()) {
			totalPage = (int) Math.ceil(totalCount/10.0);
		} 

		int startPage=((int)Math.ceil(asMngVo.getCurrentPage()/10) * 10) + 1;
		int endPage=((int)Math.ceil(asMngVo.getCurrentPage()/10) + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", asMngVo.getCurrentPage());
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("searchContent",asMngVo);
		model.addAttribute("ASList",asList);
		
		
		return "asList";
	}
	/**
	 * AS 접수 페이지 이동
	 */
	@RequestMapping(value="/as-receipt",method=RequestMethod.GET)
	public String asReceipt(Model model,HttpSession session) {
		List<AsMngVo> list=asMngService.selectCd("M");
		model.addAttribute("machineCd", list);

		UserVo userVo=(UserVo)session.getAttribute("user");

		List<AsMngVo> vo=asMngService.selectStrInfo(userVo.getUserId());
		model.addAttribute("strInfo", vo);
		return "asReceipt";
	}
	/**
	 * AS 접수
	 */
	@RequestMapping(value="/receipt",method=RequestMethod.POST)
	public String asReceipt(@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['asReceiptPath']}")String path,AsMngVo asMngVo,HttpServletRequest request) {

		String folderPath=request.getServletContext().getRealPath("")+path;
		HttpSession session=request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		asMngVo.setLocalSavePath(localPath+path);
		
		asMngService.insertAs(asMngVo);
		return "redirect:/as-list";
	}
	
	/** 
	 * as 상세 조회
	 */
	@RequestMapping(value="/as-detail",method=RequestMethod.GET)
	public String asDetail(@RequestParam String asInfoSeq,@RequestParam String asAssignSeq, Model model,HttpServletRequest request) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq,asAssignSeq);
		//접수사진
		if(vo.getFileSeq()!=null) {
			List<AsMngVo> list=asMngService.selectAsImg(vo.getFileSeq());
			model.addAttribute("asInfoImgList", list);
		}
		//결과사진
		if(vo.getResultFileSeq()!=null) {
			List<AsMngVo> list=asMngService.selectAsImg(vo.getResultFileSeq());
			model.addAttribute("asResultImgList", list);
		}
		model.addAttribute("asDetailInfo", vo);
		List<AsMngVo> list=asMngService.selectCd("L");
		model.addAttribute("locationCd", list);
		return "asDetail";
	}
	
	/**
	 * AS건에 수리기사 배정 후 상태 변경
	 */
	@RequestMapping(value="/as-assign",method=RequestMethod.POST)
	public String asDetail(AsMngVo asMngVo, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());

		asMngVo.setAsStatusCd("03");
		asMngVo=asMngService.insertAsAssign(asMngVo);
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq()+"&asAssignSeq="+asMngVo.getAsAssignSeq();
	}
	
	/**
	 * 반려 신청
	 */
	@RequestMapping(value="/reject",method=RequestMethod.POST)
	public String reject(String asInfoSeq,@RequestParam(defaultValue = "0")String asAssignSeq,String rejectRs,HttpServletRequest request) {
		HttpSession session=request.getSession();

		UserVo userVo=(UserVo)session.getAttribute("user");
		String userTypeCd=userVo.getUserTypeCd();
		String userId=userVo.getUserId();


		if(userTypeCd.equals("01")) {
			asMngService.updateInfoReject(asInfoSeq, rejectRs,userId);
		}else if(userTypeCd.equals("03")){
			asMngService.updateAssignReject(asAssignSeq, rejectRs,userId);
		}
		return "redirect:/as-detail?asInfoSeq="+asInfoSeq+"&asAssignSeq="+asAssignSeq;
	}
	
	/**
	 * AS 결과 입력
	 * @return
	 */
	@RequestMapping(value="/insertResult",method=RequestMethod.POST)
	public String insertResult(@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['asResultPath']}")String path,AsMngVo asMngVo,HttpServletRequest request) {
		String folderPath=request.getServletContext().getRealPath("")+path;
		HttpSession session=request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		asMngVo.setLocalSavePath(localPath+path);
		
		asMngService.insertAsResult(asMngVo);
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq()+"&asAssignSeq="+asMngVo.getAsAssignSeq();
	}
	/**
	 * AS 만족도 재신청 여부 입력
	 */
	@RequestMapping(value="/as-result-mng",method=RequestMethod.POST)
	public String asResultMng(AsMngVo asMngVo,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo sessionVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(sessionVo.getUserId());
		asMngService.updateResultMng(asMngVo);
		return "redirect:/as-list";
	}
	/**
	 * 기사가 신청한 반려건 처리
	 */
	@ResponseBody
	@RequestMapping(value="/reject-confirm",method=RequestMethod.POST)
	public String rejectConfirm(String mechanicId,String visitDttm,String asAssignSeq,String asInfoSeq,String flag,HttpServletRequest request) {
		AsMngVo asMngVo=new AsMngVo();
		HttpSession session=request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());

		asMngVo.setMechanicId(mechanicId);
		asMngVo.setVisitDttm(visitDttm);
		asMngVo.setAsAssignSeq(asAssignSeq);
		asMngService.updateRejectConfirm(asMngVo,flag);
		return "";
	}
	
	
	/** 
	 * 점포 검색
	 */
	@ResponseBody
	@RequestMapping(value="/search-location-cd" , method=RequestMethod.POST)
	public JSONArray searchLocationCode(String locCd) {
		List<AsMngVo> list=asMngService.selectLocationDtlCd(locCd);
		JSONArray result = new JSONArray();
		for(AsMngVo l:list) {
			result.add(l);
		}
		return result;
	}
	/**
	 *  점포 휴무 체크 
	 */
	@ResponseBody
	@RequestMapping(value="/check-str-schedule" , method=RequestMethod.POST)
	public JSONObject checkStrSchedule(String strMngId,String visitDttm) {
		HashMap<String, Integer> check= new HashMap<>();
		int count=asMngService.checkStrSchedule(visitDttm, strMngId);
		check.put("count", count);
		JSONObject result=new JSONObject(check);
		return result;
	}
	/** 
	 * 수리기사 조회
	 */
	@ResponseBody
	@RequestMapping(value="/search-mecha" , method=RequestMethod.POST)
	public JSONObject searchMecha(String locationCd,String visitDttm,String machineCd) {
		HashMap<String, Object> mechaList= new HashMap<>();
		List<AsMngVo> list = asMngService.selectMechList(visitDttm, locationCd,machineCd);
		mechaList.put("mechaList", list);
		JSONObject result=new JSONObject(mechaList);
		return result;
	}
	
	/** 
	 * 조회한 list 다운로드 
	 */
	@ResponseBody
	@RequestMapping(value="/download-list" , method=RequestMethod.POST)
	public void downloadList(String flag,@RequestParam(defaultValue = "1")String currentPage,
			@RequestParam(defaultValue = "")String startYr,@RequestParam(defaultValue = "")String startMn,
			@RequestParam(defaultValue = "")String endYr,@RequestParam(defaultValue = "")String endMn,
			@RequestParam(defaultValue = "")String asInfoSeq,@RequestParam(defaultValue = "")String storeNm,
			@RequestParam(defaultValue = "")String storeAddr,@RequestParam(defaultValue = "")String searchId,
			@RequestParam(defaultValue = "")String machineCd,@RequestParam(defaultValue = "")String asStatusCd,HttpServletRequest request,HttpServletResponse response) {
		//세션값 받기
		HttpSession session=request.getSession();
		UserVo user=(UserVo)session.getAttribute("user");
		AsMngVo vo =new AsMngVo();
		vo.setStartYr(startYr);
		vo.setStartMn(startMn);
		vo.setEndMn(endMn);
		vo.setAsInfoSeq(asInfoSeq);
		vo.setStoreNm(storeNm);
		vo.setStoreAddr(storeAddr);
		vo.setSearchId(searchId);
		vo.setMachineCd(machineCd);
		vo.setAsStatusCd(asStatusCd);
		vo.setUserId(user.getUserId());
		vo.setUserTypeCd(user.getUserTypeCd());
		asMngService.downloadExcel(response,vo, flag,currentPage);
	}
	
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
		List<AsMngVo> list = asMngService.selectCd("M");
		model.addAttribute("machineCd", list);
		return "asMod";
	}

	@RequestMapping(value = "/as-mod", method = RequestMethod.POST)
	public String asMod(@Value("#{serverImgPath['localPath']}") String localPath,
			@Value("#{serverImgPath['asReceiptPath']}") String path, AsMngVo asMngVo, HttpServletRequest request,
			Principal principal) {

		String folderPath = request.getServletContext().getRealPath("") + path;
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
		asMngVo.setLocalSavePath(localPath + path);
		asMngService.deleteFile(asMngVo, imgSeq);
		asMngService.asMod(asMngVo);
		
		
		
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



