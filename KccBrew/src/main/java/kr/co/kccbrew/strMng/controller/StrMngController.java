package kr.co.kccbrew.strMng.controller;

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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.IUserService;
import kr.co.kccbrew.strMng.model.StrMngVo;
import kr.co.kccbrew.strMng.service.IStrMngService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : StrMngController
 * @Decription : 코드 관리하기 위한 controller
 * 
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-08-23 배수연 최초생성
 * @ 수정일 수정자 수정내용 ============ ============== ============== 2023-10-05 윤진호 점포 점주 다대다 매칭
 * @author BAESOOYEON
 * @version 1.0
 */



//기본 CRUD (조회 시 검색조건, 페이징 처리 전 -> 프레임, 페이징  마스터에 머지 된 후 수정 예정)

@Controller
@RequiredArgsConstructor
public class StrMngController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IStrMngService storeService;
	private final IUserService userService;
	/* 점포조회 */
	@RequestMapping("/store")
	public String storeAll(Model model, StrMngVo strMngVo, HttpSession session) {
		List<StrMngVo> list = storeService.strFilter(strMngVo, 1); // 점포목록
		List<StrMngVo> List = storeService.locationNm(); // 지역코드리스트
		List<StrMngVo> seoulList = storeService.locationNmSeoul();// 상세지역코드리스트
		int totalPage = 0;
		int totalCount = storeService.getStrFilterCount(strMngVo);
		double totalCountDouble = (double) totalCount;
		if (list != null && !list.isEmpty()) {
			totalPage = (int) Math.ceil(totalCountDouble/10.0);
		} 

		int startPage=((int)Math.ceil(strMngVo.getCurrentPage()/10) * 10) + 1;
		int endPage=((int)Math.ceil(strMngVo.getCurrentPage()/10) + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", 1);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("searchContent", strMngVo);
		model.addAttribute("list", list);
		model.addAttribute("List", List);
		model.addAttribute("seoulList", seoulList);
		return "storeList"; // strMngList.jsp로 반환
	
	}
	
	/* 점포조회 */
	@GetMapping("/store/search")
	public String store(Model model, StrMngVo strMngVo, HttpSession session) {
		List<StrMngVo> list = storeService.strFilter(strMngVo, strMngVo.getCurrentPage()); // 점포목록
		List<StrMngVo> List = storeService.locationNm(); // 지역코드리스트
		List<StrMngVo> seoulList = storeService.locationNmSeoul();// 상세지역코드리스트
		int totalPage = 0;
		int totalCount = storeService.getStrFilterCount(strMngVo);
		double totalLogCountDouble = (double) totalCount;
		if (list != null && !list.isEmpty()) {
			totalPage = (int) Math.ceil(totalLogCountDouble/10.0);
		} 

		int startPage=((int)Math.ceil(strMngVo.getCurrentPage()/10) * 10) + 1;
		int endPage=((int)Math.ceil(strMngVo.getCurrentPage()/10) + 1) * 10;
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", strMngVo.getCurrentPage());
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		model.addAttribute("totalCount", totalCount);
		
		model.addAttribute("searchContent", strMngVo);
		model.addAttribute("list", list);
		model.addAttribute("List", List);
		model.addAttribute("seoulList", seoulList);
		return "storeList"; // strMngList.jsp로 반환
	}

	/* 점포정보 */
	@RequestMapping("/store/view/{storeSeq}")
	public String storeDetail(@PathVariable int storeSeq, Model model) {
		StrMngVo store = storeService.storeDetail(storeSeq); // 점포 Dtl
		List<StrMngVo> list = storeService.ownerList(storeSeq); // 점주리스트
		model.addAttribute("list", list);
		model.addAttribute("store", store);
		return "strMng/strMngDtl";
	}

	/* 점포등록 */
	@RequestMapping(value = "/store/insert", method = RequestMethod.GET)
	public String insert(Model model, HttpSession session, Principal principal) {
		String userId = principal.getName();
		//로그인
		if (userId != null && !userId.equals("")) { 
			model.addAttribute("userId", userId);
			List<StrMngVo> list = storeService.locationNm(); // 지역코드리스트
			List<StrMngVo> seoullist = storeService.locationNmSeoul();// 상세지역코드리스트
			model.addAttribute("list", list);
			model.addAttribute("store", new StrMngVo());
			model.addAttribute("seoullist", seoullist);
			return "strMng/strMngIns";
			
		} else {	//로그아웃 로그인페이지로 이동		
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "";

		}
	}

	/* 점포등록 */
	@RequestMapping(value = "/store/insert", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	public String insert(StrMngVo store, Model model, Principal principal) {
		String userId = principal.getName();
		store.setRegUser(userId);
		store.setModUser(userId);
		storeService.insert(store);
		model.addAttribute("store", store);
		return "redirect:/store";
	}

	/* 점포수정 */
	@RequestMapping(value = "/store/update/{storeSeq}", method = RequestMethod.GET)
	public String update(@PathVariable int storeSeq, Model model, HttpSession session, Principal principal) {
		String userId = principal.getName();
		//String userTypeCd = (String) session.getAttribute("userTypeCd"); 로그인 구현 완료 이후에 주석 해제
		//로그인
		if (userId != null && !userId.equals("")) {
			model.addAttribute("userId", userId);
			StrMngVo store = storeService.storeDetail(storeSeq);
			List<StrMngVo> list = storeService.locationNm();
			List<StrMngVo> seoullist = storeService.locationNmSeoul();// 지역상세리스트
			model.addAttribute("seoullist", seoullist);
			model.addAttribute("list", list);
			model.addAttribute("store", store);
			return "strMng/strMngMod";
		} else {
			//로그아웃  로그인 페이지로 이동
			model.addAttribute("message", "로그인 하지 않은 사용자입니다.");
			return "";
		}
	}

	/* 점포수정 */
	@RequestMapping(value = "/store/update", method = RequestMethod.POST)
	public String update(Model model, StrMngVo store, HttpSession session, Principal principal) {
		String userId = principal.getName();
		store.setModUser(userId);
		// storeNm에서 ',' 제거
		String storeNmWithoutComma = store.getStoreNm().replaceAll(",", "");
		store.setStoreNm(storeNmWithoutComma);
		storeService.update(store);
		model.addAttribute("store", store);
		return "redirect:/store";
	}

	/* 점포명중복체크 */
	@RequestMapping(value = "/user/namecheck", method = RequestMethod.POST)
	@ResponseBody
	public String nameCheck(@RequestBody String storeNm) {
		storeNm = storeNm.replaceAll("\"", "");
		String check;
		// 데이터베이스에서 이미 존재하는지 확인
		StrMngVo existingStore = storeService.selectStoreByName(storeNm);
		if (existingStore == null) {
			check = "1";
		} else {
			check = "2";
		}
		return check;
	}

	/* 점포검색 */
	@RequestMapping(value = "/store/search")
	public String search(@RequestParam(required = false, defaultValue = "") String keyword, Model model) {
		List<StrMngVo> list = storeService.search(keyword);
		model.addAttribute("list", list);
		return "strMng/strMngSearch";
	}
	
	/**
	 * 점포 조회
	*/
	@RequestMapping(value="/store-list",method=RequestMethod.GET)
	public String strMngStrList(Model model,HttpServletRequest request,Principal principal) {
		
		
		
		String userId=principal.getName();

		
		List<StrMngVo> list = storeService.locationNm();
		List<StrMngVo> seoullist = storeService.locationNmSeoul();// 지역상세리스트
		List<StrMngVo> vo= storeService.selectMyStr(userId);
		StrMngVo store = storeService.storeDetail(vo.get(0).getStoreSeq());

		model.addAttribute("seoullist", seoullist);
		model.addAttribute("list", list);
		model.addAttribute("myStrList", vo);
		model.addAttribute("store", store);
		model.addAttribute("userId", userId);
		
		
		List<UserVo> storeList=userService.selectStoreList("",1);
		int storeListCount=userService.countStoreList("");
		int totalPage = 0;
		if (storeListCount > 0) {
			// 점포 목록을 5개씩 보여줄 때의 총 페이지 수
			totalPage = (int) Math.ceil(storeListCount / 5.0); 
		}
		// 페이지 수을 5개씩 보여줄 때의 총 페이지 블럭 수 ex - (1,2,3,4,5),(6,7,8,9,10) 
		int totalPageBlock = (int) (Math.ceil(totalPage / 5.0)); 
		//현재 페이지 블럭 ex)-1,2,3,4,5
		int nowPageBlock = (int) (Math.ceil(1 / 5.0));
		// 블럭의 시작 번호 ex) 1,6,11
		int startPage = (nowPageBlock-1) * 5 + 1;
		// 끝페이지
		int endPage=0;
		if(totalPage>nowPageBlock*5) {
			endPage=nowPageBlock*5;
		}else {
			endPage=totalPage;
		}

		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", 1);
		model.addAttribute("totalPageBlock", totalPageBlock);
		model.addAttribute("nowPageBlock", nowPageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("storeList", storeList);
		model.addAttribute("keyword", "");
		
		
		return "strMngStrList";
	}
	/**
	 * 점포 등록
	 * @param storeSeq
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value="/insert-my-store",method=RequestMethod.POST)
	public String insertMyStore(String storeSeq,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		String userId=user.getUserId();
		storeService.insertStr(userId, storeSeq);
		return "";
	}
	
	/**
	 * 상세 정보 조회
	 * @param storeSeq
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/select-str-dtl",method=RequestMethod.GET)
	public Map<String, Object> selectStrDtl(String storeSeq,HttpServletRequest request) {
		StrMngVo store = storeService.storeDetail(Integer.parseInt(storeSeq));
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("strObj", store);
		return map;
	}
	
	/**
	 * 상세 정보 조회
	 * @param storeSeq
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete-my-store",method=RequestMethod.POST)
	public String deleteStr(String storeSeq,HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		String userId=user.getUserId();
		
		storeService.deleteStr(userId, storeSeq);
		return "";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/download-str-list",method=RequestMethod.POST)
	public String downloadStrList(String flag,@RequestParam(defaultValue = "1")String currentPage,@RequestParam(defaultValue = "")String startDate,@RequestParam(defaultValue = "")String endDate,
									@RequestParam(defaultValue = "")String regUser,@RequestParam(defaultValue = "")String useYn,
									@RequestParam(defaultValue = "")String locationCd,@RequestParam(defaultValue = "")String locationCdSeoul,
									@RequestParam(defaultValue = "")String keyword,HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserVo user = (UserVo)session.getAttribute("user");
		String userId=user.getUserId();
		
		StrMngVo vo=new StrMngVo();
		vo.setCurrentPage(Integer.parseInt(currentPage));
		vo.setStartDate(startDate);
		vo.setEndDate(endDate);
		vo.setUseYn(useYn);
		vo.setLocationCdSeoul(locationCdSeoul);
		vo.setLocationCd(locationCd);
		vo.setKeyword(keyword);
		vo.setFlag(flag);
		
		
		
		List<StrMngVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"스토어 이름", "지역구분", "점포 연락처"});
		if(vo.getFlag().equals("1")) {
			//현재 페이지 저장
			list=storeService.strFilter(vo,vo.getCurrentPage());
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2,new Object[]{
	        			list.get(i).getStoreNm(),list.get(i).getLocationNm(),list.get(i).getStoreTelNo()});
	        }
		}else {
			//전체 페이지 저장
			list=storeService.strAllFilter(vo);
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2,new Object[]{list.get(i).getStoreNm(),list.get(i).getLocationNm(),list.get(i).getStoreTelNo()});
	        }
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
       // 빈 Sheet를 생성
       XSSFSheet sheet = workbook.createSheet("조회한 점포 목록");

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
    
           FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" ,formatedNow+"_str_list.xlsx"));
           workbook.write(out);
           
           out.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
		
		return "";
	}
}
