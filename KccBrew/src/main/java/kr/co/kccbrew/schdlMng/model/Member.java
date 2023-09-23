package kr.co.kccbrew.schdlMng.model;

import kr.co.kccbrew.schdlMng.validation.MemberId;

public class Member {

	@MemberId
    private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
    
    
    
}
