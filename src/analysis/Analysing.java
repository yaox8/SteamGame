package analysis;

import java.io.File;
import java.util.List;

import common.App;
import crawler.Json;


public class Analysing {
public static void main(String[] args) throws Exception{
	File file=new File("steam");
	String htmldir="/SteamApps/HtmlPage";
	String listdir="/SteamApps";
	List<App> applist=Json.TranslteJson(file);
	
	System.out.println("Fetching titles...");
	Type.initial(applist, htmldir,listdir);
	List<App> gamelist=Type.getApplist(applist, 0);
	
	System.out.println("Processing terms...");

	List<Count> order=TextProcess.textProcess(gamelist);
	System.out.println(order.size()+" terms in total...");

	TextProcess.print(order, 100);
	}	
}
