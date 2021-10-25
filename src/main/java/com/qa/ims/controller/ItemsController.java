package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;


	/**
	 * Takes in item details for CRUD functionality
	 *
	 */
	public class ItemsController implements CrudController<Items> {

		public static final Logger LOGGER = LogManager.getLogger();

		private ItemsDAO itemDAO;
		private Utils utils;

		public ItemsController(ItemsDAO itemDAO, Utils utils) {
			super();
			this.itemDAO = itemDAO;
			this.utils = utils;
		}

		/**
		 * Reads all customers to the logger
		 */
		@Override
		public List<Items> readAll() {
			List<Items> Items = itemDAO.readAll();
			for (Items item : Items) {
				LOGGER.info(item);
			}
			return Items;
		}

		/**
		 * Creates a customer by taking in user input
		 */
		@Override
		public Items create() {
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			LOGGER.info("Please enter a item price");
			Double price = utils.getDouble();
			Items item = itemDAO.create(new Items(itemName, price));
			LOGGER.info("Item created");
			return item;
		}

		/**
		 * Updates an existing customer by taking in user input
		 */
		@Override
		public Items update() {
			LOGGER.info("Please enter the id of the item you would like to update");
			Long id = utils.getLong();
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			LOGGER.info("Please enter a price");
			Double price = utils.getDouble();
			Items Items = itemDAO.update(new Items(id, itemName, price));
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
