package kr.co.kccbrew.notice.service;

import java.io.FileOutputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.notice.dao.INoticeRepository;
import kr.co.kccbrew.notice.model.NoticeVo;
import kr.co.kccbrew.notice.model.PagingVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService implements INoticeServie{
	private final INoticeRepository noticeRepository;

	//공지 전체 조회
	@Override
	public List<NoticeVo> showAllNoticeList() {
		List<NoticeVo> noticeList = noticeRepository.showAllNoticeList();
		return noticeList;
	}
	
	//공지 상세 조회
	@Override
	public NoticeVo readNotice(int noticeSeq) {
		noticeRepository.updateReadCount(noticeSeq);
		return noticeRepository.readNotice(noticeSeq);
	}
	
//	@Override
//	public noticeVo readNoticeById(String writerId) {
//		return noticeRepository.readNoticeById(writerId);
//	}

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
		noticeRepository.updateNotice(noticeVo);
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
		vo.setWriterId(noticeVo.getWriterId());
		
		//파일 기본 정보 등록
		noticeRepository.insertFileInfo(vo);
		List<MultipartFile> imgFile = noticeVo.getNoticeImg();
		for(MultipartFile m : imgFile) {
			if(m.getOriginalFilename()!="") {
				vo.setFileOriginalName(m.getOriginalFilename());
				vo.setFileDetailServerName("notice_" + m.getOriginalFilename());
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

	// 공지사항 총 개수 조회
	@Override
	public int countNotice() {
		return noticeRepository.countNotice();
	}

	//공지사항 페이징 처리
	@Override
	public List<NoticeVo> selectNotice(PagingVo vo) {
		return noticeRepository.selectNotice(vo);
	}
	
	@Override
	public List<NoticeVo> noticeImageList(String fieleSeq) {
		return noticeRepository.noticeImageList(fieleSeq);
	}
}