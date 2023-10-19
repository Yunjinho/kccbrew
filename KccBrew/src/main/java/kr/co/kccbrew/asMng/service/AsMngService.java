package kr.co.kccbrew.asMng.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.dao.IAsMngRepository;
import kr.co.kccbrew.asMng.model.AsMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsMngService implements IAsMngService{
	
	/**
	 * asRepository 변수 선언
	 */
	private final IAsMngRepository asRepository;
	
	
	/** 
	 *  조회한 검색 결과
	 */
	@Override
	public List<AsMngVo> selectASList(AsMngVo asMngVo,int page) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		
		
		map.put("asVo", asMngVo);
		map.put("start", ((page-1)*10)+1);
		map.put("end", page*10);
		return asRepository.selectASList(map);
	}
	/**
	 * 조회한 검색 결과 (페이징 안한거
	 */
	@Override
	public List<AsMngVo> selectAllASList(AsMngVo asMngVo) {
		return asRepository.selectAllASList(asMngVo);
	}
	/**
	 * 조회할 AS 리스트의 수
	 */
	@Override
	public int countASList(AsMngVo asMngVo) {
		return asRepository.countASList(asMngVo);
	}
	
	/**
	 * 장비 코드 리스트 조회
	 * @return : 장비 코드 리스트
	 */
	@Override
	public List<AsMngVo> selectCd(String code) {
		return asRepository.selectCd(code);
	}

	/**
	 * 로그인한 사용자와 매핑 되어있는 점포 정보
	 */
	@Override
	public List<AsMngVo> selectStrInfo(String userId) {
		return asRepository.selectStrInfo(userId);
	}
	
	/**
	 * AS 접수
	 */
	@Transactional
	public void insertAs(AsMngVo asMngVo) {
		asMngVo.setGrpCdDtlId("02");
		asMngVo=insertImg(asMngVo);
		asRepository.insertAs(asMngVo);
	}

	/**
	 * 사용자 사진 등록
	 * @param user : 회원가입 사용자 정보 담긴 Vo
	 * @return : 회원가입 사용자 정보 담긴 Vo
	 */
	@Transactional
	private AsMngVo insertImg(AsMngVo asMngVo) {
		
		File folder = new File(asMngVo.getServerSavePath());
		File folder2 = new File(asMngVo.getLocalSavePath());
        // 폴더가 존재하지 않으면 폴더를 생성합니다.
        if (!folder.exists()) {
            folder.mkdirs(); // 폴더 생성 메소드
            folder2.mkdirs(); // 폴더 생성 메소드 서버로 배포하면 삭제 필요
        }
		
		AsMngVo vo=new AsMngVo();
		vo.setGrpCdDtlId("02");  // 
		vo.setUserId(asMngVo.getUserId());
		//기본 파일정보 등록
		asRepository.insertFile(vo);
		asMngVo.setFileSeq(vo.getFileSeq());
		
		for(MultipartFile imgFile:asMngVo.getImgFile()) {
			vo.setFileOriginalNm(imgFile.getOriginalFilename());
			if(imgFile.getOriginalFilename()!="") {
				vo.setFileOriginalNm(imgFile.getOriginalFilename());
				vo.setFileServerNm(asMngVo.getUserId()+"_"+new Date(System.currentTimeMillis())+"_"+imgFile.getOriginalFilename());
				vo.setFileFmt(imgFile.getContentType());
				vo.setStorageLocation(asMngVo.getStorageLocation());
		        String targetPath=asMngVo.getServerSavePath()+"\\"+vo.getFileServerNm();
				try {
					//imgFile.transferTo(targetPath);
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
					FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(asMngVo.getLocalSavePath()+"\\"+vo.getFileServerNm()));
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				asRepository.insertFileDtl(vo);
			}
		}
		return asMngVo;
	}

	@Override
	public AsMngVo selectAsInfoDetail(String asInfoSeq,String asAssignSeq,String storeSeq) {
		return asRepository.selectAsInfoDetail(asInfoSeq,asAssignSeq,storeSeq);
	}

	@Override
	public List<AsMngVo> selectAsImg(String fileDtlId) {
		return asRepository.selectAsImg(fileDtlId);
	}

	@Override
	public List<AsMngVo> selectLocationDtlCd(String locationCd) {
		return asRepository.selectLocationDtlCd(locationCd);
	}

	@Override
	public int checkStrSchedule(String date, String storeSeq) {
		return asRepository.checkStrSchedule(date, storeSeq);
	}

	@Override
	public List<AsMngVo> selectMechList(String date, String locationCd,String machineCd) {
		return asRepository.selectMechList(date, locationCd,machineCd);
	}
	
	@Transactional
	@Override
	public AsMngVo insertAsAssign(AsMngVo asMngVo) {
		asRepository.insertAsAssign(asMngVo);
		asRepository.updateAsInfoStatus(asMngVo);
		System.out.println(asMngVo);
		if(asMngVo.getAsResultSeq()!="" && asMngVo.getAsResultSeq()!=null) {
			System.out.println(asMngVo);
			asMngVo.setReapplyConfirm("Y");
			asRepository.updateAsResultConfirm(asMngVo);
		}
		return asMngVo;
	}

	@Override
	public void updateInfoReject(String seq, String content,String userId) {
		asRepository.updateInfoReject(seq, content,userId);
	}

	@Override
	public void updateAssignReject(String seq, String content,String userId) {
		asRepository.updateAssignReject(seq, content,userId);
		
	}

	@Override
	public void updateRejectConfirm(AsMngVo asMngVo,String flag) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("asMngVo", asMngVo);
		map.put("flag", (String)flag);
		
		asRepository.updateRejectConfirm(map);
	}

	@Override
	public void insertAsResult(AsMngVo asMngVo) {
		asMngVo.setGrpCdDtlId("03");
		asMngVo=insertImg(asMngVo);
		asRepository.insertResult(asMngVo);
		asMngVo.setAsStatusCd("04");
		asRepository.updateAsInfoStatus(asMngVo);
	}
	@Override
	public void updateResultMng(AsMngVo asMngVo) {
		asRepository.updateResultMng(asMngVo);
	}
	
	/**
	 * AS 접수
	 */
	@Transactional
	public void asMod(AsMngVo asMngVo) {
System.out.println(asMngVo);
	if(asMngVo.getImgFile()!=null) {
		asMngVo = insertImg(asMngVo);}
	else {
			asMngVo.setFileSeq("");
		}
		asRepository.asMod(asMngVo);
	}

	@Override
	public void deleteFile(AsMngVo asMngVo, String imgSeq) {
		List<AsMngVo> list = asRepository.selectAsImg(imgSeq);
		AsMngVo vo = new AsMngVo();

		for (AsMngVo e : list) {
			vo.setFileServerNm(e.getFileServerNm());
			vo.setServerSavePath(e.getServerSavePath());
			String targetPath = asMngVo.getServerSavePath() + "\\" + vo.getFileServerNm();
			File existingImg = new File(targetPath);
			if (existingImg.exists()) {
				boolean deleted = existingImg.delete();
				if (deleted) {
					
				}
			}
		}

		asRepository.deleteFile(imgSeq);
	}
	@Override
	public void downloadExcel(HttpServletResponse response,AsMngVo asMngVo,String flag,String currentPage) {
		List<AsMngVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"AS 번호", "신청일", "AS 상태","점포명","점포 주소","신청 장비","배정 기사","방문 예정일","AS 처리일","접수 내용","처리 결과 내용"});
		if(flag.equals("1")) {
			//현재 페이지 저장
			list=selectASList(asMngVo, Integer.parseInt(currentPage));
	        for(int i=0;i<list.size();i++) {
	        	if(list.get(i).getResultReapply()=="Y") {
	        		list.get(i).setAsStatusNm("재접수");
	        	}
	        	data.put(i+2, 
	        			new Object[]{
	        					 list.get(i).getAsInfoSeq()
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
			list=selectAllASList(asMngVo);
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
    
           FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" , asMngVo.getUserId()+"_"+formatedNow+"_as_list.xlsx"));
           workbook.write(out);
           
           out.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
		
	}
	@Override
	public void deleteAs(String asInfoSeq) {
		asRepository.deleteAs(asInfoSeq);
	}


}
