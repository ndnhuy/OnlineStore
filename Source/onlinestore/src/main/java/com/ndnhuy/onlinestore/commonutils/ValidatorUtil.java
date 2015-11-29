package com.ndnhuy.onlinestore.commonutils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

@Component
public class ValidatorUtil {
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	public void validate(Object o) {
		Set<ConstraintViolation<Object>> violations = validator.validate(o);
        for (ConstraintViolation c : violations) {
        	throw new RuntimeException(c.getMessage());
        }
	}
}
