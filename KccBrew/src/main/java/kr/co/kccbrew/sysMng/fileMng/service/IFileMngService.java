package kr.co.kccbrew.sysMng.fileMng.service;

import java.util.List;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;

public interface IFileMngService {
	List<FileMngVo> fileList(FileMngVo fileMngVo, int page);
	List<FileMngVo> fileTypeList();
	int getFileCount(FileMngVo fileMngVo);
	FileMngVo fileDtl(int fileSeq);
}
