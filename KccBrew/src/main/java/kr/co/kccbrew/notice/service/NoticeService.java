package kr.co.kccbrew.notice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.notice.dao.INoticeRepository;
import kr.co.kccbrew.notice.model.NoticeVo;
import kr.co.kccbrew.notice.model.PagingVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService implements INoticeService{
	private final INoticeRepository noticeRepository;
	
	// 공지사항 총 개수 조회
	@Override
	public int countNotice() {
		return noticeRepository.countNotice();
	}
	
	//공지사항 목록 조회, 페이징 처리
	@Override
	public List<NoticeVo> selectNotice(PagingVo vo) {
		return noticeRepository.selectNotice(vo);
	}

	// 공지사항 총 개수 조회 - 검색 조건 필터링한 결과만
	@Override
	public int countNoticeWithCon(String searchOption, String searchText) {
		return noticeRepository.countNoticeWithCon(searchOption, searchText);
	}
	
	//검색 조건을 설정한 공지 사항
	@Override
	public List<NoticeVo> selectNoticeWithCon(int start, int end, String searchOption, String searchText) {
		return noticeRepository.selectNoticeWithCon(start, end, searchOption,searchText);
	}
	
	
	//공지 상세 조회
	@Override
	public NoticeVo readNotice(int noticeSeq) {
		noticeRepository.updateReadCount(noticeSeq);
		return noticeRepository.readNotice(noticeSeq);
	}

	//공지 등록
	@Override
	public void insertNotice(NoticeVo noticeVo) {
		if(noticeVo.getNoticeImg() == null) {
			noticeRepository.insertNotice(noticeVo);
		}else {
			insertNoticeImg(noticeVo);
			noticeRepository.insertNotice(noticeVo);
		}
	}

	//공지 수정
	@Override
	public void updateNotice(NoticeVo noticeVo) {
		if(noticeVo.getFileSeq()!=null) {
			noticeRepository.deleteImgFIle(noticeVo.getFileSeq());
		}
		if(noticeVo.getNoticeImg() == null) {
			noticeRepository.updateNotice(noticeVo);
		}else {
			noticeVo.setFileId(Integer.parseInt(noticeVo.getFileSeq()));
			insertNoticeImg(noticeVo);
			noticeRepository.updateNotice(noticeVo);
		}
	}

	//공지 삭제
	@Override
	public void deleteNotice(int noticeSeq) {
		noticeRepository.deleteNotice(noticeSeq);
	}

	
	//공지 이미지 등록
	@Override
	public NoticeVo insertNoticeImg(NoticeVo noticeVo) {
		NoticeVo vo = new NoticeVo();
		vo.setWriterId(noticeVo.getModUser());
		
		//파일 기본 정보 등록
		if(noticeVo.getFileSeq()==null) {
			noticeRepository.insertFileInfo(vo);
		}else {
			vo.setFileId(noticeVo.getFileId());
		}
		
		List<MultipartFile> imgFile = noticeVo.getNoticeImg();
		for(MultipartFile m : imgFile) {
			if(m.getOriginalFilename()!="") {
				String uuid = UUID.randomUUID().toString();
				vo.setFileOriginalName(m.getOriginalFilename());
				vo.setFileDetailServerName("/notice_"+uuid+"_"+ m.getOriginalFilename());
				vo.setFileFmt(m.getContentType());
				vo.setFileDetailLocation(noticeVo.getFileDetailLocation());
				noticeVo.setFileId(vo.getFileId());
				
				//파일 상세 정보 등록
				noticeRepository.insertFileDtlInfo(vo);
				
				//이미지 파일 저장
				String targetPath = noticeVo.getServerSavePath()+"\\"+vo.getFileDetailServerName();
				String localPath = noticeVo.getLocalSavePath()+vo.getFileDetailServerName();
				try {
					FileCopyUtils.copy(m.getInputStream(), new FileOutputStream(targetPath));
					FileCopyUtils.copy(m.getInputStream(), new FileOutputStream(localPath));
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		return noticeVo;
	}

	
	
	
	//공지사항 첨부 이미지 리스트
	@Override
	public List<NoticeVo> noticeImageList(String fileSeq) {
		return noticeRepository.noticeImageList(fileSeq);
	}
	//메인 페이지에 보여질 공지 목록
	@Override
	public List<NoticeVo> selectMainNotice() {
		return noticeRepository.selectMainNotice();
	}

	// 공지 목록 엑셀로 다운 
	@Override
	public void downloadExcel(HttpServletResponse response, NoticeVo noticeVo, PagingVo pagingVo, String flag, int nowPage) {
		List<NoticeVo> list;
		Map<Integer, Object[]> data = new HashMap();
		data.put(1, new Object[]{"순번", "제목", "작성자","작성일","조회수"});
		if(flag.equals("1")) {
			//현재 페이지 저장
			list=selectNoticeWithCon(pagingVo.getStart(), pagingVo.getEnd(), noticeVo.getSearchOption(), noticeVo.getSearchText());
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
		}else {
			//전체 페이지 저장
			list=selectNotice(pagingVo);
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
    
           FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.home") + "\\Downloads\\" , noticeVo.getWriterId()+"_"+formatedNow+"_as_list.xlsx"));
           workbook.write(out);
           
           out.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
		
	}
}


	
