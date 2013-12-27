package com.web.utreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.web.utreader.model.NewsDetails;
import com.web.utreader.model.NewsSummary;
import com.web.utreader.utility.CategoryInfo;
import com.web.utreader.utility.OfyService;
import com.web.utreader.utility.ParseUtil;

public class GetNewsDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		final Logger log = Logger.getLogger(GetNewsDetailsServlet.class.getName());
		int cid = Integer.parseInt(req.getParameter("cid"));
		int sid = Integer.parseInt(req.getParameter("sid"));
		List<CategoryInfo> infolist = OfyService.ofy().load().type(CategoryInfo.class).list();
		
		if (infolist.isEmpty()){
			infolist.add(new CategoryInfo((long) 1));
		}
		if (infolist.get(0).getID(cid) == 0){
			ParseUtil.getNewsByCategory(cid,infolist.get(0));
		}
		
		int endId = infolist.get(0).getID(cid) - sid;
		String string = "currentIndx = ";
		for (int i = 0; i < infolist.get(0).currectIndex.length; i++) string+=(""+infolist.get(0).currectIndex[i]+",");
		log.warning(string);
		log.warning("sid = " + sid + "  endid = " + endId);

		
		List<NewsDetails> list = OfyService.ofy().load().type(NewsDetails.class).filter("cid", cid).list();
		ArrayList<NewsDetails> newsDetailsList = new ArrayList<NewsDetails>();
//		for (NewsDetails s:list){
//			
//			if (s.id < endId && s.id >= startId){
//				newsDetailsList.add(s);
//				if (newsDetailsList.size() == 6){
//					break;
//				}
//			}
//		}
		for (NewsDetails s:list){
			if (s.id < sid || sid == 0){
				newsDetailsList.add(s);
			}
		}
		Collections.sort(newsDetailsList,new Comparator<NewsDetails>(){
			public int compare(NewsDetails a, NewsDetails b){
				if (a.id < b.id){
					return 1;
				}else{
					return -1;
				}
			}
		});
		ArrayList<NewsDetails> result = new ArrayList<NewsDetails>();
		if (newsDetailsList.size() <= 6){
			result = newsDetailsList;
		}else{
			for (int i = 0; i < 6; i++){
				result.add(newsDetailsList.get(i));
			}
		}
		OfyService.ofy().save().entities(infolist).now();
		Gson gson = new Gson();
		String json = gson.toJson(result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(json);
		
	}

}