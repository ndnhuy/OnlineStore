package com.ndnhuy.onlinestore.annotation;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;

import com.ndnhuy.onlinestore.commonutils.ApplicationContextProvider;
import com.ndnhuy.onlinestore.commonutils.EntityManagerProvider;


public class UniqueValidator implements ConstraintValidator<UniqueInRepository, String> {

	@Override
	public void initialize(UniqueInRepository constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	private static final Logger logger = Logger.getLogger(UniqueValidator.class);
//
//	
//	private EntityManager em;
//
//	private String columnName;
//	private Class<?> entityType;
//	
//	@Override
//	public void initialize(UniqueInRepository constraintAnnotation) {
//		this.columnName = constraintAnnotation.columnName();
//		this.entityType = constraintAnnotation.entityType();
//		
//		em = (EntityManager) ApplicationContextProvider.getApplicationContext().getBean(EntityManagerProvider.class).getEntityManager();
//	}
//
//	@Override
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//
//		String sql = "SELECT count(e.id) FROM " + entityType.getSimpleName() + " e"
//				+ " WHERE e." + columnName + " = :value";
//		
//		Query q = em.createQuery(sql);
//		Query q2 = q.setParameter("value", value);
//		
//		Long result = (Long) q2.getSingleResult();
//		return result < 1;
//	}
	
}
