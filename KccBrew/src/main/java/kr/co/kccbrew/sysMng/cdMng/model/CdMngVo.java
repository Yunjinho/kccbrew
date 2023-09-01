package kr.co.kccbrew.sysMng.cdMng.model;

import java.sql.Date;

import lombok.Data;
/**
 * 코드관리를 위한 Vo
 * 
 * @author BAESOOYEON
 */
@Data
public class CdMngVo {
	String cdId;
	String cdNm;
	 
	Date cdRegDttm;
	String cdRegUser;
	Date cdModDttm;
	String cdModUser;
	char cdUseYn;
	String cdDtlId;
	String cdDtlNm;
	Date cdDtlRegDttm;
	String cdDtlRegUser;
	Date cdDtlModDttm;
	String cdDtlModUser;
	char cdDtlUseYn;
	int cdIdx;
	String keyword;
}
