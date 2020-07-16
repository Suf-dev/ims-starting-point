package com.qa.ims.persistence.domain;



public class Order {
	
	private Long id;
	private Long customer_id;
	private Long item_id;
	private String date;
	
	
	
	public Order() {
		
	}
	
	public Order(Long customer_id, Long item_id, String date) {
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.date = date;
		
	}
	public Order(Long id, Long customer_id, Long item_id) {
		this.id = id;
		this.customer_id = customer_id;
		this.item_id = item_id;
	
		//this.date = date;
	}
	public Long getOrder_id() {
		return id;
	}
	public void setOrder_id(Long order_id) {
		this.id = order_id;
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


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", item_id=" + item_id + ", date=" + date + "]";
	}

	

	
	
	}

	
	
	
	
	
	

	
	
	
	
	


