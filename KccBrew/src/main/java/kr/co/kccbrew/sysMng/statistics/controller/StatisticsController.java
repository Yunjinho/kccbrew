package kr.co.kccbrew.sysMng.statistics.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.sysMng.statistics.model.StatisticsVo;
import kr.co.kccbrew.sysMng.statistics.service.IStatisticsService;
import lombok.RequiredArgsConstructor;

/**
 * @ClassNmae : StatisticController
 * @Decription : 여러가지 통계를 보기위한 controller
 * 
 * @   수정일               수정자             수정내용
 * ============      ==============     ==============
 * 2023-09-25			윤진호				최초생성
 * @author YUNJINHO
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class StatisticsController {
	/**
	 * IStatisticsService 변수선언
	 */
	private final IStatisticsService service;
	
	@RequestMapping(value="/statistics",method=RequestMethod.GET)
	public String statistic(Model model) {
		Date now=new Date();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		
		model.addAttribute("year", year);
		return "statistics";
	}
	/**
	 * 월별 장비별 AS 건수 조회
	 * @param date : 해당 달
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/machine-by-year",method=RequestMethod.POST)
	public JSONArray machineByYear(String date) {
		List<StatisticsVo> list = service.machineByMonth(date);
		JSONArray result=new JSONArray();
		JSONArray arr=new JSONArray();
		Map<String, String> map =new HashMap<String, String>();
		for(StatisticsVo l:list) {
			Map<String, Object> m =new HashMap<String, Object>();
			m.put("name",l.getMachineNm());
			m.put("y", Integer.parseInt(l.getCount()));
			
			arr.add(m);
		}
		result.add(arr);
		map.put("date", date);
		result.add(map);
		return result;
	}
	/**
	 * 평점 상위 기사
	 * @param date
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/high-rank-mecha",method=RequestMethod.POST)
	public JSONArray highRankMecha(String date) {
		List<StatisticsVo> list = service.highRankMecha(date);
		JSONArray result=new JSONArray();
		JSONArray arr=new JSONArray();
		Map<String, String> map =new HashMap<String, String>();
		for(StatisticsVo l:list) {
			Map<String, Object> m =new HashMap<String, Object>();
			m.put("name",l.getUserNm()+","+l.getUserId());
			m.put("y", Double.parseDouble(l.getStoreMngFb()));
			
			arr.add(m);
		}
		result.add(arr);
		map.put("date", date);
		result.add(map);
		return result;
	}
	
	/**
	 * 평점 하위 기사
	 * @param date
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/low-rank-mecha",method=RequestMethod.POST)
	public JSONArray lowRankMecha(String date) {
		List<StatisticsVo> list = service.lowRankMecha(date);
		JSONArray result=new JSONArray();
		JSONArray arr=new JSONArray();
		Map<String, String> map =new HashMap<String, String>();
		for(StatisticsVo l:list) {
			Map<String, Object> m =new HashMap<String, Object>();
			m.put("name",l.getUserNm()+","+l.getUserId());
			m.put("y", Double.parseDouble(l.getStoreMngFb()));
			
			arr.add(m);
		}
		result.add(arr);
		map.put("date", date);
		result.add(map);
		return result;
	}
	
	/**
	 * 장비별 재접수율 변화
	 * @param date
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/reapply-rate-by-machine",method=RequestMethod.POST)
	public JSONArray reapplyRateByMachine(String date) {
		List<StatisticsVo> list = service.reapplyRateByMachine(date);
		
		JSONArray result=new JSONArray();
		Map<String, String> map =new HashMap<String, String>();
		Map<String, Object> data= new HashMap<String, Object>();
		JSONArray datalist=new JSONArray();
		JSONArray seires=new JSONArray();
		String name="";
		if(list!=null && list.size()!=0) {
			name=list.get(0).getMachineNm();
		}
		String[] rate={null,null,null,null,null,null,null,null,null,null,null,null};

		for(StatisticsVo l:list) {
			if(!l.getMachineNm().equals(name)) {
				data.put("name", name);
				for(String r:rate){
					if(r!=null) {
						datalist.add(Double.parseDouble(r));
					}else {
						datalist.add(r);
					}
					r=null;
				}
				data.put("data",datalist);
				seires.add(data);
				name=l.getMachineNm();
				data= new HashMap<String, Object>();
				datalist=new JSONArray();
			}
			String[] month = new String[2];
			month=l.getYearmonth().split("/");
			switch (month[1]) {
			case "01": rate[0]=l.getPercents(); break;
			case "02": rate[1]=l.getPercents(); break;
			case "03": rate[2]=l.getPercents(); break;
			case "04": rate[3]=l.getPercents(); break;
			case "05": rate[4]=l.getPercents(); break;
			case "06": rate[5]=l.getPercents(); break;
			case "07": rate[0]=l.getPercents(); break;
			case "08": rate[7]=l.getPercents(); break;
			case "09": rate[8]=l.getPercents(); break;
			case "10": rate[9]=l.getPercents(); break;
			case "11": rate[10]=l.getPercents(); break;
			case "12": rate[11]=l.getPercents(); break;
			}
			
		}
		if(list!=null && list.size()!=0) {
			data.put("name", name);
			for(String r:rate){
				if(r!=null) {
					datalist.add(Double.parseDouble(r));
				}else {
					datalist.add(r);
				}
			}
		}
		data.put("data",datalist);
		seires.add(data);
		
		result.add(seires);
		map.put("date", date);
		result.add(map);
		return result;
	}
	
	/** 
	 * 통계 다운로드 
	 */
	@ResponseBody
	@RequestMapping(value="/download-statistics" , method=RequestMethod.POST)
	public void downloadList(@RequestParam(defaultValue = "")String year,HttpServletRequest request,HttpServletResponse response) {
		//세션값 받기
		HttpSession session=request.getSession();
		UserVo user=(UserVo)session.getAttribute("user");
		StatisticsVo vo=new StatisticsVo();
		vo.setDate(year);
		vo.setUserId(user.getUserId());
		service.downloadExcel(response, vo);
	}
	
}