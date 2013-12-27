package com.web.utreader.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class Comment {
	@Id
	public String uidString;
	public int id;
	public int cid;
	public String time;
	public String name;
	public String content;
	
	public Comment(){
		
	}
	public Comment(int cid, int id, String name,
			String content){
		Date date = new Date();
		
		this.time=date.toString();
		this.cid = cid;
		this.id = id;
		this.content = content;
		this.name = name;
		this.uidString = this.name+this.cid+this.id+this.time;
	}

}
