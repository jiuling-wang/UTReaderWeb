package com.web.utreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.utreader.model.Comment;
import com.web.utreader.model.UserInfo;
import com.web.utreader.utility.OfyService;

public class GetTopicsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		final Logger log = Logger.getLogger(GetTopicsServlet.class.getName());
		String username = req.getParameter("username");
		List<UserInfo> list = OfyService.ofy().load().type(UserInfo.class).list();
		String res = "";
		for (UserInfo info : list){
			log.warning("username = " + info.username );
			log.warning("topics = " + info.topics );
			if (info.username.equals(username)){
				res = info.topics;
				break;
			}
			
		}	
		resp.getWriter().println(res);
	}

}
