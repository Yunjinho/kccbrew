package kr.co.kccbrew.sysMng.fileMng.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 파일관리를 위한 Vo
 * 
 * @author BAESOOYEON
 */
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
	int currentPage;
	String startDate;
	String endDate;

}
