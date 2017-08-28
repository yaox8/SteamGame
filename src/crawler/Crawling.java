package crawler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.App;


public class Crawling {
public static void main(String[] args) throws Exception{
		
		//File file=new File("test");
		File file=new File("steam");
		String dir="/SteamApps/HtmlPage";
		List<App> applist=Json.TranslteJson(file);
		
		System.out.println("Total apps number: " + applist.size());
		
		Crawler.crawling(applist,dir);
	}	
}
