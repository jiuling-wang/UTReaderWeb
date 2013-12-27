package com.web.utreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.Cookie;
import com.google.gson.Gson;
import com.web.utreader.model.Comment;
import com.web.utreader.utility.OfyService;

public class GetCommentsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int cid = Integer.parseInt(req.getParameter("cid"));
		int id = Integer.parseInt(req.getParameter("id"));
		List<Comment> list = OfyService.ofy().load().type(Comment.class).list();
		ArrayList<Comment> res = new ArrayList<Comment>();
		for (Comment comment : list){
			if (comment.id == id && comment.cid == cid){
				res.add(comment);
			}
			
		}
		Gson gson = new Gson();
		String json = gson.toJson(res);
		
		resp.setContentType("application/json");
		resp.getWriter().print(json);
	}

}
