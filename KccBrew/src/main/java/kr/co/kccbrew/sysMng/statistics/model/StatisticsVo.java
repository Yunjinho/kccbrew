package kr.co.kccbrew.sysMng.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * 관리자 통계 작성 Vo
 * @author YUNJINHO
 */
@AllArgsConstructor
@Data
public class StatisticsVo {
	private String date;
	private String count;
	private String machineNm;
	
	private String userId;
	private String storeMngFb;
	private String userNm;
	
	
	private String percents;
	private String reapplyCnt;
	private String totalCnt;
	private String yearmonth;
	public StatisticsVo() {
		this.date = "";
		this.count = "";
		this.machineNm = "";
		
		this.userId = "";
		this.storeMngFb = "";
		this.userNm = "";

		this.percents = "";
		this.reapplyCnt = "";
		this.totalCnt = "";
	}
	
	
}