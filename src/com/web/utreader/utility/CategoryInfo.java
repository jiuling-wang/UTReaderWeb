package com.web.utreader.utility;

import java.util.logging.Logger;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.web.utreader.CheckUpdatesServlet;
@Entity
public class CategoryInfo {
	@Id
	Long id;
	String[] categories = {"university","sports","fashion","games-and-tech","music","food","books"};
	String[] latest = {"","","","","","",""};
	String url="http://www.dailytexanonline.com/section/";
	public int[] currectIndex = {0,0,0,0,0,0,0};
	
	public CategoryInfo() {
		
	}
	public CategoryInfo(Long d){
		id = d;
	}
	public String getURL(int index){
		return url+categories[index];
	}
	public String getLatest(int index){
		return latest[index];
	}
	
	public void setLatest(int index, String titlestamp){
		latest[index] = titlestamp;
	}
	
	public int getID(int idx){
		return currectIndex[idx];
	}
	
	public void incID(int idx){
		currectIndex[idx]++;
	}

}
