package kr.co.kccbrew.notice.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import kr.co.kccbrew.notice.dao.INoticeRepository;
import kr.co.kccbrew.notice.model.noticeVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class noticeService implements INoticeServie{
	private final INoticeRepository noticeRepository;

	@Override
	public List<noticeVo> showAllNoticeList() {
		List<noticeVo> noticeList = noticeRepository.showAllNoticeList();
		return noticeList;
	}

	@Override
	public noticeVo readNotice(int noticeSeq) {
		noticeRepository.updateReadCount(noticeSeq);
		return noticeRepository.readNotice(noticeSeq);
	}

	@Override
	public void insertNotice(noticeVo noticeVo) {
		insertNoticeImg(noticeVo);
		noticeRepository.insertNotice(noticeVo);
	}

	@Override
	public void updateNotice(noticeVo noticeVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNotice(int noticeSeq) {
		noticeRepository.deleteNotice(noticeSeq);
	}

	@Override
	public noticeVo insertNoticeImg(noticeVo noticeVo) {
		noticeVo vo = new noticeVo();
		vo.setWriterId(noticeVo.getWriterId());
		
		//파일 기본 정보 등록
		noticeRepository.insertFileInfo(vo);
		MultipartFile imgFile = noticeVo.getNoticeImg();
		
		vo.setFileOriginalName(imgFile.getOriginalFilename());
		vo.setFileDetailServerName("notice_" + imgFile.getOriginalFilename());
		vo.setFileFmt(imgFile.getContentType());
		vo.setFileDetailLocation(noticeVo.getFileDetailLocation());
		noticeVo.setFileId(vo.getFileId());
		
		//파일 상세 정보 등록
		noticeRepository.insertFileDtlInfo(vo);
		
		//이미지 파일 저장
		String targetPath = noticeVo.getServerSavePath()+"\\"+vo.getFileDetailServerName();
		String localPath = noticeVo.getLocalSavePath()+vo.getFileDetailServerName();
		try {
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(targetPath));
			FileCopyUtils.copy(imgFile.getInputStream(), new FileOutputStream(localPath));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return noticeVo;
	}
}
