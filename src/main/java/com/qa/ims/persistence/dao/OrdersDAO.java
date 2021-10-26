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

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {
	
	
	//�	Create an order in the system done
	//�	View all orders in the system done
    //� Delete an order in the system done
	//�	Add an item to an order done
	//�	Calculate a cost for an order
	//�	Delete an item in an order done
	

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("orderId");
		Double totalPrice = resultSet.getDouble("totalPrice");
		int quantity = resultSet.getInt("quantity");
		
		
		Long itemId = resultSet.getLong("itemId");
		String itemName = resultSet.getString("itemName");
		Double price = resultSet.getDouble("price");
		Item item = new Item(itemId, itemName, price);
		
		Long customerId = resultSet.getLong("id");
		String customerFirstName = resultSet.getString("first_name");
		String customerSurName = resultSet.getString("surname");
		Customer customer = new Customer(customerId, customerFirstName, customerSurName);
	
		return new Orders(orderId, customer, totalPrice, quantity, item);
	}

	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
		
	}

	@Override
	public Orders read(Long orderId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderId = ?");) {
			statement.setLong(1, orderId);
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
	public Orders create(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(quantity, totalPrice, customerId) VALUES (?, ?, ?)");) {
			statement.setInt(1, orders.getQuantity());
			statement.setDouble(2, orders.getTotalPrice());
			statement.setLong(3, orders.getCustomer().getId());
			statement.executeUpdate();
		
			return null;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders update(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET quantity = ?, totalPrice = ? WHERE ordersId = ?");) {
			statement.setInt(1, orders.getQuantity());
			statement.setDouble(2, orders.getTotalPrice());
			statement.setLong(3, orders.getOrderId());
			statement.executeUpdate();
			return read(orders.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderId = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	
	
	
	//�	Add an item to an order 
	
	public Orders AddItemToOrder(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items SET quantity = ?, totalPrice = ? WHERE item_id = ?");) {
			statement.setInt(1, order.getQuantity());
			statement.setDouble(2, order.getTotalPrice());
			statement.setLong(3, order.getItem().getItemId()); 
			statement.executeUpdate();
			return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
		//�	Delete an item in an order
	
	public Orders deleteItemFromOrders(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE item_id = ? and orders_id = ?");) {
			statement.setLong(1, order.getItem().getItemId());
			statement.setLong(2, order.getOrderId());
			 statement.executeUpdate();
			 return read(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
}
	
}	
	
	
	
	
	
	
	
	
	
	
	
	

