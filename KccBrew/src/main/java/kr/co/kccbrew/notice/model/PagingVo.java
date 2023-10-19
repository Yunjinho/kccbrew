package kr.co.kccbrew.notice.model;

import lombok.Data;

@Data
public class PagingVo {

	int nowPage;		//현재 페이지 번호
	int startPage;		//현재 페이지 목록의 시작 페이지
	int endPage;		//현재 페이지 목록의 끝 페이지
	int total;			//전체 글의 수
	int totalWithCon;	//검색 조건을 적용한 총 글의 수
	int cntPerPage;		//한 페이지 당 보여질 글의 수
	int lastPage;		//마지막 페이지
	int start;			//현재 페이지 범위의 시작 항목 번호
	int end;			//현재 페이지 범위의 끝 항목 번호
	int cntPage = 5;	//한 번에 표시할 페이지 목록의 수
	
	
	public PagingVo() {
	}
	public PagingVo(int total, int nowPage) {
		setNowPage(nowPage);
		setTotal(total);
	}
	public PagingVo(int total, int nowPage, int cntPerPage) {
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	
	// 제일 마지막 페이지 계산 - 글의 총 개수 / 한 페이지 당 보여줄 글의 수
	public void calcLastPage(int total, int cntPerPage) {
		setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
	}
	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
	}
	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
	}
	
	public int getNowPage() {
		return nowPage;
	}
}