package kr.co.kccbrew.sysMng.cdMng.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 코드관리를 위한 Vo
 * 
 * @author BAESOOYEON
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CdMngVo {
	String cdId;
	String cdNm;
	 
	Date cdRegDttm;
	String cdRegUser;
	Date cdModDttm;
	String cdModUser;
	String cdUseYn;
	String cdDtlId;
	String cdDtlNm;
	Date cdDtlRegDttm;
	String cdDtlRegUser;
	Date cdDtlModDttm;
	String cdDtlModUser;
	String cdDtlUseYn;
	int cdIdx;
	//검색조건
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;
	String keyword;
}
