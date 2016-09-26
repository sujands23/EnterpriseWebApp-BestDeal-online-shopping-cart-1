package com.poc.sujan;

public class Product {
	int productID;
	String productName;
	public Product(int productID, String productName) {
		super();
		this.productID = productID;
		this.productName = productName;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
