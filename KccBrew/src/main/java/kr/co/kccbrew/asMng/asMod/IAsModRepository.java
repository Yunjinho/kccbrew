package kr.co.kccbrew.asMng.asMod;

import kr.co.kccbrew.asMng.model.AsMngVo;

public interface IAsModRepository {
	void asMod(AsMngVo asMngVo);
	void deleteFile(String imgSeq);
}
