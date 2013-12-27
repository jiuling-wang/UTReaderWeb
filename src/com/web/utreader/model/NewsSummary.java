package com.web.utreader.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class NewsSummary {
	@Id
	public String title;
	public String source;
	public String time; 
	public String digest;
	@Index
	public int cid;
	@Index
	public int id;
	public NewsSummary(){
		
	}
	public NewsSummary(String title, String digest,String source, String time,int cid,int id) {
		this.title = title;
		this.digest = digest;
		this.source = source;
		this.time = time;
		this.cid = cid;
		this.id = id;
	}
	
}
