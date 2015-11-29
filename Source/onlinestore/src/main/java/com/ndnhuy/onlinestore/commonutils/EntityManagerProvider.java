package com.ndnhuy.onlinestore.commonutils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

@Component
public class EntityManagerProvider {
	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
}
