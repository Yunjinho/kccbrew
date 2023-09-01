package kr.co.kccbrew.comm.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 회원가입시 등록된 장비군 조회를 위한 Vo
 * @author YUNJINHO
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MechineListVo {
	private String grpCdDtlId;
	private String grpCdId;
	private String grpCdDtlNm;
}
