package com.ewa.sujan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RemoveProductServlet extends HttpServlet {
       
    public RemoveProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet of RemoveProductServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String html="";
		String rowHtml="";
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String todate = dateFormat.format(date);
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +14);
        Date todate1 = cal.getTime();    
        String deliveryDate = dateFormat.format(todate1);
		
		String productToRemove=request.getParameter("RemoveProductFromCart");
		System.out.println(productToRemove);
		
		HashMap<String,Product> cartMap;
		String name=(String)session.getAttribute("user");
		cartMap=(HashMap<String, Product>) session.getAttribute("userOrder");
		int CartItems= (Integer) session.getAttribute("cartItems");
		//Double Total=(Double)session.getAttribute("total");
		int Total=0;
		
		Set<Map.Entry<String, Product>> entries=cartMap.entrySet();
		for(Map.Entry<String, Product> prodMap:entries){
			Product product=prodMap.getValue();
			System.out.println("Before Remove Product : "+prodMap.getKey()+" :  "+product.getProductID()+"		|	"+product.getCategory()+"	|	"+product.getProductName()+"	|	"+product.getBrand()+"	|	"+product.getDescription()+"	|	"+product.getPrice()+"	|	"+product.getImage()+"	|	"+product.orderQuantity);
		}
		//Total=Total-(product.getPrice()*product.orderQuantity);
		Iterator<Map.Entry<String,Product>> iter = cartMap.entrySet().iterator();
		while (iter.hasNext()) {
		    Map.Entry<String,Product> entry = iter.next();
		    if(entry.getKey().equals(productToRemove)){
		    	iter.remove();
		    }
		}
		
		Double SubTotal=0.0;
		System.out.println("-------------------------------------------------------------------------------");
		for(Map.Entry<String, Product> prodMap:entries){
			Product product=prodMap.getValue();
			System.out.println("After Remove Product : "+prodMap.getKey()+" :  "+product.getProductID()+"		|	"+product.getCategory()+"	|	"+product.getProductName()+"	|	"+product.getBrand()+"	|	"+product.getDescription()+"	|	"+product.getPrice()+"	|	"+product.getImage());
			SubTotal=product.price*product.getOrderQuantity();
			rowHtml=rowHtml+"<tr><td><image src='./images/"+product.getImage()+"'></td><td><ul><li>"+product.getBrand()+"</li><li>"+product.getProductName()+"</li><li>"+product.getDescription()+"</li><li>Retailer</li></ul><span id='RemoveItemButtonSpan'><form action='RemoveProduct' method='post'><button id='removeButton' name='RemoveProductFromCart' type='submit' value='"+product.getProductID()+"'>Remove<i class='fa fa-trash' aria-hidden='true'></i></button></form></span></td><td>"+product.getPrice()+"</td><td>"+product.getOrderQuantity()+"</td><td>"+SubTotal+"</td><td>"+deliveryDate+"</td> </tr>";
			SubTotal=0.0;
		}
		html="<!DOCTYPE html> <html> <head> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/OrderSummary.css'> <style>  </style> </head> <body>  <table> <tr> 	<td colspan='6' class='total'> 		<div id='pageTitle'> 			<span>ORDER SUMMARY</span> 		</div> 		<div id='loginId'> 			<span>"+name+"</span> 		</div> 		<div id='CartDisplay'> 			<span><i class='fa fa-shopping-cart' aria-hidden='true'></i> Cart : Items</span> 			<span id='cartItemNumber'>("+CartItems+")</span> 		</div> 	</td></tr><tr id='links'> 	<td colspan='3'> 		<div id='customerLinks'> 			<a href='ViewProducts'>View Products</a> 			<a href='ChangePassword.html'>Change password</a> 			<a href='ChangePassword.html'>View Orders</a> 			<a href='logout'>Logout</a> 		</div> 	</td> 	<td colspan='3'> 		<div id='managerLinks'> 			<a href='AddProduct.html'>Add product</a> 			<a href='DeleteProduct'>Delete product</a> 		</div> 	</td> </tr><tr>     <th>Image</th>     <th>ITEM</th>     <th>PRICE</th>     <th>QUANTITY</th>     <th>SUBTOTAL</th>     <th>DELIVERY DETAILS</th> </tr>"+rowHtml+" <tr> 	<td colspan='6' class='total'>     <div id='totalAmount'> 		<span id='totalAmountText'>TOTAL AMOUNT</span> 		<span id='totalAmountValue'>"+Total+"</span> 	</div> 	</td> </tr>  <tr> 	<td colspan='6'> 		<div id='placeOrderDiv'><button value='placeOrder' id='placeOrderButton'><a href='Payment.html'>PLACE ORDER</a></button></div> 	</td> </tr> </table> </body> </html>";
		out.println(html);
	}

}
