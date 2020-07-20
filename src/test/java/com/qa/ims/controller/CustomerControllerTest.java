package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy // for the methods in customerController
	@InjectMocks // for any classes our customerController calls (in this case customerService)
	private CustomerController customerController;

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Chris", "P", "ho close", "cp@qa.com", "07596213469"));
		customers.add(new Customer(1L, "Rhys", "T", "pop road", "rt@qa.com", "07955316546"));
		customers.add(new Customer(1L, "Nic", "J", "oats avenue", "nicj@qa.com", "07594621313"));
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	@Test
	public void createTest() {
		String firstName = "Chris";
		String surname = "Perrins";
		String address = "56 apple court";
		String email = "cp@qa.com";
		String mobile = "07652315985";
		Mockito.doReturn(firstName, surname, address, email, mobile).when(customerController).getInput();
		Customer customer = new Customer(firstName, surname, address, email, mobile);
		Customer savedCustomer = new Customer(1L, "Chris", "Perrins", "56 apple court","cp@qa.com", "07652315985");
		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	/**
	 *
	 */
	@Test
	public void updateTest() {
		String id = "1";
		String firstName = "Rhys";
		String surname = "Thompson";
		String address = "56 com road";
		String email = "a@b.com";
		String mobile = "07133516561";
		Mockito.doReturn(id, firstName, surname, address, email, mobile).when(customerController).getInput();
		Customer customer = new Customer(1L, firstName, surname, address, email, mobile);
		Mockito.when(customerServices.update(customer)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		customerController.delete();
		Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
	}

}
