package kr.co.kccbrew.sysMng.fileMng.service;

import java.util.List;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;

public interface IFileMngService {
	/**
	 * 
	 * 파일리스트
	 */
	List<FileMngVo> fileList(FileMngVo fileMngVo, int page);
	/**
	 * 
	 * 파일분류
	 */
	List<FileMngVo> fileTypeList();
	/**
	 * 
	 * 파일개수
	 */
	int getFileCount(FileMngVo fileMngVo);
	/**
	 * 
	 * 파일조회
	 */
	FileMngVo fileDtl(int fileSeq);
}
