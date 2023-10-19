package kr.co.kccbrew.notice.service;

import java.util.List;

import kr.co.kccbrew.notice.model.PagingVo;
import kr.co.kccbrew.notice.model.NoticeVo;

public interface INoticeService {
	
	// 공지사항 총 개수, 검색 조건 없음
	public int countNotice(); 						
	// 검색 조건 없이 공지사항 목록 조회
	public List<NoticeVo> selectNotice(PagingVo vo);
	
	//검색 조건 결과에 해당하는 공지사항 개수
	public int countNoticeWithCon(String searchOption, String searchText);	
	// 검색 조건을 설정한 공지 목록 조회, 페이징 포함
	List<NoticeVo> selectNoticeWithCon(int start, int end, String searchOption, String searchText);
	
	NoticeVo readNotice(int noticeSeq);				// 공지사항 상세 조회
	void insertNotice(NoticeVo noticeVo); 			// 공지사항 등록
	void updateNotice(NoticeVo noticeVo);			// 공지사항 수정
	void deleteNotice(int noticeSeq);				// 공지사항 삭제
	
	
	NoticeVo insertNoticeImg(NoticeVo noticeVo); 	// 공지사항에 넣을 이미지 등록
	public List<NoticeVo> noticeImageList(String fileSeq);	//공지 사항 첨부 이미지 리스트
	List<NoticeVo> selectMainNotice();				// 메인 화면에 보일 공지 목록
	
}