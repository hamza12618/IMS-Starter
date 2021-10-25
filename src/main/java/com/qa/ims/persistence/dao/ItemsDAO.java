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

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAO implements Dao<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long itemId = resultSet.getLong("id");
		String itemName = resultSet.getString("item_Name");
		Double price = resultSet.getDouble("price");
		return new Items(itemId, itemName, price);
	}

    /**
 * Reads all customers from the database
 * 
 * @return A list of customers
 */
@Override
public List<Items> readAll() {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
		List<Items> items = new ArrayList<>();
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

public Items readLatest() {
	try (Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
		resultSet.next();
		return modelFromResultSet(resultSet);
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return null;
}
/**
 * Creates a customer in the database
 * 
 * @param customer - takes in a customer object. id will be ignored
 */
@Override
public Items read(Long id) {
	
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
public Items create(Items item) {
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
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
@Override
public Items update(Items items) {
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
			PreparedStatement statement = connection.prepareStatement("DELETE FROM customers WHERE id = ?");) {
		statement.setLong(1, id);
		return statement.executeUpdate();
	} catch (Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return 0;
}}

