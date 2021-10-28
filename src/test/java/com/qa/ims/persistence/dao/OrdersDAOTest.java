package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {

	private final OrdersDAO DAO = new OrdersDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		
		final Long customerId = 1L;
		final String customerFirstName = "jordan";
		final String customerSurName = "harrison";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Long itemId = 1L;
		final String itemName = "Coke";
		final Double price = 2.00;
		final Item item = new Item(itemId, itemName, price);
		
		final Orders created = new Orders(1L, customer, 1.00, 1, item);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		
		final Long customerId = 1L;
		final String customerFirstName = "jordan";
		final String customerSurName = "harrison";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Long itemId = 1L;
		final String itemName = "Coke";
		final Double price = 2.00;
		final Item item = new Item(itemId, itemName, price);
		
		
		List<Orders> expected = new ArrayList<>();
		expected.add(new Orders(1L, customer, 1.00, 1, item));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testRead() {
		final Long customerId = 1L;
		final String customerFirstName = "jordan";
		final String customerSurName = "harrison";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Long itemId = 1L;
		final String itemName = "Coke";
		final Double price = 2.00;
		final Item item = new Item(itemId, itemName, price);
		
		
		final long ID = 1L;
		assertEquals(new Orders(ID, customer, 1.00, 1, item), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Long customerId = 1L;
		final String customerFirstName = "jordan";
		final String customerSurName = "harrison";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Long itemId = 1L;
		final String itemName = "Coke";
		final Double price = 2.00;
		final Item item = new Item(itemId, itemName, price);
		
		
		final Orders updated = new Orders(customer, 1.00, 1, item);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	
	
}
