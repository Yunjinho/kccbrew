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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.asMng.service.IAsMngService;
import kr.co.kccbrew.comm.register.model.RegisterVo;
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
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
		
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
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
	public String ssearchAsList(@RequestParam(defaultValue = "1") int currentPage,AsMngVo asMngVo,Model model,HttpSession session) {
		List<AsMngVo> list=asMngService.selectMachineCd();
		model.addAttribute("machineCd", list);
		
		list=asMngService.selectAsStatusCd();
		model.addAttribute("asStatusCd", list);
		
		list=asMngService.selectLocationCd();
		model.addAttribute("locationCd", list);
		
		asMngVo.setUserId((String)session.getAttribute("userId"));
		asMngVo.setUserTypeCd((String)session.getAttribute("userTypeCd"));
		List<AsMngVo> asList=asMngService.selectASList(asMngVo,currentPage);
		int totalPage = 0;
		int totalCount = asMngService.countASList(asMngVo);

		//   paging처리
		if (asList != null && !asList.isEmpty()) {
			totalPage = (int) Math.ceil(totalCount/10.0);
		} 

		int startPage=((int)Math.ceil(currentPage/10) * 10) + 1;
		int endPage=((int)Math.ceil(currentPage/10) + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
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
	public String asDetail(@RequestParam String asInfoSeq, Model model,HttpServletRequest request) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq);
		if(vo.getFileSeq()!=null) {
			List<AsMngVo> list=asMngService.selectAsInfoImg(vo.getFileSeq());
			model.addAttribute("asInfoImgList", list);
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
		System.out.println(asMngVo);
		asMngService.insertAsAssign(asMngVo);
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq();
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
	public JSONObject searchMecha(String locationCd,String visitDttm) {
		HashMap<String, Object> mechaList= new HashMap<>();
		List<AsMngVo> list = asMngService.selectMechList(visitDttm, locationCd);
		mechaList.put("mechaList", list);
		JSONObject result=new JSONObject(mechaList);
		return result;
	}
}

