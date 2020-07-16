package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Items>{

	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);
	
	private CrudServices<Items> itemService;
	
	public ItemController(CrudServices<Items> itemService) {
		this.itemService = itemService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	Double getInput1() {
		return Utils.getInput1();
	}
	int getInput2() {
		return Utils.getInput2();
	}
	public ItemController() {
		
	}
	
	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> item = itemService.readAll();
		for(Items items: item) {
			LOGGER.info(items.toString());
		}
		return item;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Items create() {
		
		LOGGER.info("Please enter the item name");
		String itemName = getInput();
		
		
		LOGGER.info("Please enter the price");
		double price = getInput1();
		
		LOGGER.info("Please enter the stock amount");
		int stock = getInput2();
		
		
		Items item = itemService.create(new Items(itemName, price, stock));
		LOGGER.info("Item created");
		
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = Long.valueOf(getInput());
		
		LOGGER.info("Please enter the item name");
		String itemName = getInput();
		
		
		LOGGER.info("Please enter the price");
		double price = getInput1();
		
		LOGGER.info("Please enter the stock amount");
		int stock = getInput2();
		
		Items item = itemService.update(new Items(id, itemName, price, stock));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = Long.valueOf(getInput());
		itemService.delete(id);
		LOGGER.info("item deleted");
	}
	
}
