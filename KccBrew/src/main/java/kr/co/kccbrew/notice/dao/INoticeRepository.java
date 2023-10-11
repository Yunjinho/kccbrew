package kr.co.kccbrew.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.notice.model.PagingVo;
import kr.co.kccbrew.notice.model.NoticeVo;

@Mapper
@Repository
public interface INoticeRepository {
	List<NoticeVo> selectUserInfo();				//사용자 정보 받아오기
	String selectUserName(String writerId);			//사용자 이름 받아오기

	/*
	 * 페이징 처리
	 */
	public int countNotice(); 						//공지사항 총 개수
	public List<NoticeVo> selectNotice(PagingVo vo);// 페이징 처리 공지사항 조회
	
	
	List<NoticeVo> showAllNoticeList();				// 공지사항 목록 조회
	NoticeVo readNotice(int noticeSeq);				// 공지사항 상세 조회
	NoticeVo readNoticeById(String writerId);		// 사용자 아이디로 공지 상세 조회
	void insertNotice(NoticeVo noticeVo); 			// 공지사항 등록
	void updateNotice(NoticeVo noticeVo);			// 공지사항 수정
	void deleteNotice(int noticeSeq);				// 공지사항 삭제
	
	void updateReadCount(int noticeSeq);			//조회수 기능
	
	void insertFileInfo(NoticeVo noticeVo); 		// 공지사항에 넣을 이미지 정보 등록
	void insertFileDtlInfo(NoticeVo noticeVo); 		// 공지사항 이미지 상세 정보 등록
	List<NoticeVo> noticeImageList(String fieleSeq);
	List<NoticeVo> selectMainNotice();
	
}