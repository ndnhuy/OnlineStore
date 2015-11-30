package com.ndnhuy.onlinestore.domain.domainservice;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.sync.Patch;

/**
 * Interface for generic CRUD operations on a service for a specific pair of E and D.
 * 
 * @author Huy Nguyen
 *
 * @param <E> entity type
 * @param <D> dto type
 * @param <ID> ID type
 */
public interface GenericService<E, D, ID extends Serializable> {
	D findOne(ID id);
	D add(D dto);
	void delete(ID id);
	Collection<D> findAll();
	D udpate(D updatedInfo);
	D updateChanges(ID id, Patch partialChanges);
}
