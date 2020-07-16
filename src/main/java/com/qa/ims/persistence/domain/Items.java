package com.qa.ims.persistence.domain;

public class Items {
	
	private Long id;
	private String itemName;
	private double price;
	private int stock;
	
	public Items(String itemName, double price, int stock) {
		this.itemName = itemName;
		this.price = price;
		this.stock = stock;
	}
	public Items(Long id, String itemName, double price, int stock) {
		this.id = id;
		this.itemName = itemName;
		this.price = price;
		this.stock = stock;
	}
	public Items() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "id:" + id + "itemName:" + itemName + "price:" + price + "stock:" + stock;
	}
	
	 
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		
//		int result = 1;
//		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		return result;
//	}
	
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Items other = (Items) obj;
//		
//		if (itemName == null) {
//			if (other.itemName != null)
//				return false;
//		} else if (!itemName.equals(other.itemName))
//			return false;
//		
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		
//		if (stock == null) {
//			if (other.stock != null)
//				return false;
//		} else if (!stock.equals(other.stock))
//			return false;
//		
//		if (price == null) {
//			if (other.price != null)
//				return false;
//		} else if (!price.equals(other.price))
//			return false;
//		return true;
	
	
	
	

}
