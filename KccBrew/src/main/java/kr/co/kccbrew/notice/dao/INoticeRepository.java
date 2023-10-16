package kr.co.kccbrew.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.kccbrew.notice.model.PagingVo;
import kr.co.kccbrew.notice.model.NoticeVo;

@Mapper
@Repository
public interface INoticeRepository {
	List<NoticeVo> selectUserInfo();				//사용자 정보 받아오기
	String selectUserName(String writerId);			//사용자 이름 받아오기

	
	//공지사항 총 개수
	public int countNotice(); 						
	
	//검색 조건 포함 공지사항 개수
	public int countNoticeWithCon(@Param("searchOption") String searchOption, @Param("searchText") String searchText);
	
	public List<NoticeVo> selectNotice(PagingVo vo);// 페이징 처리 공지사항 조회
	
	// 검색 조건을 설정한 공지 목록 조회, 페이징 포함
	List<NoticeVo> selectNoticeWithCon(@Param("start") int start, @Param("end") int end, @Param("searchOption") String searchOption, @Param("searchText") String searchText);
	NoticeVo readNotice(int noticeSeq);				// 공지사항 상세 조회
	
	void insertNotice(NoticeVo noticeVo); 			// 공지사항 등록
	void updateNotice(NoticeVo noticeVo);			// 공지사항 수정
	void deleteNotice(int noticeSeq);				// 공지사항 삭제
	
	void updateReadCount(int noticeSeq);			//조회수 기능
	
	void insertFileInfo(NoticeVo noticeVo); 		// 공지사항에 넣을 이미지 정보 등록
	void insertFileDtlInfo(NoticeVo noticeVo); 		// 공지사항 이미지 상세 정보 등록
	List<NoticeVo> noticeImageList(String fieleSeq);// 공지사항 첨부 이미지 목
	List<NoticeVo> selectMainNotice();				// 메인 페이지에 보여질 공지 리스트	
	void deleteImgFIle(@Param("fileSeq") String fileSeq);
}