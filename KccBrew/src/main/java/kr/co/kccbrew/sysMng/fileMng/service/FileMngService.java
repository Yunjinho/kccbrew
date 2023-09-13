package kr.co.kccbrew.sysMng.fileMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.kccbrew.sysMng.fileMng.dao.IFileMngRepository;
import kr.co.kccbrew.sysMng.fileMng.model.FileMngVo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileMngService implements IFileMngService {

	private final IFileMngRepository fileMngRepository;
	
	@Override
	public List<FileMngVo> fileList(FileMngVo file, int currentPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("FileMngVo", file);
		map.put("firstRowNum", currentPage*10-9);
		map.put("lastRowNum", currentPage*10);
		return fileMngRepository.fileList(map);
	}
	
	@Override
	public int getFileCount(FileMngVo file) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("FileMngVo", file);
		return fileMngRepository.getFileCount(map);
	}
	@Override
	public List<FileMngVo> fileTypeList(){
	return fileMngRepository.fileTypeList();	
	}

}
