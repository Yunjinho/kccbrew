package kr.co.kccbrew.sysMng.fileMng.service;

import java.util.List;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;

public interface IFileMngService {
	List<FileMngVo> fileList(FileMngVo file, int currentPage);
	List<FileMngVo> fileTypeList();
	int getFileCount(FileMngVo file);
}
