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
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.ItemServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoTest {
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
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql ("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");

		Long customer_id = 3L;
		Long item_id = 2L;
		String date = "2020/14/07";
		Order order = new Order(1L, customer_id, item_id);
		
		Long customer_id2 = 3L;
		Long item_id2 = 2L;
		String date2 = "2020/14/07";
		Order order2 = new Order(2L, customer_id2, item_id2);
		
		Long customer_id3 = 3L;
		Long item_id3 = 2L;
		String date3 = "2020/14/07";
		
		Order order3 = new Order(3L, customer_id3, item_id3);
	
		assertEquals(order, orderDaoMysql.create(order));
		assertEquals(order2, orderDaoMysql.create(order2));
		assertEquals(order3, orderDaoMysql.create(order3));
	}

	@Test
	public void cReadAllTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		List<Order> order = new ArrayList<>();
		order.add(new Order(3L, 2L, "2020/14/07"));
		order.add(new Order(2L, 3L, "2020/14/07"));
		order.add(new Order(4L, 2L, "2020/14/07"));

		assertEquals(order, orderDaoMysql.readAll());

	}

	@Test
	public void dReadLatestTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Order order = new Order(3L, 3L, "2020/14/07");
		assertEquals(order, orderDaoMysql.readLatest());
	}

	@Test
	public void eReadCustomerTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Order order = new Order(2L, 1L, "2020/14/07");
		assertEquals(order, orderDaoMysql.readAll());
	}

	//
//			/**
//			 * 
//			 */
	@Test
	public void fUpdateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Long customer_id = 3L;
		Long item_id = 2L;
		String date = "2020/14/07";
		Order order = new Order(1L, customer_id, item_id);
		assertEquals(order, orderDaoMysql.update(order));
		
	}

//	@Test
//	public void gDeleteTest() {
//		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
//				"2294");
//		String id = "3";
//		orderDaoMysql.delete(Long.parseLong(id));
//		List<Order> order = new ArrayList<>();
//		order.add(new Order(1L, customer_id, item_id));
//		assertEquals(order, orderDaoMysql.readAll());
//	}

	@AfterClass
	public static void cleanDB() {

		try (Connection connection = DriverManager.getConnection(jdbcurl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("drop table orders");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
