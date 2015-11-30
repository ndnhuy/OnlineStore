package com.ndnhuy.onlinestore.commonutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.dozer.Mapping;
import org.springframework.stereotype.Component;

import com.ndnhuy.onlinestore.annotation.UniqueInRepository;

@Component
public class ValidatorUtil {
	
	private static final Logger logger = Logger.getLogger(ValidatorUtil.class);
	
	private EntityManager em;
	
	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	
	public void validate(Object o) {
		Set<ConstraintViolation<Object>> violations = validator.validate(o);
        for (ConstraintViolation c : violations) {
        	throw new RuntimeException(c.getMessage());
        }
	}
	
	public void checkIfFieldValueIsUniqueInRepo(Object o, Class<?> type) {
		em = ApplicationContextProvider.getApplicationContext().getBean(EntityManagerProvider.class).getEntityManager();
		
		Field[] privateFields = o.getClass().getDeclaredFields();
		
		for (Field field : privateFields) {
			field.setAccessible(true);
			
			Annotation[] annotations = field.getAnnotations();
			
			String fieldName = null;
			
			UniqueInRepository uniqueInRepositoryAnno = field.getAnnotation(UniqueInRepository.class);
			Mapping mappingAnnotation = field.getAnnotation(Mapping.class);
			
			if (uniqueInRepositoryAnno != null 
					&& mappingAnnotation != null) {
				
				fieldName = mappingAnnotation.value();
				
				try {
					type.getDeclaredField(fieldName);
				} catch (NoSuchFieldException | SecurityException e) {
					logger.error("No such field name '" + fieldName + "' found in " + type.getName(), e);
					throw new RuntimeException(e.getMessage());
				}
				
				
				String sql = "SELECT count(e.id) FROM " + type.getName() + " e"
						+ " WHERE e." + fieldName + " = :value";
				
				Object fieldValue;
				try {
					fieldValue = field.get(o);
					Long result = (Long) em.createQuery(sql).setParameter("value", fieldValue).getSingleResult();
					
					if (result > 0) {
						// Ex: [com.ndnhuy.onlinestore.domain.entity.Customer] 'name' field (value = 'A') must be unique, this value already existed
						throw new RuntimeException("[" + type.getName() + "] '" + fieldName + "' field (value = '" + fieldValue + "') " + uniqueInRepositoryAnno.message());
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					logger.error("Error occur when get value of '" + field.getName() + "' [" + type.getName() + "]", e);
					throw new RuntimeException(e.getMessage());
				}
			}
		}
	}
}
