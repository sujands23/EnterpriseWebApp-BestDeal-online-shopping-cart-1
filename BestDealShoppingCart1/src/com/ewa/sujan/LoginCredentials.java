package com.ewa.sujan;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginCredentials {

	HashMap<String,UserDetails> userMap=new HashMap<String, UserDetails>();
	
	public HashMap<String,UserDetails> getUserMap(){
		return userMap;
	}
	
	public void addUser(String UserId,String Password,String EmailId){
		userMap.put(UserId, new UserDetails(UserId,Password,EmailId));
	}
	
	Set<Map.Entry<String, UserDetails>> entries=userMap.entrySet();
	
	public void printUserMap(){
		System.out.println("Inside printUserMap() of LoginCredentials");
		for(Map.Entry<String, UserDetails> userMap:entries){
			System.out.println();
			UserDetails userDetails=userMap.getValue();
			System.out.println(userDetails.getUserId()+" :  "+userDetails.getPassword()+"  "+userDetails.getEmailId());
		}
	}
	
	public boolean containsUser(String UserId){
		System.out.println("Inside containsUser() of LoginCredentials");
		if(userMap.containsKey(UserId)){
			return true;
		}
		else{
			return false;
		}
	}
}
