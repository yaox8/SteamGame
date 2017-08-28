package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.App;

public class Crawler {
	public static Boolean crawling(List<App> applist,String dir) throws Exception {
		
		Set<Integer> recordSet = new HashSet<Integer>();
				
		File recorddir = new File(dir);
		String[] records=recorddir.list();
		for(String record:records){
			recordSet.add(Integer.valueOf(record));
		}
		
		System.out.println("Current files number:"+records.length);
		
		for (App app : applist){
			
			try {
            
				if(!recordSet.contains(app.getappid())){
						          
					String html=GetHtml.getHtml("https://steamdb.info/app/"+app.getappid());
					GetHtml.write(app.getappid(),html,dir);
					recordSet.add(app.getappid());
					
					Thread.sleep((long)(Math.random()*500+1000));            
				}   
				
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		System.out.println("All finished!");

		return true;
	}
}
