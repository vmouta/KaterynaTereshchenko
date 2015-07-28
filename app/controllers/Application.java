package controllers;

import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends AppController {

    public Result home(String lang) {
    	if(needLanguageRedirect(lang)) return redirect("/"+ lang().code() + "/home.html");
    	return ok(index.render("Kateryna Tereshchenko"));
    }
    
    public Result about(String lang) {
        return ok(about.render(""));
    }
    
    public Result contact(String lang) {
        return ok(contact.render(""));
    }
    
    public Result works(String lang) {
        return ok(works.render(""));
    }
    
    public Result blog(String lang) {
        return ok(blog.render(""));
    }
    
    public Result updateLang(String lang) {
		return redirect("/"+ lang + "/home.html");
	}
    
	/* Expoused routes to javascript */
	public Result jsRoutes() {
		return ok(Routes.javascriptRouter("jsRoutes")).as("text/javascript");
	}
}
