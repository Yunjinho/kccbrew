package kr.co.kccbrew.sysMng.statistics.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.statistics.dao.IStatisticsRepository;
import kr.co.kccbrew.sysMng.statistics.model.StatisticsVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsService implements IStatisticsService{
	/**
	 * IStatisticsRepository 변수 선언
	 */
	private final IStatisticsRepository repository;
	
	/**
	 * 장비별 AS 건수 조회
	 * @param date : 해당 달
	 * @return
	 */
	@Override
	public List<StatisticsVo> machineByMonth(String date) {
		return repository.machineByMonth(date+"/01",date+"/12");
	}
	/**
	 * 평점 상위/하위 기사
	 * @param date
	 * @return
	 */
	@Override
	public List<StatisticsVo> highRankMecha(String date) {
		return repository.highRankMecha(date+"/01",date+"/12");
	}
	@Override
	public List<StatisticsVo> lowRankMecha(String date) {
		return repository.lowRankMecha(date+"/01",date+"/12");
	}
	
	/**
	 * 장비별 월별 재접수율
	 * @param start
	 * @param end
	 * @return
	 */
	@Override
	public List<StatisticsVo> reapplyRateByMachine(String date) {
		return repository.reapplyRateByMachine(date+"/01",date+"/12");
	}
	/**
	 * 통계 다운로드
	 */
	@Override
	public void downloadExcel(HttpServletResponse response, StatisticsVo vo) {
		List<StatisticsVo> list;
		XSSFWorkbook workbook = new XSSFWorkbook();
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
		
		list=machineByMonth(vo.getDate());
		Object[] col=new Object[list.size()];
		data.put(1, new Object[] {vo.getDate()+"년도 장비별 AS 건수"});
		for(int i=0;i<col.length;i++) {
			col[i]=list.get(i).getMachineNm();
		}
		
		data.put(2, col);
		col=new Object[list.size()];
		for(int i=0;i<col.length;i++) {
			col[i]=list.get(i).getCount();
		}
    	data.put(3,col);
        
       // 빈 Sheet를 생성
       XSSFSheet sheet = workbook.createSheet(vo.getDate()+"년도 통계");

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

       // 상위기사 sheet
       data = new HashMap<Integer, Object[]>();
       list=highRankMecha(vo.getDate());
       data.put(1, new Object[] {vo.getDate()+"년도 평점 상위 기사"});
       col=new Object[3];
       col[0]="ID";
       col[1]="이름";
       col[2]="평점";
       data.put(2, col);
       for(int i=0;i<list.size();i++) {
       	data.put(i+3,new Object[]{
       					list.get(i).getUserId()
       					,list.get(i).getUserNm()
       					,list.get(i).getStoreMngFb()
       			});
       }
      // 빈 Sheet를 생성
      // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
      keyset = data.keySet();
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
      // 하위기사 sheet
      data = new HashMap<Integer, Object[]>();
      list=lowRankMecha(vo.getDate());
      data.put(1, new Object[] {vo.getDate()+"년도 평점 하위 기사"});
      col=new Object[3];
      col[0]="ID";
      col[1]="이름";
      col[2]="평점";
      data.put(2, col);
      for(int i=0;i<list.size();i++) {
    	  data.put(i+3,new Object[]{
    			  list.get(i).getUserId()
    			  ,list.get(i).getUserNm()
    			  ,list.get(i).getStoreMngFb()
    	  });
      }
      // 빈 Sheet를 생성
      // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
      keyset = data.keySet();
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
      
      // 장비별 재접수율 sheet
      data = new HashMap<Integer, Object[]>();
      list=reapplyRateByMachine(vo.getDate());
      Collections.sort(list, new Comparator<StatisticsVo>() {
		@Override
		public int compare(StatisticsVo o1,StatisticsVo o2) {
			return 	o1.getYearmonth().compareTo(o2.getYearmonth());
		}
      });

      data.put(1, new Object[] {vo.getDate()+"장비별 재접수율"});
      col=new Object[5];
      col[0]="년/월";
      col[1]="장비 구분";
      col[2]="재접수 건수";
      col[3]="총접수 건수";
      col[4]="재접수율(%)";
      data.put(2, col);
      for(int i=0;i<list.size();i++) {
    	  data.put(i+3,new Object[]{
    			  list.get(i).getYearmonth()
    			  ,list.get(i).getMachineNm()
    			  ,list.get(i).getReapplyCnt()
    			  ,list.get(i).getTotalCnt()
    			  ,list.get(i).getPercents()
    	  });
      }
      // 빈 Sheet를 생성
      // data에서 keySet를 가져온다. 이 Set 값들을 조회하면서 데이터들을 sheet에 입력한다.
      keyset = data.keySet();
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
	
	   FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" , formatedNow+"_"+vo.getDate()+"_statistics.xlsx"));
	       workbook.write(out);
	       out.close();
	   } catch (Exception e) {
	       e.printStackTrace();
	   }	
		
	}
	

}