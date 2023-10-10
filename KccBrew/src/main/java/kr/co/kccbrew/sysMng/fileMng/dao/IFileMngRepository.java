package kr.co.kccbrew.sysMng.fileMng.dao;

import java.util.List;
import java.util.Map;

import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;

public interface IFileMngRepository {
	/**
	 * 파일리스트
	 */
	List<FileMngVo> fileList(Map<String, Object> map);
	/**
	 * 파일개수
	 */
	int getFileCount(Map<String, Object> map);
	/**
	 * 파일 분류
	 */
	List<FileMngVo> fileTypeList();
	/**
	 * 파일상세조회
	 */
	FileMngVo fileDtl(int fileSeq);
}

