package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long itemId;
	private String itemName;
	private Double price;

	
	
	public Item(String itemName, Double price) {
		super();
		this.itemName = itemName;
		this.price = price;
	}



	public Item(Long itemId, String itemName, Double price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}

	

	public Long getItemId() {
		return itemId;
	}



	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}

	

	@Override
	public String toString() {
		return "id:" + itemId + " item name:" + itemName + ", price=" + price;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}



	
}



