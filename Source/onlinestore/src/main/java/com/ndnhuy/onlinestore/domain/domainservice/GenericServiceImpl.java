package com.ndnhuy.onlinestore.domain.domainservice;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ndnhuy.onlinestore.commonutils.ValidatorUtil;

/**
 * @author Huy Nguyen
 */
public abstract class GenericServiceImpl<E, D, ID extends Serializable> implements GenericService<E, D, ID>{
	
	private static final Logger logger = Logger.getLogger(GenericServiceImpl.class);
	
	@Autowired
	protected JpaRepository<E, ID> repository;
	
	@Autowired
	protected DozerBeanMapper mapper;
	
	@Autowired
	protected ValidatorUtil validator;
	
	protected Class<E> entityType;
	protected Class<D> dtoType;
	protected Class<ID> idType;
	
	
	
	@SuppressWarnings("unchecked")
	public GenericServiceImpl() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityType = (Class<E>) genericSuperClass.getActualTypeArguments()[0];
		this.dtoType = (Class<D>) genericSuperClass.getActualTypeArguments()[1];
		this.idType = (Class<ID>) genericSuperClass.getActualTypeArguments()[2];
	}
	
	@Override
	public D findOne(ID id) {
		if (logger.isDebugEnabled())
			logger.debug("Find " + entityType.getName() + " has id " + id);
		return mapper.map(repository.findOne(id), dtoType);
	}

	@Override
	public D add(D dto) {
		if (logger.isDebugEnabled())
			logger.debug("Add " + entityType.getName() + "[" + ToStringBuilder.reflectionToString(dto) + "]");
		
		E e = mapper.map(dto, entityType);
		repository.saveAndFlush(e);
		return mapper.map(e, dtoType);
	}

	@Override
	public void delete(ID id) {
		if (logger.isDebugEnabled())
			logger.debug("Delete " + entityType.getName() + " has id " + id);
		
		repository.delete(id);
	}


	@Override
	public Collection<D> findAll() {
		if (logger.isDebugEnabled())
			logger.debug("Find all " + entityType.getName());
		
		Collection<D> results = new ArrayList<D>();
		for (E e : repository.findAll()) {
			results.add(mapper.map(e, dtoType));
		}
		
		return results;
	}

	@Override
	public D udpate(D updatedInfo) {
		if (logger.isDebugEnabled())
			logger.debug("Update " + entityType.getName() + " with " + ToStringBuilder.reflectionToString(updatedInfo));
		
		Method method = null;
		try {
			method = dtoType.getMethod("getId", null);
			
			if (!method.getReturnType().equals(idType)) {
				throw new RuntimeException("The return type of this getId() is not compatible with " + idType.getName());
			}
			
			
			ID id = (ID) method.invoke(updatedInfo, null);
			if (!repository.exists(id)) {
				throw new RuntimeException(entityType.getSimpleName() + " (id = " + id + ") not found");
			}
			
			E entityToSave = mapper.map(updatedInfo, entityType);
			validator.validate(entityToSave);
			
			return mapper.map(repository.saveAndFlush(mapper.map(updatedInfo, entityType)), dtoType);
			
		} catch (NoSuchMethodException e) {
			logger.error("No such method " + dtoType.getName() + "#getId()", e);
		} catch (SecurityException e) {
			logger.error("Cannot find method " + dtoType.getName() + "#getId()", e);
		} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			logger.error("The error occured when call method " + method.getName(), e);
		}
		
		
		
		return null;
	}
}
