package com.web.utreader.utility;


import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.web.utreader.model.Comment;
import com.web.utreader.model.NewsDetails;
import com.web.utreader.model.NewsSummary;
import com.web.utreader.model.UserInfo;

// APT: this solves potential "not registered" failures, e.g., if we bringup app and immediately go to ViewAllStreams.jsp
// call to data store read via objectify service will fail even if stream class calls the register() method 
// See this for details: https://code.google.com/p/objectify-appengine/wiki/BestPractices#How_NOT_To_Register_Entities
public class OfyService {
    static {
    	factory().register(NewsDetails.class);
    	factory().register(Comment.class);
    	factory().register(UserInfo.class);
    	factory().register(CategoryInfo.class);
    }
    
    public static Objectify ofy() {
        return com.googlecode.objectify.ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return com.googlecode.objectify.ObjectifyService.factory();
    }

}