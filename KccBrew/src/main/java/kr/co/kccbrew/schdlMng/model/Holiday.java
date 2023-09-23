package kr.co.kccbrew.schdlMng.model;

import java.sql.Date;

import kr.co.kccbrew.schdlMng.validation.LegalHoliday;
import lombok.Data;

@Data
public class Holiday {

	@LegalHoliday
    private Date startDate;
	
}
