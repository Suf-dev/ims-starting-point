package com.qa.ims.persistance.doa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ItemDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.ItemServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Item2DaoTest {
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	static String jdbcurl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
	static String username = "root";
	static String password = "2294";


	@BeforeClass
	public static void setup() {
		try {
			Connection connection = DriverManager.getConnection(jdbcurl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate("Drop database ims_test");

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	@BeforeClass
	public static void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://" + Utils.MYSQL_URL + "/", username, password, "src/main/resources/sql-schema.sql");

	}

	@Test
	public void bCreateTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql ("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");

		String ItemName = "Mercury";
		double price = 45;
		int stock = 13;
		Items item = new Items(1L, ItemName, price, stock);
		
		String ItemName2 = "shadow legends";
		double price2 = 56;
		int stock2 = 10;
		Items item2 = new Items(2L, ItemName2, price2, stock2);
		
		String ItemName3 = "Wrestlemania";
		double price3 = 44.20;
		int stock3 = 53;
		Items item3 = new Items(3L, ItemName3, price3, stock3);
	
		assertEquals(item, item2DaoMysql.create(item));
		assertEquals(item2, item2DaoMysql.create(item2));
		assertEquals(item3, item2DaoMysql.create(item3));
	}

	@Test
	public void cReadAllTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		List<Items> items = new ArrayList<>();
		items.add(new Items(1L, "Mercury", 45, 13));
		items.add(new Items(2L, "Shadow legends", 56, 10));
		items.add(new Items(3L, "Wrestlemania", 44.20, 53));

		assertEquals(items, item2DaoMysql.readAll());

	}

	@Test
	public void dReadLatestTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Items items = new Items(3L, "Wrestlemania", 44.20, 53);
		assertEquals(items, item2DaoMysql.readLatest());
	}

	@Test
	public void eReadCustomerTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Items items = new Items(2L, "Shadow legends", 56, 10);
		assertEquals(items, item2DaoMysql.readItem(2L));
	}

	//
//			/**
//			 * 
//			 */
	@Test
	public void fUpdateTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		
		String ItemName = "Mercury";
		double price = 45;
		int stock = 13;
		
		
		
		Items item = new Items(1L, ItemName, price, stock);
		assertEquals(item, item2DaoMysql.update(item));
		
	}

	@Test
	public void gDeleteTest() {
		ItemDaoMysql item2DaoMysql = new ItemDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		String id = "3";
		item2DaoMysql.delete(Long.parseLong(id));
		List<Items> item = new ArrayList<>();
		item.add(new Items(3L, "Wrestlemania", 44.20, 53));
		assertEquals(item, item2DaoMysql.readAll());
	}

	@AfterClass
	public static void cleanDB() {

		try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("drop table customers");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
