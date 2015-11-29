package com.ndnhuy.onlinestore.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
	String message() default "{com.ndnhuy.onlinestore.annotation.Unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    String columnName();
    Class<?> entityType();
}
