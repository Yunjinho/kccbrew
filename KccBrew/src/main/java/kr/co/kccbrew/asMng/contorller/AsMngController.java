package kr.co.kccbrew.asMng.contorller;

import java.io.File; 
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		asMngVo.setUserId((String)session.getAttribute("userId"));
		
		
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
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
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
		list=asMngService.selectLocationCd();
		model.addAttribute("locationCd", list);
		
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
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
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		AsMngVo vo=asMngService.selectStrInfo((String)session.getAttribute("userId"));
		model.addAttribute("strInfo", vo);
		return "asReceipt";
	}
	/**
	 * AS 접수
	 */
	@RequestMapping(value="/receipt",method=RequestMethod.POST)
	public String asReceipt(@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['asReceiptPath']}")String path,AsMngVo asMngVo,HttpServletRequest request) {

		String folderPath=request.getServletContext().getRealPath("")+path;
		File folder = new File(folderPath);
        // 폴더가 존재하지 않으면 폴더를 생성합니다.
        if (!folder.exists()) {
            boolean success = folder.mkdirs(); // 폴더 생성 메소드
        }
		HttpSession session=request.getSession();
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		//local 저장 위치 배포할땐 삭제
		File folder2 = new File(localPath+path);
		// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder2.exists()) {
			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
		}
		asMngVo.setLocalSavePath(localPath+path);
		
		asMngService.insertAs(asMngVo);
		return "redirect:/as-list";
	}
	
	/** as 상세 조회*/
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
		List<AsMngVo> list=asMngService.selectLocationCd();
		model.addAttribute("locationCd", list);
		return "asDetail";
	}
	
	/**
	 * AS건에 수리기사 배정 후 상태 변경
	 */
	@RequestMapping(value="/as-assign",method=RequestMethod.POST)
	public String asDetail(AsMngVo asMngVo, Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		asMngVo.setUserId((String) session.getAttribute("userId"));
		asMngVo.setAsStatusCd("03");
		asMngVo=asMngService.insertAsAssign(asMngVo);
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq();
	}
	
	/**
	 * 반려 신청
	 */
	@RequestMapping(value="/reject",method=RequestMethod.POST)
	public String reject(String asInfoSeq,@RequestParam(defaultValue = "0")String asAssignSeq,String rejectRs,HttpServletRequest request) {
		HttpSession session=request.getSession();
		String userTypeCd=(String)session.getAttribute("userTypeCd");
		String userId=(String)session.getAttribute("userId");
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
		File folder = new File(folderPath);
        // 폴더가 존재하지 않으면 폴더를 생성합니다.
        if (!folder.exists()) {
            boolean success = folder.mkdirs(); // 폴더 생성 메소드
        }
		HttpSession session=request.getSession();
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		//local 저장 위치 배포할땐 삭제
		File folder2 = new File(localPath+path);
		// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder2.exists()) {
			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
		}
		asMngVo.setLocalSavePath(localPath+path);
		
		asMngService.insertAsResult(asMngVo);
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq()+"&asAssignSeq="+asMngVo.getAsAssignSeq();
	}
	/**
	 * 기사가 신청한 반려건 처리
	 */
	@ResponseBody
	@RequestMapping(value="/reject-confirm",method=RequestMethod.POST)
	public String rejectConfirm(String mechanicId,String visitDttm,String asAssignSeq,String asInfoSeq,String flag,HttpServletRequest request) {
		AsMngVo asMngVo=new AsMngVo();
		HttpSession session=request.getSession();
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setMechanicId(mechanicId);
		asMngVo.setVisitDttm(visitDttm);
		asMngVo.setAsAssignSeq(asAssignSeq);
		asMngService.updateRejectConfirm(asMngVo,flag);
		return "";
	}
	
	
	/** 점포 검색 */
	@ResponseBody
	@RequestMapping(value="/search-location-cd" , method=RequestMethod.GET)
	public JSONArray searchLocationCode(String locCd) {
		List<AsMngVo> list=asMngService.selectLocationDtlCd(locCd);
		JSONArray result = new JSONArray();
		for(AsMngVo l:list) {
			result.add(l);
		}
		return result;
	}
	/** 점포 휴무 체크 */
	@ResponseBody
	@RequestMapping(value="/check-str-schedule" , method=RequestMethod.GET)
	public JSONObject checkStrSchedule(String strMngId,String visitDttm) {
		HashMap<String, Integer> check= new HashMap<>();
		int count=asMngService.checkStrSchedule(visitDttm, strMngId);
		check.put("count", count);
		JSONObject result=new JSONObject(check);
		return result;
	}
	/** 수리기사 조회 */
	@ResponseBody
	@RequestMapping(value="/search-mecha" , method=RequestMethod.GET)
	public JSONObject searchMecha(String locationCd,String visitDttm,String machineCd) {
		HashMap<String, Object> mechaList= new HashMap<>();
		List<AsMngVo> list = asMngService.selectMechList(visitDttm, locationCd,machineCd);
		mechaList.put("mechaList", list);
		JSONObject result=new JSONObject(mechaList);
		return result;
	}


}

