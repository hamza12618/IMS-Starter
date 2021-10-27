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

import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.ItemDAO;
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
		final String customerFirstName = "Hamza";
		final String customerSurName = "Shah";
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
		
		final Double totalPrice = 5.00;
		final Integer quantity = 6;
		
		final Long itemId = 2L;
		final String itemName = "Coke";
		final Double price = 6.00;
		final Item item = new Item(itemId, itemName, price);
		
		final Orders created = new Orders(customer, totalPrice, quantity, item);

		Mockito.when(utils.getLong()).thenReturn(customerId, itemId);
		Mockito.when(utils.getString()).thenReturn(customerFirstName, customerSurName, itemName);
		Mockito.when(utils.getDouble()).thenReturn(totalPrice, price);
		Mockito.when(utils.getInteger()).thenReturn(quantity);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Orders> order = new ArrayList<>();
		order.add(new Orders(1L, 5.00, 6, 2L));

		Mockito.when(dao.readAll()).thenReturn(order);

		assertEquals(order, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "Red Bull", 3.00);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long itemId = 1L;

		Mockito.when(utils.getLong()).thenReturn(itemId);
		Mockito.when(dao.delete(itemId)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(itemId);
	}

	
	
}
