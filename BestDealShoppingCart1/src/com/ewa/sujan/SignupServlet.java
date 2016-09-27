package com.ewa.sujan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SignupServlet extends HttpServlet {

    public SignupServlet() {
        super();
        System.out.println("Inside no argument constructor of Signup Servlet");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside doPost() of LoginServlet");
		String Name=request.getParameter("Name");
		String EmailId=request.getParameter("EmailId");
		String Password=request.getParameter("Password");
		String RepeatPassword=request.getParameter("RepeatPassword");
		
		/*String userRole="";
		String domain=EmailId.substring(EmailId.indexOf("@"), EmailId.length());
		if(domain.equals("bestdeal.com")){
			userRole="StoreManager";
		}*/
		
		PrintWriter out=response.getWriter();
		String message=" ";
		String errorMessage=" ";
		boolean errorFlag=false;
	
		/*if(Name=="null"||Name.trim().length()==0){
			message=message+"Please enter a valid Name</br>";
			errorFlag=true;
		}

		if(Password=="null"||Password.trim().length()==0){
			message=message+"Please enter a valid password</br>";
			errorFlag=true;
		}
		if(EmailId=="null"||EmailId.trim().length()==0){
			message=message+"Please enter a valid Email Id</br>";
			errorFlag=true;
		}*/
		if(Password!=null&&RepeatPassword!=null){
			if(!Password.equals(RepeatPassword)){
				message=message+"Password and Repeat Password should be same!</br>";
				errorFlag=true;
			}
		}
		
		if(errorFlag==false){//No input validation errors
						
			Properties p=new Properties();
			File file=new File("RegisteredUsers.txt");
			FileOutputStream output = null;
			String html="";
			
			if(file.exists()){
				System.out.println("RedisteredUsers.txt file exists");
				
				p.load(new FileInputStream("RegisteredUsers.txt"));
				String value=p.getProperty(EmailId);
				
				if(value!=null){
					System.out.println("Account with entered Email Id already exists!");
					message=message+"Account already exists with this Email Id.Please try to login";
					html="<!DOCTYPE html> <html> <head> <title>Login</title> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/Login.css'> </head> <body> <table> <tr> 	<th>LOGIN <i class='fa fa-sign-in' aria-hidden='true'></i></th> </tr> <tr> 	<td> 	<div id='Message'>"+message+"</div> 	<div id='enterDiv'> 		<form action='' method='post'> 			<div><input type='text'  class='credentials' name='EmailId' placeholder='Email ID' maxlength='25'></input></div> 			<div><input type='password'  class='credentials' name='Password' placeholder='Password'></input></div> 			<div><button type='submit' value='login' id='loginButton' class='registerButtons'>Login <i class='fa fa-lock' aria-hidden='true'></i></button></div> 			<div><button id='signUpButton' class='registerButtons'><a href='Signup.html' id='signupLink'>New to Best Deal? Sign up</a></button></div> 		</form> 	</div> 	</td> </tr> </table> </body> </html>";
					out.println(html);
					return;
				}
				else{
					System.out.println("Registered with new Email Id");
					output=new FileOutputStream(file);
					p.setProperty(EmailId,Password);
					p.store(output,"RegisteredUsers");//Store the file in the file system.
					message=message+"Account is created successfully!!Please login";
					html="<!DOCTYPE html> <html> <head> <title>Login</title> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/Login.css'> </head> <body> <table> <tr> 	<th>LOGIN <i class='fa fa-sign-in' aria-hidden='true'></i></th> </tr> <tr> 	<td> 	<div id='Message'>"+message+"</div> 	<div id='enterDiv'> 		<form action='' method='post'> 			<div><input type='text'  class='credentials' name='EmailId' placeholder='Email ID' maxlength='25'></input></div> 			<div><input type='password'  class='credentials' name='Password' placeholder='Password'></input></div> 			<div><button type='submit' value='login' id='loginButton' class='registerButtons'>Login <i class='fa fa-lock' aria-hidden='true'></i></button></div> 			<div><button id='signUpButton' class='registerButtons'><a href='Signup.html' id='signupLink'>New to Best Deal? Sign up</a></button></div> 		</form> 	</div> 	</td> </tr> </table> </body> </html>";
					out.println(html);
				}
			}
			else{
				System.out.println("Created new file RegisteredUsers.txt");
				p.setProperty(EmailId,Password);
				p.store(new FileOutputStream("RegisteredUsers.txt"),"RegisteredUsers");//Store the file in the file system.
				message=message+"Account is created successfully!Please login";
				
				html="<!DOCTYPE html> <html> <head> <title>Login</title> <link rel='stylesheet' href='./CSS/font-awesome-4.6.3/css/font-awesome.min.css'> <link rel='stylesheet' href='./CSS/Login.css'> </head> <body> <table> <tr> 	<th>LOGIN <i class='fa fa-sign-in' aria-hidden='true'></i></th> </tr> <tr> 	<td> 	<div id='Message'>"+message+"</div> 	<div id='enterDiv'> 		<form action='' method='post'> 			<div><input type='text'  class='credentials' name='EmailId' placeholder='Email ID' maxlength='25'></input></div> 			<div><input type='password'  class='credentials' name='Password' placeholder='Password'></input></div> 			<div><button type='submit' value='login' id='loginButton' class='registerButtons'>Login <i class='fa fa-lock' aria-hidden='true'></i></button></div> 			<div><button id='signUpButton' class='registerButtons'><a href='Signup.html' id='signupLink'>New to Best Deal? Sign up</a></button></div> 		</form> 	</div> 	</td> </tr> </table> </body> </html>";
				out.println(html);
			}
		}
		else{
			String html="<html><head></head><body>Error! "+message+"</body></html>";
			out.println(html);
		}
	}
}