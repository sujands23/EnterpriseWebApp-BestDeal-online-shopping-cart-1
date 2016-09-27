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
import javax.servlet.http.HttpSession;

import com.ewa.sujan.Product;


public class PaymentServlet extends HttpServlet {

    public PaymentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doGet of PaymentServlet");
		HttpSession session=request.getSession(false);
		String user=(String)request.getParameter("user");
		String Total=request.getParameter("total");
		String cartItems=request.getParameter("cartItems");
		
		PrintWriter out=response.getWriter();
		String html="";
		
		html="<!DOCTYPE html> <html> <head> <title>Payment</title> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' type='text/css' href='./CSS/Payment.css'> </head> <body> <table> <tr> 	<td colspan='6' class='total'> 		<div id='pageTitle'> 			<span>CREDIT CARD PAYMENT <i class='fa fa-credit-card' aria-hidden='true'></i></span> 		</div> 		<div id='loginId'> 			<span>"+user+"</span> 		</div> 		<div id='CartDisplay'> 			<span><i class='fa fa-shopping-cart' aria-hidden='true'></i> Cart : Items</span> 			<span id='cartItemNumber'>("+cartItems+")</span> 		</div> 	</td> </tr> <tr id='links'> 	<td colspan='3'> 		<div id='customerLinks'> 			<a href='ViewProducts'>View Products</a> 			<a href='ChangePassword.html'>Change password</a> 			<a href='ChangePassword.html'>View Orders</a> 			<a href='logout'>Logout</a> 		</div> 	</td> 	<td colspan='3'> 		<div id='managerLinks'> 			<a href='AddProduct.html'>Add product</a> 			<a href='DeleteProduct'>Delete product</a> 		</div> 	</td> </tr> <tr> 	<td colspan='6'> 		<div id='creditCardDetails'> 			<form action='OrderConfirmation' method='post'> 				<div><input type='text' id='cardNumber' name='cardNumber' class='cardDetail' placeholder='Card Number' maxlength='16'></input></div> 				<div> 					<div id='cardDateMonth'> 						<span class='cardDateDiv'><input type='text' class='cardDetail cardTwo' placeholder='MM' maxlength='2'></input></span> 						<span class='delimiter'>/</span> 						<span class='cardDateDiv'><input type='text' class='cardDetail cardTwo' placeholder='YY' maxlength='2'></input></span> 					</div> 					<span class='cardCVV'><input type='password' id='cvvWidth' class='cardDetail' placeholder='CVV' maxlength='3'></input></span> 				</div> 				<div><input type='text' id='nameOnCard' class='cardDetail' placeholder='Name on card'></input></div> 				<div id='payButtonDiv'><button type='submit' class='cardDetail' id='payButton'>Make Payment</button></div> 			</form> 		</div> 	</td> </tr> </table> </body> </html>";
		out.println(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doPost of PaymentServlet");
		
	}

}
