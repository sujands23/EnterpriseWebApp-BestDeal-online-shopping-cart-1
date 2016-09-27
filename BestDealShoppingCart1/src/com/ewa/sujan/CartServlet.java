package com.ewa.sujan;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CartServlet extends HttpServlet {
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet of CartServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doPost of CartServlet");
		HttpSession session=request.getSession(false);
		String html="";
		PrintWriter out=response.getWriter();
		
		if(session!=null){
			String name=(String)session.getAttribute("user");
			String productID[]=request.getParameterValues("productID");
			String productQuantity[]=request.getParameterValues("productQuantity");
			
			HashMap<String,Product> cartMap=new HashMap<String, Product>();
			
			String productFields[];
			
			String prodID;
			String prodCategory;
			String prodName;
			String prodBrand;
			String prodDescription;
			Double prodPrice;
			String prodImage;
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			String todate = dateFormat.format(date);
			Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.DATE, +14);
	        Date todate1 = cal.getTime();    
	        String deliveryDate = dateFormat.format(todate1);
	        
	        int month = cal.get(Calendar.MONTH);
	        int CartItems=0;
	        int Quantity=0;
	        Double SubTotal=0.0;
	        Double Total=0.0;
			String rowHtml="";
			
			for(int i=0;i<productID.length;i++){
				
				Quantity=Integer.parseInt(productQuantity[i]);
				CartItems=CartItems+Quantity;
				String productDetails=request.getParameter(""+productID[i]+"");
				System.out.println(productDetails);
				
				prodID=productID[i];
				productFields=productDetails.split(":");
				
				prodCategory=productFields[0];
				prodName=productFields[1];
				prodBrand=productFields[2];
				prodDescription=productFields[3];
				prodPrice=Double.parseDouble(productFields[4]);
				prodImage=productFields[5];
				
				System.out.println("Price : "+prodPrice+" Quantity : "+Quantity+" SubTotal : "+SubTotal);
				
				SubTotal=Quantity*prodPrice;
				Total=Total+SubTotal;
				System.out.println("SUB total"+SubTotal);
				
				cartMap.put(prodID,new Product(prodID,prodCategory,prodName,prodBrand,prodDescription,prodPrice,prodImage,Quantity));
				
				rowHtml=rowHtml+"<tr><td><image src='./images/"+prodImage+"'></td><td><ul><li>"+prodBrand+"</li><li>"+prodName+"</li><li>"+prodDescription+"</li><li>Retailer</li></ul><span id='RemoveItemButtonSpan'><form action='RemoveProduct' method='post'><button id='removeButton' name='RemoveProductFromCart' type='submit' value='"+prodID+"'>Remove<i class='fa fa-trash' aria-hidden='true'></i></button></form></span></td><td>"+prodPrice+"</td><td>"+Quantity+"</td><td>"+SubTotal+"</td><td>"+deliveryDate+"</td> </tr>";
			}
			
			Set<Map.Entry<String, Product>> entries=cartMap.entrySet();
			for(Map.Entry<String, Product> prodMap:entries){
				Product product=prodMap.getValue();
				System.out.println("ORDERS : "+prodMap.getKey()+" :  "+product.getProductID()+" "+product.getCategory()+"  "+product.getProductName()+" "+product.getBrand()+" "+product.getDescription()+""+product.getPrice()+" "+product.getImage());
			}
			
			session.setAttribute("userOrder",cartMap);
			session.setAttribute("total", Total);
			session.setAttribute("cartItems", CartItems);
			
			html="<!DOCTYPE html> <html> <head> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/OrderSummary.css'> <style>  </style> </head> <body>  <table> <tr> 	<td colspan='6' class='total'> 		<div id='pageTitle'> 			<span>ORDER SUMMARY</span> 		</div> 		<div id='loginId'> 			<span>"+name+"</span> 		</div> 		<div id='CartDisplay'> 			<span><i class='fa fa-shopping-cart' aria-hidden='true'></i> Cart : Items</span> 			<span id='cartItemNumber'>("+CartItems+")</span> 		</div> 	</td></tr><tr id='links'> 	<td colspan='3'> 		<div id='customerLinks'> 			<a href='ViewProducts'>View Products</a> 			<a href='ChangePassword.html'>Change password</a> 			<a href='ChangePassword.html'>View Orders</a> 			<a href='logout'>Logout</a> 		</div> 	</td> 	<td colspan='3'> 		<div id='managerLinks'> 			<a href='AddProduct.html'>Add product</a> 			<a href='DeleteProduct'>Delete product</a> 		</div> 	</td> </tr><tr>     <th>Image</th>     <th>ITEM</th>     <th>PRICE</th>     <th>QUANTITY</th>     <th>SUBTOTAL</th>     <th>DELIVERY DETAILS</th> </tr>"+rowHtml+" <tr> 	<td colspan='6' class='total'>     <div id='totalAmount'> 		<span id='totalAmountText'>TOTAL AMOUNT</span> 		<span id='totalAmountValue'>"+Total+"</span> 	</div> 	</td> </tr>  <tr> 	<td colspan='6'> 		<div id='placeOrderDiv'><button value='placeOrder' id='placeOrderButton'><a href='Payment'>PLACE ORDER</a></button></div> 	</td> </tr> </table> </body> </html>";
			out.println(html);
		}
		else{
			html="<html><head></head><body><h2>Session does not exist for the user.Please <a href='Login.html'>login</a> again</h2></body></html>";
		}
		
	}
}