package com.qa.ims.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order>{

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Order> orderService;
	
	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	Long getInput3() {
		return Utils.getInput3();
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for(Order order: orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}
	
//	CustomerDaoMysql c = new CustomerDaoMysql();
//	
//	OrderDaoMysql o = new OrderDaoMysql();
//	
	Order d = new Order();
	//LOGGER.info(o.readAll());

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		
		
		LOGGER.info("please select your customer id");
		Long Customer_id = Long.valueOf(getInput());
		
		LOGGER.info("select an item by id");
		Long Item_id  = getInput3();
		
		//java.util.Date date = d.getDate();
		
		SimpleDateFormat formateDate = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = new Date();
		String someDate = formateDate.format(date1);
		d.setDate(someDate);
		System.out.println(formateDate.format(date1));
		System.out.println(someDate);
		
//		String date = date.now();
		Order order = orderService.create(new Order(Customer_id, Item_id,someDate));
		LOGGER.info("Order created, thanks for ordering !");
		
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = Long.valueOf(getInput());
		
		LOGGER.info("select a customer id");
		Long Customer_id = getInput3();
		
		
		LOGGER.info("select an item by id");
		Long Item_id  = getInput3();
		
		SimpleDateFormat formateDate = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = new Date();
		String someDate = formateDate.format(date1);
		d.setDate(someDate);
		System.out.println(formateDate.format(date1));
		System.out.println(someDate);
		
//		String date = date.now();
		Order order = orderService.create(new Order(Customer_id, Item_id, someDate));
		LOGGER.info("Order updated !");
		
		return order;
			
		
	}
	
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Customer deleted");
	}



	
}
