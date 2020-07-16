package com.qa.ims.controller;



import java.util.List;

import org.apache.log4j.Logger;


import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderlineController implements CrudController<Orderline>{

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServices<Orderline> orderlineService;
	
	public OrderlineController(CrudServices<Orderline> orderlineService) {
		this.orderlineService = orderlineService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	Long getInput3() {
		return Utils.getInput3();
	}
	int getInput2() {
		return Utils.getInput2();
	}
	double getInput1() {
		return Utils.getInput1();
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Orderline> readAll() {
		List<Orderline> orderlines = orderlineService.readAll();
		
		
		for(Orderline orderline: orderlines) {
			LOGGER.info(orderline.toString());
	
		}
	
		return orderlines;
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
	public Orderline create() {
		
		
		LOGGER.info("please select your order id");
		Long Order_id = Long.valueOf(getInput());
		
		LOGGER.info("select your customer id");
		Long Customer_id  = getInput3();
		
		LOGGER.info("select your item id");
		Long Item_id  = getInput3();
		
		LOGGER.info("please select your quantity ");
		int Quantity  = getInput2();
		
		LOGGER.info("please insert the price of the item ");
		double price = getInput1();
		
		double total = price * Quantity;

		
	
		Orderline orderline = orderlineService.create(new Orderline(Order_id, Customer_id, Item_id, Quantity, price, total));
		
		LOGGER.info("The total for the order: " + Order_id + "is: " + total);
		
		return orderline;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orderline update() {
		
		LOGGER.info("please select the orderline id you would like to update");
		Long id = Long.valueOf(getInput());
		
		LOGGER.info("please select your order id");
		Long Order_id = Long.valueOf(getInput());
		
		LOGGER.info("select your customer id");
		Long Customer_id  = getInput3();
		
		LOGGER.info("select your item id");
		Long Item_id  = getInput3();
		
		LOGGER.info("please select your quantity ");
		int Quantity  = getInput2();
		
		LOGGER.info("please insert the price of the item ");
		double price = getInput1();
		
		double total = price * Quantity;
		
		Orderline orderline = orderlineService.create(new Orderline(id, Order_id, Customer_id, Item_id, Quantity, price, total));
		LOGGER.info("Order line updated! the total cost of the order is: " + total);
		
		return orderline;
	}
		
		
		

	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the orderline id you would like to delete");
		Long id = Long.valueOf(getInput());
		orderlineService.delete(id);
		LOGGER.info("orderline deleted");
	}



	
}
