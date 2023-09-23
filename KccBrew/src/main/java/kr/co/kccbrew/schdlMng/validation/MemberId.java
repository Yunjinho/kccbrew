package kr.co.kccbrew.schdlMng.validation;

import java.lang.annotation.Documented; 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;


@Constraint(validatedBy = MemberIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented 

public @interface MemberId{
   String message() default "유효하지 않은 memberId"; 

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
