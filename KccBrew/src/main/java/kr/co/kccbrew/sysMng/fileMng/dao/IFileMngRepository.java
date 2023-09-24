package kr.co.kccbrew.sysMng.fileMng.dao;

import java.util.List;
import java.util.Map;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;

public interface IFileMngRepository {
	List<FileMngVo> fileList(Map<String, Object> map);
	int getFileCount(Map<String, Object> map);
	List<FileMngVo> fileTypeList();
	FileMngVo fileDtl(int fileSeq);
}

