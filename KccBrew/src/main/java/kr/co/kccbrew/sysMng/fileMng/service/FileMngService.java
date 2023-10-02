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
	public List<FileMngVo> fileList(FileMngVo fileMngVo, int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("FileMngVo", fileMngVo);
		map.put("start", ((page-1)*10)+1);
		map.put("end", page*10);
		return fileMngRepository.fileList(map);
	}
	
	@Override
	public int getFileCount(FileMngVo fileMngVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("FileMngVo", fileMngVo);
		return fileMngRepository.getFileCount(map);
	}
	@Override
	public List<FileMngVo> fileTypeList(){
	return fileMngRepository.fileTypeList();	
	}
	
	@Override
	public FileMngVo fileDtl(int fileSeq) {
		return fileMngRepository.fileDtl(fileSeq);
	}

}
