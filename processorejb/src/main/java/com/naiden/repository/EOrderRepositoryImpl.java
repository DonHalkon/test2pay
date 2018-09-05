package com.naiden.repository;

import com.naiden.model.EOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EOrderRepositoryImpl implements EOrderRepository {

	@PersistenceContext(unitName = "postgres-unit")
	private EntityManager em;

	// ToDO: pagination
	@Override
	public List<EOrder> findAll() {
		return em.createQuery("from EOrder", EOrder.class).getResultList();
	}

	@Override
	public void create(EOrder order) {
		em.persist(order);
	}
}
