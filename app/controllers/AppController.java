package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import play.i18n.Lang;
import play.mvc.*;
import play.mvc.Http.Cookie;
import utils.AppLogger;
import utils.Cookies;
import utils.RequestHeaders;

public class AppController extends Controller {
	
	public static String formatTimestamp(final long t) {
		return new SimpleDateFormat("yyyy-dd-MM HH:mm:ss").format(new Date(t));
	}
	
	public static boolean needLanguageRedirect(String lang) {
		if(findLanguage(lang) == null) {
    		// Lets try to find a fallback languges (Ex: "de" from "de-CH")
    		String fallBackLang = findFallbackLanguage(lang);
    		if(fallBackLang == null) {
    			// Requested language not found in supported languages
        		Cookie langCookie = request().cookie(Cookies.PLAY_LANG);
        		if(langCookie == null) {
        			// No cookie or cookie language no longer supported
    	    		String preferredLang = Lang.preferred(request().acceptLanguages()).code();
    	    		AppLogger.debugLog("Not supported language change to " + preferredLang + " and set cookie");
    	    		changeLang(preferredLang);
        		} else if(findLanguage(langCookie.value()) == null) {
        			fallBackLang = findFallbackLanguage(langCookie.value());
        			if(fallBackLang == null) {
        				fallBackLang = Lang.preferred(request().acceptLanguages()).code();
        			}
        			AppLogger.debugLog("Not supported language change to " + fallBackLang);
        			// Let user the best we can guess
        			changeLang(langCookie.value());
        		} else {
        			AppLogger.debugLog("Not supported language change to " + langCookie.value());
        			// Let use the cookie language
        			changeLang(langCookie.value());
        		}
    		} else {
    			// Let set the fallback language
    			changeLang(fallBackLang);
    		}
    		return true;
    	} else {
    		Cookie langCookie = request().cookie(Cookies.PLAY_LANG);
    		if(langCookie == null || !langCookie.value().equals(lang)) {
    			// language doesn't match the cookie lets update it
    			AppLogger.debugLog("Set application language to " + lang);
    			changeLang(lang);
    		}
    	}
		return false;
	}
	
	public static List<Locale> requestLocales() {
		String[] languages = request().headers().get(RequestHeaders.ACCEPTLANGUAGE);
		List<Locale> locales = new ArrayList<Locale>(languages.length);
		for (String str : languages){
		    String[] arr = str.trim().replace("-", "_").split(";");
	
		    //Parse the locale
		    Locale locale = null;
		    String[] l = arr[0].split("_");
		    switch(l.length){
		        case 2: locale = new Locale(l[0], l[1]); break;
		        case 3: locale = new Locale(l[0], l[1], l[2]); break;
		        default: locale = new Locale(l[0]); break;
		    }
	
		    //Parse the q-value
		    Double q = 1.0D;
		    for (String s : arr){
		        s = s.trim();
		        if (s.startsWith("q=")){
		            q = Double.parseDouble(s.substring(2).trim());
		            break;
		        }
		    }
		    locales.add(locale);
		}
		return locales;
	}
	
	public static String findLangByCode(String lang) {
		for(Lang language : Lang.availables()) {
			if(language.code().equals(lang)) return language.code();
		}
		return null;
	}
	
	public static String findLangByLanguage(String lang) {
		for(Lang language : Lang.availables()) {
			if(language.language().equals(lang)) return language.code();
		}
		return null;
	}
	
	public static String findLanguage(String lang) {
		if(lang.contains("-")) return findLangByCode(lang);
		else return findLangByLanguage(lang);	
	}
	
	public static String findFallbackLanguage(String lang) {
		String[] components = lang.split("-");
		if(components.length == 2) return findLangByLanguage(components[0]);
		return null;
	}
	
	protected static String logRequest() {
		StringBuilder sb = new StringBuilder();
		sb.append("Headers:");
        Iterator<Entry<String, String[]>> iter = request().headers().entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, String[]> entry = iter.next();
            sb.append(entry.getKey());
            sb.append('=').append('"');
            sb.append(Arrays.toString(entry.getValue()));
            sb.append('"');
            sb.append("\n");
        }
        sb.append("\nBody:");
        sb.append(request().body().asText());
        sb.append("\nFormUrlEncoded:");
        sb.append(request().body().asRaw());
        return sb.toString();
	}
	
	public Result goHome() {
		return indexI18n(lang().code());
	}
	
	public Result updateLang(String lang) {
		return redirect("/"+ lang + "/home.html");
	}
	
	public Result index() {
        return i18nRedirect("");
    }
    
    public Result indexI18n(String lang) {
    	return redirect("/"+ lang + "/home.html");
    }
	
    public Result i18nRedirect(String uri) {
    	Cookie langCookie = request().cookie(Cookies.PLAY_LANG);
    	if(langCookie == null) {
    		String lang = Lang.preferred(request().acceptLanguages()).code();
    		AppLogger.debugLog("Set application language to " + lang);
    		changeLang(lang);
    	} else if(findLanguage(langCookie.value()) == null) {
    		String lang = Lang.preferred(request().acceptLanguages()).code();
    		AppLogger.debugLog("Language cookie not found set language to " + lang);
    		changeLang(lang);
    	} else {
    		AppLogger.debugLog("Cookie Language " + langCookie.value());
    	}
        return redirect("/"+ lang().code() + "/"+ uri);
    }
    
    public Result routerI18n(String lang, String uri) {
    	if(needLanguageRedirect(lang)) return redirect("/"+ lang().code() + "/" + uri);
    	return notFound();
    }
}
