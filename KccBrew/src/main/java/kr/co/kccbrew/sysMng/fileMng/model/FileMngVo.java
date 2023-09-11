package kr.co.kccbrew.sysMng.fileMng.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMngVo {
	//fileInfo
	int fileSeq;
	String grpCdDtlId;
	Date fileRegDttm;
	String fileRegUser;
	Date fileModDttm;
	String fileModUser;
	//fileDtlInfo
	int fileDtlSeq;
	String fileOriginNm;
	String storageLocation;
	Date regDttm;
	String regUser;
	Date modDttm;
	String modUser;
	String fileServerNm;
	String fileFmt;
	String	grpCdDtlNm;
	private Integer startYr;
	private Integer startMn;
	private Integer endYr;
	private Integer endMn;

}
