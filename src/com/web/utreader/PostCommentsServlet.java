package com.web.utreader;



import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.web.utreader.model.Comment;
import com.web.utreader.utility.OfyService;


public class PostCommentsServlet extends HttpServlet{
	public void  doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		final Logger log = Logger.getLogger(PostCommentsServlet.class.getName());
		int cid = Integer.parseInt(req.getParameter("cid"));
		int id = Integer.parseInt(req.getParameter("id"));
		String content = req.getParameter("content");
		String name = req.getParameter("name");
		Comment comment = new Comment(cid,id,name,content);
		log.warning(comment.toString());
		OfyService.ofy().save().entity(comment).now();
		
	}

}
