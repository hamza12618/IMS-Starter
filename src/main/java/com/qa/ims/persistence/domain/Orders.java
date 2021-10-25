package com.qa.ims.persistence.domain;

public class Orders {

	private Long orderId;
	private Customer customer;
	private Double totalPrice;
	private int quantity;
	
	
	public Orders(Long orderId, Customer customer, Double totalPrice, int quantity) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
	}



	public Orders(Customer customer, Double totalPrice, int quantity) {
		super();
		this.customer = customer;
		this.totalPrice = totalPrice;
		this.quantity = quantity;
	}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", customer=" + customer + ", totalPrice=" + totalPrice + ", quantity="
				+ quantity + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity != other.quantity)
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}



	
	

	
	
}
