package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO dao;

	@InjectMocks
	private OrdersController controller;

	@Test
	public void testCreate() {
		final Long customerId = 1L;
		final String customerFirstName = "jordan";
		final String customerSurName = "harrison";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Double totalPrice = 1.00;
		final Integer quantity = 1;
		
		final Long itemId = 1L;
		final String itemName = "Coke";
		final Double price = 2.00;
		final Item item = new Item(itemId, itemName, price);
		
		final Orders created = new Orders(customer, totalPrice, quantity, item);

		Mockito.when(utils.getLong()).thenReturn(customerId, itemId);
		Mockito.when(utils.getString()).thenReturn(customerFirstName, customerSurName, itemName);
		Mockito.when(utils.getDouble()).thenReturn(totalPrice, price);
		Mockito.when(utils.getInteger()).thenReturn(quantity);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
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
		
		List<Orders> order = new ArrayList<>();
		order.add(new Orders(customer, 2.00, 1, item));

		Mockito.when(dao.readAll()).thenReturn(order);

		assertEquals(order, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
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
		
		Orders updated = new Orders(customer, 2.00, 1, item);

		
		Mockito.when(utils.getLong()).thenReturn(customerId, itemId, null);
		Mockito.when(utils.getString()).thenReturn(customerFirstName, customerSurName, itemName);
		Mockito.when(utils.getDouble()).thenReturn(price, updated.getTotalPrice());
		
		
		
		
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(this.utils, Mockito.times(3)).getString();
		Mockito.verify(this.utils, Mockito.times(2)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ordersId = 1L;

		Mockito.when(utils.getLong()).thenReturn(ordersId);
		Mockito.when(dao.delete(ordersId)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ordersId);
	}

	
	
}
