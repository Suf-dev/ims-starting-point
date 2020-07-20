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
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoTest {
	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	static String jdbcurl = "jdbc:mysql://" + Utils.MYSQL_URL + "/ims";
	static String username = "root";
	static String password = "2294";


	@BeforeClass
	public static void setup() {
		try {
			Connection connection = DriverManager.getConnection(jdbcurl, username, password);
			Statement statement = connection.createStatement();
			statement.executeUpdate("Drop database ims");

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
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");

		String firstName = "Mark";
		String surname = "Jacobs";
		String address = "12 Knighton close";
		String email = "fort@qa.com";
		String mobile = "07614528964";
		Customer customer = new Customer(1L, firstName, surname, address, email, mobile);

		String firstName2 = "John";
		String surname2 = "Jenkins";
		String address2 = "13 jenkins close";
		String email2 = "John@qa.com";
		String mobile2 = "07654654656";
		Customer customer2 = new Customer(2L, firstName2, surname2, address2, email2, mobile2);

		String firstName3 = "Adam";
		String surname3 = "man";
		String address3 = "13 strike close";
		String email3 = "AdamJ@qa.com";
		String mobile3 = "0777521632";
		Customer customer3 = new Customer(3L, firstName3, surname3, address3, email3, mobile3);

		assertEquals(customer, customerDaoMysql.create(customer));
		assertEquals(customer2, customerDaoMysql.create(customer2));
		assertEquals(customer3, customerDaoMysql.create(customer3));
	}

	@Test
	public void cReadAllTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Mark", "Jacobs", "12 Knighton close", "fort@qa.com", "07614528964"));
		customers.add(new Customer(2L, "John", "Jenkins", "13 jenkins close", "John@qa.com", "07654654656"));
		customers.add(new Customer(3L, "Adam", "man", "13 strike close", "AdamJ@qa.com", "0777521632"));

		assertEquals(customers, customerDaoMysql.readAll());

	}

	@Test
	public void dReadLatestTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		Customer customer = new Customer(3L, "Adam", "man", "13 strike close", "AdamJ@qa.com", "0777521632");
		assertEquals(customer, customerDaoMysql.readLatest());
	}

//	@Test
//	public void eReadCustomerTest() {
//		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
//				"2294");
//		Customer customer = new Customer(2L, "John", "Jenkins", "13 jenkins close", "John@qa.com", "07654654656");
//		assertEquals(customer, customerDaoMysql.readCustomer(2L));
//	}

	//
//			/**
//			 * 
//			 */
	@Test
	public void fUpdateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		
		Long id = 1L;
		String firstName = "Mark";
		String surname = "Jacobs";
		String address = "12 Knighton close";
		String email = "fort@qa.com";
		String mobile = "07614528964";
		
		
		Customer customer = new Customer(id, firstName, surname, address, email, mobile);
		
		assertEquals(customer, customerDaoMysql.update(customer));
		
	}

	@Test
	public void gDeleteTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("jdbc:mysql://" + Utils.MYSQL_URL + "/ims", "root",
				"2294");
		String id = "3";
		customerDaoMysql.delete(Long.parseLong(id));
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Mark", "Jacobs", "12 Knighton close", "fort@qa.com", "07614528964"));
		//customers.add(new Customer(3L, "Adam", "man", "13 strike close", "AdamJ@qa.com", "0777521632"));
		assertEquals(customers, customerDaoMysql.readAll());
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
