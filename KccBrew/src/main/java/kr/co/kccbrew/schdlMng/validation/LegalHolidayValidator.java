package kr.co.kccbrew.schdlMng.validation;

import java.sql.Date; 
import java.time.LocalDate;

import javax.validation.ConstraintValidator; 
import javax.validation.ConstraintValidatorContext;

import kr.co.kccbrew.schdlMng.model.Holiday;
import kr.co.kccbrew.schdlMng.model.Member;



public class LegalHolidayValidator implements ConstraintValidator<LegalHoliday, Date>{

   
    private Holiday holidayVo;

    public void initialize(LegalHoliday constraintAnnotation) {
    }
    
   public boolean isValid(Date value, ConstraintValidatorContext context) {
		LocalDate localDate = LocalDate.of(2023, 9, 24);
		java.sql.Date thisSunday = java.sql.Date.valueOf(localDate);
		
	      if(value.equals(thisSunday)) {
	         return false;
	      }
	     return true;
    }
}