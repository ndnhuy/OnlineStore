package com.ndnhuy.onlinestore.annotation;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;

import com.ndnhuy.onlinestore.commonutils.ApplicationContextProvider;
import com.ndnhuy.onlinestore.commonutils.EntityManagerProvider;


public class UniqueValidator implements ConstraintValidator<Unique, String> {
	
	private static final Logger logger = Logger.getLogger(UniqueValidator.class);

	
	private EntityManager em;

	private String columnName;
	private Class<?> entityType;
	
	@Override
	public void initialize(Unique constraintAnnotation) {
		this.columnName = constraintAnnotation.columnName();
		this.entityType = constraintAnnotation.entityType();
		
		em = (EntityManager) ApplicationContextProvider.getApplicationContext().getBean(EntityManagerProvider.class).getEntityManager();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		String sql = "SELECT count(e.id) FROM " + entityType.getSimpleName() + " e"
				+ " WHERE e." + columnName + " = :value";
		
		
		Long result = (Long) em.createQuery(sql).setParameter("value", value).getSingleResult();
		return result < 1;
	}
	
}
