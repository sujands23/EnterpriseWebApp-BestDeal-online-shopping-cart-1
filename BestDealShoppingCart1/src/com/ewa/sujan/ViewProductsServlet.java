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

public class ViewProductsServlet extends HttpServlet {
    
    public ViewProductsServlet() {
        super();
        System.out.println("Inside no argument constructor of ViewProductsServlet");
    }
    
    public void init() throws ServletException{
    	System.out.println("Inside init() of ViewProductsServlet");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet of ViewProductServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);//It will return false if the session doesn't exist for this client.So do a null check
		PrintWriter out=response.getWriter();
		String html="";
		int cartItemCount=0;
		if(session!=null){//Session exists for user
			String name=(String)session.getAttribute("user");//user is the key
			
			html="<html><head><title>ViewProducts</title><link rel='stylesheet' type='text/css' href='./CSS/ViewProducts.css'><link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'></head><body><form action='addToCart' method='post'><table><tr><td colspan='7' class='total'><div id='pageTitle'><span>VIEW PRODUCTS</span></div><div id='loginId'><span>"+name+"</span></div><div id='CartDisplay'><span><i class='fa fa-shopping-cart' aria-hidden='true'></i> Cart : Items</span><span id='cartItemNumber'>("+cartItemCount+")</span></div></td></tr><tr id='links'> 	<td colspan='4'> 		<div id='customerLinks'> 			<a href='ViewProducts'>View Products</a> 			<a href='ChangePassword.html'>Change password</a> 			<a href='ChangePassword.html'>View Orders</a> 			<a href='logout'>Logout</a> 		</div> 	</td> 	<td colspan='3'> 		<div id='managerLinks'> 			<a href='AddProduct.html'>Add product</a> 			<a href='DeleteProduct'>Delete product</a> 		</div> 	</td> </tr><tr><th>Image</th><th>Brand</th><th>Model</th><th>Description</th><th>Price</th><th>Select Model</th><th>Quantity</th></tr>";
			
			ProductDataStore productStore=new ProductDataStore(getServletContext().getRealPath("Product.xml"));
	    	HashMap<String,Product> productMapPrint=productStore.addProductsToMap();
	    	
	    	Set<Map.Entry<String, Product>> entries=productMapPrint.entrySet();
			String hiddenInputValue="";
			
			for(Map.Entry<String, Product> prodMap:entries){
				System.out.println();
				Product product=prodMap.getValue();
				System.out.println(prodMap.getKey()+" :  "+product.getProductID()+"  "+product.getProductName()+" "+product.getBrand());
				hiddenInputValue=hiddenInputValue+"<input type='hidden' name='"+product.getProductID()+"' value='"+product.getCategory()+":"+product.getProductName()+":"+product.getBrand()+":"+product.getDescription()+":"+product.getPrice()+":"+product.getImage()+"'>";
				String selectString="";
				if(product.getQuantityAvailable()>0){
					selectString="<select name='productQuantity'>";
					for(int i=0;i<=product.getQuantityAvailable();i++){
						selectString=selectString+"<option value="+i+">"+i+"</option>";
					}
					selectString=selectString+"</select>";
				}
				else{
					selectString="Out of Stock";
				}
				
				html=html+"<tr><td><img src='./images/"+product.getImage()+"' style='width:100px;height:100px;'></td><td>"+product.getBrand()+"</td><td>"+product.getProductName()+"</td><td>"+product.getDescription()+"</td><td>$"+product.getPrice()+"</td><td><input type='checkbox' name='productID' value='"+product.getProductID()+"'></input></td><td>"+selectString+"</td></tr>";
			}
			html=html+"<td colspan='7'><div id='AddToCartDiv'><button type='submit' id='AddToCartButton'><i class='fa fa-shopping-cart' aria-hidden='true' style='color:black'></i>ADD TO CART</button></div></td></table>"+hiddenInputValue+"</form></body></html>";
		}
		else{
			html="<html><head></head><body><h2>Session does not exist for the user.Please <a href='Login.html'>login</a> again</h2></body></html>";
		}
		out.println(html);
	}
}