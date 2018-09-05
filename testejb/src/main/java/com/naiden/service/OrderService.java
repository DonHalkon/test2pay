package com.naiden.service;


import com.naiden.model.EOrder;

import javax.ejb.Local;
import javax.jms.JMSException;
import java.util.List;

@Local
public interface OrderService {
    void addOrder(EOrder order) throws JMSException;
	List<EOrder> findAll();
}
