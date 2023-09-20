package kr.co.kccbrew.asMng.asMod;

import kr.co.kccbrew.asMng.model.AsMngVo;

public interface IAsModService {
	
	void asMod(AsMngVo asMngVo);
	void deleteFile(AsMngVo asMngVo, String imgSeq);
}
