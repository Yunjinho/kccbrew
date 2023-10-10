package kr.co.kccbrew.notice.service;

import java.util.List;

import kr.co.kccbrew.notice.model.PagingVo;
import kr.co.kccbrew.notice.model.noticeVo;

public interface INoticeServie {
	List<noticeVo> showAllNoticeList();				// 공지사항 목록 조회
	noticeVo readNotice(int noticeSeq);				// 공지사항 상세 조회
//	noticeVo readNoticeById(String writerId);		// 사용자 아이디로 공지 상세 조회
	void insertNotice(noticeVo noticeVo); 			// 공지사항 등록
	void updateNotice(noticeVo noticeVo);			// 공지사항 수정
//	void updateNoticeExceptImg(noticeVo noticeVo);	// 공지사항 이미지 수정
	void deleteNotice(int noticeSeq);				// 공지사항 삭제
	
	public int countNotice(); 						//공지사항 총 개수
	public List<noticeVo> selectNotice(PagingVo vo);// 페이징 처리 공지사항 조회
	
	noticeVo insertNoticeImg(noticeVo noticeVo); 	// 공지사항에 넣을 이미지 등록
}
