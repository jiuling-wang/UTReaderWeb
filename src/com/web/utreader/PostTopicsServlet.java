package com.web.utreader;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;
import com.web.utreader.model.UserInfo;
import com.web.utreader.utility.OfyService;

public class PostTopicsServlet extends HttpServlet{
	public void  doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		String username = req.getParameter("username");
		String topics = req.getParameter("topics");
		List<UserInfo> list= OfyService.ofy().load().type(UserInfo.class).filter("username", username).list();
		if (list.isEmpty()){
			UserInfo userInfo = new UserInfo(username,topics);
			OfyService.ofy().save().entity(userInfo).now();
		}else{
			for (UserInfo info:list){
				if (info.username.equals(username)){
					info.topics = topics;
					OfyService.ofy().save().entity(info).now();
				}
			}
		}
		
	}

}
