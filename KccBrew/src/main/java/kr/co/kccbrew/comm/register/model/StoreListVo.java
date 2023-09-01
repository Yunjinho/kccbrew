package kr.co.kccbrew.comm.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 회원가입시 점포 리스트를 조회하기 위한 Vo
 * @author YUNJINHO
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreListVo {
	private String storeSeq;
	private String storeNm;
	private String storeAddr;
	private String storeTelNo;
}
