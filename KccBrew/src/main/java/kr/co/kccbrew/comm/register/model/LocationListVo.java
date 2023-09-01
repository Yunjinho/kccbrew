package kr.co.kccbrew.comm.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 회원가입시 등록된 지역코드를 조회하기 위한 Vo
 * 
 * @author YUNJINHO
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationListVo {
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdNm;
	private String grpCdDtlNm;
}
