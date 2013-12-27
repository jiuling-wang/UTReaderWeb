package com.web.utreader;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.utreader.utility.CategoryInfo;
import com.web.utreader.utility.OfyService;
import com.web.utreader.utility.ParseUtil;

public class CheckUpdatesServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException{
		final Logger log = Logger.getLogger(CheckUpdatesServlet.class.getName());
		List<CategoryInfo> list = OfyService.ofy().load().type(CategoryInfo.class).list();
		
		if (list.isEmpty()){
			log.warning("check for updates info null" );
			list.add(new CategoryInfo((long) 1));
		}
		for (int i = 0; i < 7; i++){
			ParseUtil.getNewsByCategory(i,list.get(0));
		}
		OfyService.ofy().save().entities(list).now();
	}

}
