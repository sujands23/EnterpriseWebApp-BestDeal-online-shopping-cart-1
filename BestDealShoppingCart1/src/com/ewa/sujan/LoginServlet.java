package com.ewa.sujan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
    	System.out.println("Inside init() of LoginServlet");
    	LoginCredentials lc=new LoginCredentials();
    	lc.printUserMap();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String EmailId=request.getParameter("EmailId");
		String Password=request.getParameter("Password");
		
		PrintWriter out=response.getWriter();
		String message=" ";
		boolean errorFlag=false;
		String html;
		
		/*if(EmailId=="null"||EmailId.trim().length()==0){
			message=message+"Please enter a valid Email Id</br>";
			errorFlag=true;
		}
		if(Password=="null"||Password.trim().length()==0){
			message=message+"Please enter a valid password</br> ";
			errorFlag=true;
		}*/
		
		if(errorFlag==false){//No error occured
			Properties p=new Properties();
			File file=new File("RegisteredUsers.txt");
			if(file.exists()){
				p.load(new FileInputStream("RegisteredUsers.txt"));
				String value=p.getProperty(EmailId);//Value contains Password.
				
				if(p.containsKey(EmailId)){
					if(value!=null&&value.equals(Password)){//Checking the Password stored in the file and the Password entered by the user is same.
						message=message+"Welcome back "+EmailId;
						html="<html><head></head><body>"+message+"</br>";
						html=html+"<form action=\"ViewProducts\" method=\"post\"><input type=\"submit\" value=\"View products\"/></form></br>";
						html=html+"<a href=\"ChangePassword\">Change password</a></br><a href=\"logout\">Logout</a></br><a href=\"DeleteProduct\">Delete product</a></br><a href=\"AddProduct.html\">Add product</a></body></html>";
						
						HttpSession session=request.getSession(true);
						session.setAttribute("user",EmailId);
						
						RequestDispatcher rd=request.getRequestDispatcher("ViewProducts");  
					    rd.forward(request, response);
					}
					else{
						message=message+"Incorrect Password";
						html="<html><head></head><body>"+message+"</body></html>";
					}
				}
				else{
					message=message+" Account with the Email Id does not exist. Please create an account and login";
					html="<html><head></head><body>"+message+"</body></html>";
				}
			}else{
				message=message+" Account with the Email Id does not exist. Please create an account and login";
				html="<html><head></head><body>"+message+"</body></html>";
			}
			out.println(html);
		}
		else{
			html="<html><head></head><body>Error! "+message+"</body></html>";
			out.println(html);
		}
	}
}