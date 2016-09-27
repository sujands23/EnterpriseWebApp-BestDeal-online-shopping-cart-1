package com.ewa.sujan;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ewa.sujan.Product;


public class OrderConfirmationServlet extends HttpServlet {

    public OrderConfirmationServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet of orderConfirmation");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doPost of orderConfirmation");
		String html="";
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		
		HashMap<String,Product> orderMap;
		String name=(String)session.getAttribute("user");
		
		Random rand = new Random();
		int  orderNumber = rand.nextInt(50) + 1;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String orderDate = dateFormat.format(date);
		
		System.out.println("Order Number : "+orderNumber);
		System.out.println("Order date"+orderDate);
		
		String cardNumber=request.getParameter("cardNumber");
		String lastFourDigits =cardNumber.substring((cardNumber.length()-4), cardNumber.length());
		
		html="<!DOCTYPE html> <html> <head> <title>OrderConfirmation</title> <link rel='stylesheet' type='text/css' href='./CSS/OrderConfirmation.css'> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> </head> <body> <table> <tr> 	<td colspan='1' class='total'> 		<div id='pageTitle'> 			<span>Order Confirmation<i class='fa fa-check' aria-hidden='true'></i></span> 		</div> 		<div id='loginId'> 			<span>"+name+"</span> 		</div> 	</td> </tr><tr> 	<td> 		<h3>Your Payment information</h3> 		<hr> 		<ul> 			<li>Order Confirmation Number	:	<b>"+orderNumber+"</b></li> 			<li>Payment method				:	Credit Card XXXX-XXXX-XXXX-"+lastFourDigits+"<b></b></li> 			<li>Total Amount Paid 			:	<b></b></li> 			<li>Transaction Date			:	<b>"+orderDate+"</b></li> 		</ul> 		<h3>Order Details</h3> 		<hr> 		<ul>";
		
		orderMap=(HashMap<String, Product>) session.getAttribute("userOrder");
		
		Set<Map.Entry<String, Product>> entries=orderMap.entrySet();
		for(Map.Entry<String, Product> prodMap:entries){
			Product product=prodMap.getValue();
			System.out.println("Order Confirmation : "+prodMap.getKey()+" :  "+product.getProductID()+"		|	"+product.getCategory()+"	|	"+product.getProductName()+"	|	"+product.getBrand()+"	|	"+product.getDescription()+"	|	"+product.getPrice()+"	|	"+product.getImage());
			html=html+"<li>"+product.getProductID()+"		|	"+product.getCategory()+"	|	"+product.getProductName()+"	|	"+product.getBrand()+"	|	"+product.getDescription()+"	|	"+product.getPrice()+"	|	"+product.getImage()+"</li>";
		}
		html=html+"</ul> 	</td> </tr> </table> </body> </html>";
		out.println(html);
	}

}
