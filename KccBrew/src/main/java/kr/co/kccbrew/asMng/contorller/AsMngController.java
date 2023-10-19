package kr.co.kccbrew.asMng.contorller;

import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.MidiDevice.Info;

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
import lombok.extern.slf4j.Slf4j;
/**
 * @ClassNmae : AsMngController
 * @Decription : AS 기능을 관리하기 위한 controller
 * 

 * @   수정일               수정자        					 			    수정내용
 * ============      ==============     ============================
 * 2023-09-05					윤진호											최초생성
 * 2023-09-06					윤진호										AS 조회 구현
 * 2023-09-07					윤진호											AS 접수
 * 2023-09-10		    	배수연				            AS 접수 수정
 * 2023-09-13					윤진호										AS 기사 배정
 * 2023-10-06					윤진호							점포 점주 다대다 매핑 수정
 * 2023-10-06					이세은							AS접수 비동기(ajax)로 변경
 * 2023-10-06					이세은							AS배정 비동기(ajax)로 변경
 * 2023-10-10					이세은						AS배정반려 비동기(ajax)로 변경

 * @author YUNJINHO
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
@Slf4j
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
	@ResponseBody
	public void asReceipt(@Value("#{serverImgPath['localPath']}")String localPath,
			@Value("#{serverImgPath['asReceiptPath']}")String path,
			AsMngVo asMngVo,
			HttpServletRequest request) {

		String folderPath=request.getServletContext().getRealPath("")+path;
		HttpSession session=request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		asMngVo.setLocalSavePath(localPath+path);

		asMngService.insertAs(asMngVo);
	}

	/** 
	 * as 상세 조회
	 */
	@RequestMapping(value="/as-detail",method=RequestMethod.GET)
	public String asDetail(@RequestParam String asInfoSeq,@RequestParam String asAssignSeq, @RequestParam String storeSeq, Model model,HttpServletRequest request) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq,asAssignSeq,storeSeq);
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
	@ResponseBody
	public void asDetail(AsMngVo asMngVo, Model model,HttpServletRequest request) {

		/*파라미터 데이터 확인*/
		System.out.println("AsMngController.asDetail");
		System.out.println("asMngVo: " + asMngVo);

		HttpSession session = request.getSession();
		UserVo userVo=(UserVo)session.getAttribute("user");
		asMngVo.setUserId(userVo.getUserId());

		asMngVo.setAsStatusCd("03");
		asMngVo=asMngService.insertAsAssign(asMngVo);
		/*return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq()+"&asAssignSeq="+asMngVo.getAsAssignSeq();*/
	}

	/**
	 * 반려 신청
	 */
	@RequestMapping(value="/reject",method=RequestMethod.POST)
	@ResponseBody
	public void reject(String asInfoSeq,
									      String storeSeq,
										 @RequestParam(defaultValue = "0")String asAssignSeq,
										  String rejectRs,
										  HttpServletRequest request) {
		System.out.println("AsMngController.reject");
		System.out.println(asInfoSeq + ", " + storeSeq +  ", " + asAssignSeq + ", " + rejectRs);

		HttpSession session=request.getSession();

		UserVo userVo=(UserVo)session.getAttribute("user");
		String userTypeCd=userVo.getUserTypeCd();
		String userId=userVo.getUserId();


		if(userTypeCd.equals("01")) {
			asMngService.updateInfoReject(asInfoSeq, rejectRs,userId);
		}else if(userTypeCd.equals("03")){
			asMngService.updateAssignReject(asAssignSeq, rejectRs,userId);
		}
		//		return "redirect:/as-detail?asInfoSeq="+asInfoSeq+"&asAssignSeq="+asAssignSeq+"&storeSeq="+storeSeq;
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
		return "redirect:/as-detail?asInfoSeq="+asMngVo.getAsInfoSeq()+"&asAssignSeq="+asMngVo.getAsAssignSeq()+"&storeSeq="+asMngVo.getStoreSeq();
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
	public JSONObject checkStrSchedule(String storeSeq,String visitDttm) {
		HashMap<String, Integer> check= new HashMap<>();
		int count=asMngService.checkStrSchedule(visitDttm, storeSeq);
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
	public void downloadList(String flag
							,@RequestParam(defaultValue = "1")String currentPage
							,@RequestParam(defaultValue = "")String wishingStartDate
							,@RequestParam(defaultValue = "")String wishingEndDate
							,@RequestParam(defaultValue = "")String asInfoSeq
							,@RequestParam(defaultValue = "")String storeNm
							,@RequestParam(defaultValue = "")String storeAddr
							,@RequestParam(defaultValue = "")String searchId
							,@RequestParam(defaultValue = "")String machineCd
							,@RequestParam(defaultValue = "")String asStatusCd
							,HttpServletRequest request
							,HttpServletResponse response) {
		//세션값 받기
		HttpSession session=request.getSession();
		UserVo user=(UserVo)session.getAttribute("user");
		AsMngVo vo =new AsMngVo();
		vo.setWishingStartDate(wishingStartDate);
		vo.setWishingEndDate(wishingEndDate);
		vo.setAsInfoSeq(asInfoSeq);
		vo.setStoreNm(storeNm);
		vo.setStoreAddr(storeAddr);
		vo.setSearchId(searchId);
		vo.setMachineCd(machineCd);
		vo.setAsStatusCd(asStatusCd);
		vo.setUserId(user.getUserId());
		vo.setUserTypeCd(user.getUserTypeCd());
		
		List<AsMngVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"AS 번호", "신청일", "AS 상태","점포명","점포 주소","신청 장비","배정 기사","방문 예정일","AS 처리일","접수 내용","처리 결과 내용"});
		if(flag.equals("1")) {
			//현재 페이지 저장
			list=asMngService.selectASList(vo, Integer.parseInt(currentPage));
			System.out.println(list.size());
	        for(int i=0;i<list.size();i++) {
	        	if(list.get(i).getResultReapply()=="Y") {
	        		list.get(i).setAsStatusNm("재접수");
	        	}
	        	data.put(i+2, 
	        			new Object[]{list.get(i).getAsInfoSeq()
	        					,list.get(i).getRegDttm()
	        					,list.get(i).getAsStatusNm()
	        					,list.get(i).getStoreNm()
	        					,list.get(i).getStoreAddr()+","+list.get(i).getStoreAddrDtl()
	        					,list.get(i).getMachineCdNm()
	        					,list.get(i).getMechanicNm()
	        					,list.get(i).getVisitDttm()
	        					,list.get(i).getResultDttm()
	        					,list.get(i).getAsContent()
	        					,list.get(i).getResultDtl()
	        					});
	        }
		}else {
			//전체 페이지 저장
			list=asMngService.selectAllASList(vo);
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2, 
	        			new Object[]{list.get(i).getAsInfoSeq()
	        					,list.get(i).getRegDttm()
	        					,list.get(i).getAsStatusNm()
	        					,list.get(i).getStoreNm()
	        					,list.get(i).getStoreAddr()+","+list.get(i).getStoreAddrDtl()
	        					,list.get(i).getMachineCdNm()
	        					,list.get(i).getMechanicNm()
	        					,list.get(i).getVisitDttm()
	        					,list.get(i).getResultDttm()
	        					,list.get(i).getAsContent()
	        					,list.get(i).getResultDtl()
	        					});
	        }
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
       // 빈 Sheet를 생성
       XSSFSheet sheet = workbook.createSheet("조회한 AS 목록");

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
               }
           }
       }

       try {
           // 현재 날짜 구하기
           LocalDateTime now = LocalDateTime.now();
           // 포맷 정의
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
           // 포맷 적용
           String formatedNow = now.format(formatter);
    
           FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" , vo.getUserId()+"_"+formatedNow+"_as_list.xlsx"));
           workbook.write(out);
           
           out.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
		
		//asMngService.downloadExcel(response,vo, flag,currentPage);
	}

	@RequestMapping(value = "/as-mod", method = RequestMethod.GET)
	public String asMod(@Value("#{serverImgPath['localPath']}") String localPath
			, @Value("#{serverImgPath['asReceiptPath']}") String path, @RequestParam String asInfoSeq
			, @RequestParam String asAssignSeq,@RequestParam String storeSeq,Model model, HttpSession session) {
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq, asAssignSeq,storeSeq);
		vo.setLocalSavePath(localPath + path);
		session.setAttribute("asInfoSeq", asInfoSeq);
		session.setAttribute("asAssignSeq", asAssignSeq);
		session.setAttribute("storeSeq", storeSeq);
		session.setAttribute("fileSeq", vo.getFileSeq());

		model.addAttribute("asInfoSeq", asInfoSeq);
		model.addAttribute("asAssignSeq", asAssignSeq);
		model.addAttribute("storeSeq", storeSeq);
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
		String asAssignSeq = (String) session.getAttribute("asAssignSeq");
		String storeSeq = (String) session.getAttribute("storeSeq");
		String imgSeq = (String) session.getAttribute("fileSeq");
		asMngVo.setAsInfoSeq(asInfoSeq);
		asMngVo.setUserId(userId);
		asMngVo.setStorageLocation(path);
		asMngVo.setServerSavePath(folderPath);
		System.out.println(folderPath);
		System.out.println(path);
		System.out.println(asMngVo);
		asMngVo.setLocalSavePath(localPath + path);
		if(imgSeq!=null) {
		asMngService.deleteFile(asMngVo, imgSeq);}
		asMngService.asMod(asMngVo);



		return "redirect:/as-detail?asInfoSeq=" + asInfoSeq + "&asAssignSeq="+asAssignSeq+"&storeSeq="+storeSeq;
	}



	@GetMapping("/getAsInfoImages")
	@ResponseBody
	public List<AsMngVo> getAsInfoImages(@RequestParam String asInfoSeq, @RequestParam String asAssignSeq,@RequestParam String storeSeq) {
		// 이미지 정보를 가져와서 List<AsMngVo> 형태로 반환\
		AsMngVo vo = asMngService.selectAsInfoDetail(asInfoSeq, asAssignSeq,storeSeq);
		System.out.println(vo.getFileSeq());
		List<AsMngVo> imageList = null;
		if(vo.getFileSeq()!=null) {
		imageList = asMngService.selectAsImg(vo.getFileSeq());}
		else {
			imageList=null;
		}
		return imageList;
	}

	@RequestMapping(value="/delete-as")
	@ResponseBody
	public String deleteAS(@RequestParam String asInfoSeq) {
		asMngService.deleteAs(asInfoSeq);
		return "";
	}
}
