package com.poc.sujan;

public class GithubPOC {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		Product prod=new Product(001, "Samsung Galaxy Grand");
		
		System.out.println("Product ID	:	"+prod.getProductID());
		System.out.println("Product name	:	"+prod.getProductName());
		
		System.out.println("Added after initial commit");
		System.out.println("Second change after initial commit");
	}
}
