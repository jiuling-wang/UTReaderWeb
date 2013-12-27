package com.web.utreader.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class NewsDetails {
	@Id
	public String title;
	public String source;
	public String time; 
	public String digest;
	public String article;
	public String imgURL;
	@Index
	public int cid;
	@Index
	public int id;
	
	public NewsDetails(){
		
	}
	

	public NewsDetails(String imgURL,String title, String digest,String article, String source, String time,int cid,int id){
		this.title = title;
		this.digest = digest;
		this.article = article;
		this.source = source;
		this.time = time;
		this.cid = cid;
		this.id = id;
		this.imgURL = imgURL;
	}

}
