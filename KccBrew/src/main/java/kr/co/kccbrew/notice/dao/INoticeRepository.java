package kr.co.kccbrew.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.notice.model.noticeVo;

@Mapper
@Repository
public interface INoticeRepository {
	List<noticeVo> showAllNoticeList();				// 공지사항 목록 조회
	noticeVo readNotice(int noticeSeq);				// 공지사항 상세 조회
	void insertNotice(noticeVo noticeVo); 			// 공지사항 등록
	void updateNotice(noticeVo noticeVo);			// 공지사항 수정
	void deleteNotice(int noticeSeq);				// 공지사항 삭제
	
	void updateReadCount(int noticeSeq);			//조회수 기능
	
	void insertFileInfo(noticeVo noticeVo); 		// 공지사항에 넣을 이미지 정보
	void insertFileDtlInfo(noticeVo noticeVo); 		// 공지사항 이미지 상세 정보
}
