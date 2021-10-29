package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {
	/**
	 * Takes in Order details for CRUD functionality
	 *
	 */
	

		public static final Logger LOGGER = LogManager.getLogger();

		private OrdersDAO ordersDAO;
		private Utils utils;

		public OrdersController(OrdersDAO ordersDAO, Utils utils) {
			super();
			this.ordersDAO = ordersDAO;
			this.utils = utils;
		}

		/**
		 * Reads all Orders to the logger
		 */
		@Override
		public List<Orders> readAll() {
			List<Orders> orders = ordersDAO.readAll();
			for (Orders order : orders) {
				LOGGER.info(order);
			}
			return orders;
		}

		/**
		 * Creates a Order by taking in user input
		 */
		@Override
		public Orders create() {
			
		
			
			LOGGER.info("Please enter a customer");
			Long customerId = utils.getLong();
			String customerFirstName = utils.getString();
			String customerSurName = utils.getString();
			Customer customer = new Customer(customerId, customerFirstName, customerSurName);

			
			
			LOGGER.info("Please enter a total price");
			Double totalPrice = utils.getDouble();
			
			LOGGER.info("Please enter a quantity");
			Integer quantity = utils.getInteger();
			
			LOGGER.info("Please enter a item ");
			Long itemId = utils.getLong();
			String itemName = utils.getString();
			Double price = utils.getDouble();
			Item item = new Item(itemId, itemName, price);
			
			Orders Orders = ordersDAO.create(new Orders( customer, totalPrice, quantity, item));
			LOGGER.info("Order Created");
			return Orders;
		}

		/**
		 * Updates an existing Order by taking in user input
		 */
		@Override
		public Orders update() {
			LOGGER.info("Please enter a order Id");
			Long orderId = utils.getLong();
			
			LOGGER.info("Please enter a customer");
			Long customerId = utils.getLong();
			
			Customer customer = new Customer(customerId, null, null);

			
			
			
			
			LOGGER.info("Please enter a quantity");
			Integer quantity = utils.getInteger();
			
			LOGGER.info("Please enter a item ");
			Long itemId = utils.getLong();
			
			Item item = new Item(itemId, null, null);
			
		
			
			Orders Orders = ordersDAO.update(new Orders(orderId, customer, null, quantity, item));
			LOGGER.info("Order Updated");
			return Orders;
		}

		/**
		 * Deletes an existing Order by the id of the customer
		 * 
		 * @return
		 */
		@Override
		public int delete() {
			LOGGER.info("Please enter the Order Id of the order you would like to delete");
			Long orderId = utils.getLong();
			return ordersDAO.delete(orderId);
		}

	
		// Add an item to a order
		
		public Orders AddItemToOrder() {
			
			LOGGER.info("Please enter the orderId for the order that you would like to add the items from");
			Long ordersId = utils.getLong();
			
			
			LOGGER.info("Please enter a customer");
			Long customerId = utils.getLong();
			String customerFirstName = utils.getString();
			String customerSurName = utils.getString();
			Customer customer = new Customer(customerId, customerFirstName, customerSurName);
			
			
			LOGGER.info("Please enter a total price");
			Double totalPrice = utils.getDouble();
			
			LOGGER.info("Please enter a quantity");
			Integer quantity = utils.getInteger();
			
			
		LOGGER.info("Please enter the item id that you would like to add to the order");
		Long itemId = utils.getLong();
		String itemName = utils.getString();
		Double price = utils.getDouble();
		Item item = new Item(itemId, itemName, price);
		
		
		Orders Orders = ordersDAO.update(new Orders(ordersId, customer, totalPrice, quantity, item));
		LOGGER.info("Item Added ");
		
		return Orders;
		}

		
		//Delete an item in an order
		
		
		public Orders deleteItemFromOrders() {
			
			LOGGER.info("Please enter the orderId for the order that you would like to delete the items from");
			Long ordersId = utils.getLong();
			
			
			LOGGER.info("Please enter a customer");
			Long customerId = utils.getLong();
			String customerFirstName = utils.getString();
			String customerSurName = utils.getString();
			Customer customer = new Customer(customerId, customerFirstName, customerSurName);

			
			
			LOGGER.info("Please enter a total price");
			Double totalPrice = utils.getDouble();
			
			LOGGER.info("Please enter a quantity");
			Integer quantity = utils.getInteger();
			
			LOGGER.info("Please enter a item ");
			Long itemId = utils.getLong();
			String itemName = utils.getString();
			Double price = utils.getDouble();
			Item item = new Item(itemId, itemName, price);
			
			
			Orders orders = ordersDAO.deleteItemFromOrders(new Orders(ordersId, customer, totalPrice, quantity, item));
			LOGGER.info("Item deleted from Order");
			return orders;
			
			
		}

		//•	Calculate a cost for an order
		//public double Calculator {

		
		
		double price = 0;
		int quantity = 0;
		
		double totalPrice = price * quantity;
	   
		
		public static double multiply(double price, int quantity) {
			return  price * quantity;
	  
		
		
		
		}
		
		
		
		
		
		}
		 
		
		
		
		
		
		

