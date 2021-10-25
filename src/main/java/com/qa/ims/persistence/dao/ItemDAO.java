package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("id");
		String itemName = resultSet.getString("item_Name");
		Double price = resultSet.getDouble("price");
		return new Item(itemId, itemName, price);
	}

    /**
 * Reads all items from the database
 * 
 * @return A list of items
 */
@Override
public List<Item> readAll() {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
		List<Item> items = new ArrayList<>();
		while (resultSet.next()) {
			items.add(modelFromResultSet(resultSet));
		}
		return items;
	} catch (SQLException e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return new ArrayList<>();
}

public Item readLatest() {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY itemsId DESC LIMIT 1");) {
		resultSet.next();
		return modelFromResultSet(resultSet);
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return null;
}
/**
 * Creates a item in the database
 * 
 * @param item - takes in a item object. id will be ignored
 */
@Override
public Item read(Long id) {
	
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE itemId = ?");) {
		statement.setLong(1, id);
		try (ResultSet resultSet = statement.executeQuery();) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		}
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	
	return null;
}

@Override
public Item create(Item item) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("INSERT INTO items(itemID, item_Name, price) VALUES (?, ?)");) {
		statement.setLong(1, item.getItemId());
		statement.setString(2, item.getItemName());
		statement.setDouble(3, item.getPrice());
		statement.executeUpdate();
		return readLatest();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	return null;
}
}
	/**
	 * Updates a item in the database
	 * 
	 * @param item - takes in a item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
@Override
public Item update(Item items) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection
					.prepareStatement("UPDATE items SET itemId = ?, item_Name = ? WHERE price = ?");) {
		statement.setLong(1, items.getItemId());
		
		statement.setString(2, items.getItemName());
		statement.setDouble(3, items.getPrice());
		statement.executeUpdate();
		return read(items.getItemId());
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return null;
}

@Override
public int delete(long id) {
	try (Connection connection = DBUtils.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE itemId = ?");) {
		statement.setLong(1, id);
		return statement.executeUpdate();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return 0;
}}

