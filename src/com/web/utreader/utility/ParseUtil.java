package com.web.utreader.utility;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.web.utreader.model.NewsDetails;
import com.web.utreader.model.NewsSummary;

public class ParseUtil {
	public static String replace(String s){
		String res = s.replaceAll("“", "\"");
		res=res.replaceAll("”",  "\"");
		res = res.replaceAll("’", "\'");
		res = res.replaceAll("’","\'");
		res = res.replaceAll("�", " ");
		return res;
		
	}
	public static boolean getNewsByCategory(int index,CategoryInfo info) throws IOException{

		String url = info.getURL(index);	
		Document document = Jsoup.connect(url).get();
		Elements links = document.select(".story-title a");
		Elements times = document.select(".story-time");
		Elements digests = document.select("div.story-teaser");
		Elements authors = document.select("div.story-author");
		String titlestamp = info.getLatest(index);
		
		Element link0 = links.get(0);
		if (titlestamp.equals(link0.text())){
				return false;
			}else{
				info.setLatest(index, link0.text());
		}
		int cid = index;
		int id = info.getID(cid);
		for (int i = links.size()-1; i >= 0; i--){
			Element link = links.get(i);
			Element time = times.get(i);
			Element digest = digests.get(i);
			Element author = authors.get(i);
			String t = time.attr("datetime").substring(11);
			String src = author.text().substring(0,author.text().indexOf("-"));
			
			String newURL = "http://www.dailytexanonline.com"+link.attr("href");
			Document doc = Jsoup.connect(newURL).get();
			Elements paragraphs = doc.getElementById("article-body").getElementsByTag("p");
            String articleString = "";
            for (Element p:paragraphs){
            	if (p.text().length() < 10) continue;
            	articleString = articleString+p.text() +"\n\n";
            }
            Elements imgs = doc.select("#article-main-image [src]");
            String imgURL = null;
            if (imgs.size()>0){
            	imgURL = imgs.get(0).attr("abs:src");
            }
            NewsDetails nd = new NewsDetails(imgURL,replace(link.text()),replace(digest.text()),replace(articleString), src, t, cid,id);
            OfyService.ofy().save().entity(nd).now();
			id = id + 1;
            info.incID(cid);
		}
		OfyService.ofy().save().entity(info);
		return true;
	}

}
