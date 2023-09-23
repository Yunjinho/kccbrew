package kr.co.kccbrew.schdlMng.validation;

import java.sql.Date;

import javax.validation.ConstraintValidator; 
import javax.validation.ConstraintValidatorContext;

import kr.co.kccbrew.schdlMng.model.Member;



public class MemberIdValidator implements ConstraintValidator<MemberId, String>{

   
    private Member member;

    public void initialize(MemberId constraintAnnotation) {
     
    }
   public boolean isValid(String value, ConstraintValidatorContext context) {	  
	     
	      if(value.equals("admin")) {
	         return false;
	      }
	      
	     return true;
    }
}