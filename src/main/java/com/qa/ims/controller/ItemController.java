package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;


	/**
	 * Takes in item details for CRUD functionality
	 *
	 */
	public class ItemController implements CrudController<Item> {

		public static final Logger LOGGER = LogManager.getLogger();

		private ItemDAO itemDAO;
		private Utils utils;

		public ItemController(ItemDAO itemDAO, Utils utils) {
			super();
			this.itemDAO = itemDAO;
			this.utils = utils;
		}

		/**
		 * Reads all customers to the logger
		 */
		@Override
		public List<Item> readAll() {
			List<Item> Items = itemDAO.readAll();
			for (Item item : Items) {
				LOGGER.info(item);
			}
			return Items;
		}

		/**
		 * Creates a customer by taking in user input
		 */
		@Override
		public Item create() {
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			LOGGER.info("Please enter a item price");
			Double price = utils.getDouble();
			Item item = itemDAO.create(new Item(itemName, price));
			LOGGER.info("Item created");
			return item;
		}

		/**
		 * Updates an existing customer by taking in user input
		 */
		@Override
		public Item update() {
			LOGGER.info("Please enter the id of the item you would like to update");
			Long id = utils.getLong();
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			LOGGER.info("Please enter a price");
			Double price = utils.getDouble();
			Item Items = itemDAO.update(new Item(id, itemName, price));
			LOGGER.info("Item Updated");
			return Items;
		}

		/**
		 * Deletes an existing customer by the id of the customer
		 * 
		 * @return
		 */
		@Override
		public int delete() {
			LOGGER.info("Please enter the Item Id of the item you would like to delete");
			Long id = utils.getLong();
			return itemDAO.delete(id);
		}

	

}
