package kr.co.kccbrew.schdlMng.validator;

import java.lang.annotation.Documented; 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;


@Constraint(validatedBy = LegalHolidayValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented 

public @interface LegalHoliday{
   String message() default "주말·공휴일에는 휴가신청이 불가능합니다."; 

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
