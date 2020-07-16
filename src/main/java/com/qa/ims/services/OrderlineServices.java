package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Orderline;


public class OrderlineServices implements CrudServices<Orderline> {

	private Dao<Orderline> orderlineDao;
	
	public OrderlineServices(Dao<Orderline> orderlineDao) {
		this.orderlineDao = orderlineDao;
	}
	
	@Override
	public List<Orderline> readAll() {
		return orderlineDao.readAll();
	}

	@Override
	public Orderline create(Orderline t) {
		return orderlineDao.create(t);
	}

	@Override
	public Orderline update(Orderline t) {
		return orderlineDao.update(t);
	}

	@Override
	public void delete(Long id) {
		 orderlineDao.delete(id);
		
	}

}
