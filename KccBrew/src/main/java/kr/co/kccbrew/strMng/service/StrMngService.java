package kr.co.kccbrew.strMng.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import kr.co.kccbrew.asMng.model.AsMngVo;
import kr.co.kccbrew.strMng.dao.IStrMngRepository;
import kr.co.kccbrew.strMng.model.StrMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StrMngService implements IStrMngService {
	/**
	 * 점포관리를 위한 서비스
	 * 
	 * @author BAESOOYEON
	 */
	private final IStrMngRepository storeRepository;

	@Override
	public List<StrMngVo> storeAll(StrMngVo strMngVo) {
		return storeRepository.storeAll(strMngVo);
	}

	@Override
	public List<StrMngVo> strFilter(StrMngVo strMngVo, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("StrMngVo", strMngVo);
		map.put("start", ((page-1)*10)+1);
		map.put("end", page*10);
		return storeRepository.strFilter(map);
	};
	@Override
	public List<StrMngVo> strAllFilter(StrMngVo strMngVo) {
		return storeRepository.strAllFilter(strMngVo);
	}
	
	@Override
	public int getStrFilterCount(StrMngVo strMngVo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("StrMngVo", strMngVo);
		return storeRepository.getStrFilterCount(map);
	}

	
	@Override
	public StrMngVo storeDetail(int storeSeq) {
		return storeRepository.storeDetail(storeSeq);
	}

	/* 점포의 점주 */
	@Override
	public List<StrMngVo> ownerList(int storeSeq) {
		return storeRepository.ownerList(storeSeq);
	}

	@Override
	public void insert(StrMngVo store) {
		storeRepository.insert(store);
	};

	/* 지역리스트 */
	@Override
	public List<StrMngVo> locationNm() {
		return storeRepository.locationNm();
	}

	/* 지역상세리스트 */
	@Override
	public List<StrMngVo> locationNmSeoul() {
		return storeRepository.locationNmSeoul();
	}

	@Override
	public void update(StrMngVo store) {
		storeRepository.update(store);
	};

	@Override

	/* 점포명중복확인 */
	public StrMngVo selectStoreByName(String storeNm) {
		return storeRepository.selectStoreByName(storeNm);

	};

	/* 점포검색 */
	@Override
	public List<StrMngVo> search(String keyword) {
		return storeRepository.search(keyword);
	}

	@Override
	public List<StrMngVo> selectMyStr(String userId) {
		return storeRepository.selectMyStr(userId);
	}

	@Override
	public void insertStr(String userId, String storeSeq) {
		storeRepository.insertStr(userId, storeSeq);
	}

	@Override
	public void deleteStr(String userId, String storeSeq) {
		List<AsMngVo> asList = storeRepository.selectDeleteAsList(userId,storeSeq);
		for(AsMngVo list:asList) {
			if(list.getAsResultSeq()!=null) {
				storeRepository.deleteASresult(list.getAsResultSeq());
			}
			if(list.getAsAssignSeq()!=null) {
				storeRepository.deleteASAssign(list.getAsAssignSeq());
			}
		}
		for(AsMngVo list:asList) {
			if(list.getAsInfoSeq()!=null) {
				storeRepository.deleteASinfo(list.getAsInfoSeq());
			}
		}
		storeRepository.deleteholiday(userId, storeSeq);
		storeRepository.deleteStr(userId, storeSeq);
	}

	@Override
	public void downloadStrList(StrMngVo vo) {
		List<StrMngVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"스토어 이름", "지역구분", "점포 연락처"});
		if(vo.getFlag().equals("1")) {
			//현재 페이지 저장
			list=strFilter(vo,vo.getCurrentPage());
	        for(int i=0;i<list.size();i++) {
	        	data.put(i+2,new Object[]{
	        			list.get(i).getStoreNm(),list.get(i).getLocationNm(),list.get(i).getStoreTelNo()});
	        }
		}else {
			//전체 페이지 저장
			list=strAllFilter(vo);
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
		
	}
}
