package com.ndnhuy.onlinestore.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {FIELD})
@Retention(RUNTIME)
@Documented
public @interface UniqueInRepository {
	String message() default "must be unique, this value already existed";
}
