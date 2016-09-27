package com.ewa.sujan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doPost() of DeleteProductServlet");
		PrintWriter out=response.getWriter();
		String html="";
		String selectValues="";
		ProductDataStore productStore=new ProductDataStore(getServletContext().getRealPath("Product.xml"));
    	HashMap<String,Product> productMapPrint=productStore.addProductsToMap();
    	
    	Set<Map.Entry<String, Product>> entries=productMapPrint.entrySet();
    	
    	html="<!DOCTYPE html> <html> <head> <title>DeleteProduct</title> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/DeleteProduct.css'> </head> <body> <table> <tr> 	<th>DELETE PRODUCT <i class='fa fa-minus-square-o' aria-hidden='true'></i></th> </tr> <tr> 	<td> 	<div id='Message'>Product deleted successfully!</div><div id='enterDiv'><form action='DeleteProduct' method='post'><div><select type='select' class='credentials'><option value='Select'>Select Product</option>";
		for(Map.Entry<String, Product> prodMap:entries){
			Product product=prodMap.getValue();
			System.out.println(prodMap.getKey()+" :  "+product.getProductID()+"  "+product.getProductName()+" "+product.getBrand());
			selectValues=selectValues+"<option value='Select'>"+product.getProductName()+"</option>";
		}
		html=html+selectValues;
		html=html+"</select></div><div><button type='submit' value='login' id='addProductButton' class='registerButtons'>Delete product <i class='fa fa-minus-square' aria-hidden='true'></i></button></div></form></div></td></tr></table></body></html>";
		out.print(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
