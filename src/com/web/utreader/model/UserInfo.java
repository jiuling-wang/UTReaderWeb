package com.web.utreader.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UserInfo {
	@Id
	public String username;
	public String topics;
	public UserInfo(){
		
	}
	
	public UserInfo(String name, String t){
		username = name;
		topics = t;
	}
	

}
