package kr.co.kccbrew.notice.service;

import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

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
}


	
