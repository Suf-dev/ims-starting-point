package com.qa.ims.persistence.domain;

public class Orderline {
	
	private Long id;
	private Long order_id;
	private Long customer_id;
	private Long item_id;
	private int quantity;
	private double price;
	private double total;
	
	
	
	
	public Orderline(Long order_id, Long customer_id, Long item_id, int quantity, double price, double total) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}
	public Orderline(Long id, Long order_id, Long customer_id, Long item_id, int quantity, double price, double total) {
		this.id = id;
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Orderline [id=" + id + ", order_id=" + order_id + ", customer_id=" + customer_id + ", item_id="
				+ item_id + ", quantity=" + quantity + ", price=" + price + ", total=" + total + "]";
	}
	
	
	
	
	
	
	

}
