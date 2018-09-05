package com.naiden.repository;

import com.naiden.model.EOrder;

import java.util.List;

public interface EOrderRepository {
	List<EOrder> findAll();
	void create(EOrder order);
}
