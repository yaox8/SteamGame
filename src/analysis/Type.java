package analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import common.App;

public class Type {
	private static List<Integer> gamelist=new ArrayList<Integer>();
	private static List<Integer> dlclist=new ArrayList<Integer>();
	private static List<Integer> videolist=new ArrayList<Integer>();
	private static List<Integer> softwarelist=new ArrayList<Integer>();
	private static List<Integer> otherlist=new ArrayList<Integer>();
	


	public static void initial(List<App> applist,String dir,String listdir) throws IOException {
		File gameFile = new File(listdir+"/gamelist");
		File dlcFile = new File(listdir+"/dlclist");
		File videoFile = new File(listdir+"/videolist");
		File softwareFile = new File(listdir+"/softwarelist");
		File otherFile = new File(listdir+"/otherlist");

		
		if(gameFile.exists()&&dlcFile.exists()&&videoFile.exists()&&softwareFile.exists()){
			try {
				BufferedReader reader = new BufferedReader(new FileReader(gameFile));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					if(line.isEmpty()) continue;
					gamelist.add(Integer.valueOf(line));
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(dlcFile));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					if(line.isEmpty()) continue;
					dlclist.add(Integer.valueOf(line));
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}try {
				BufferedReader reader = new BufferedReader(new FileReader(videoFile));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					if(line.isEmpty()) continue;
					videolist.add(Integer.valueOf(line));
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}try {
				BufferedReader reader = new BufferedReader(new FileReader(softwareFile));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					if(line.isEmpty()) continue;
					softwarelist.add(Integer.valueOf(line));
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
				
			}try {
				BufferedReader reader = new BufferedReader(new FileReader(otherFile));
				String line;
				while ((line = reader.readLine()) != null) {
					line = line.trim();
					if(line.isEmpty()) continue;
					otherlist.add(Integer.valueOf(line));
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}else{
			
			FileWriter gameWriter = new FileWriter(gameFile,true);
			BufferedWriter gameBF = new BufferedWriter(gameWriter);	
			FileWriter dlcWriter = new FileWriter(dlcFile,true);
			BufferedWriter dlcBF = new BufferedWriter(dlcWriter);	
			FileWriter videoWriter = new FileWriter(videoFile,true);
			BufferedWriter videoBF = new BufferedWriter(videoWriter);	
			FileWriter softwareWriter = new FileWriter(softwareFile,true);
			BufferedWriter softwareBF = new BufferedWriter(softwareWriter);	
			FileWriter otherWriter = new FileWriter(otherFile,true);
			BufferedWriter otherBF = new BufferedWriter(otherWriter);
			
			
			for(App app:applist){
				int appid=app.getappid();
				File html = new File(dir+"/"+appid);
				
				
				try {
					BufferedReader reader = new BufferedReader(new FileReader(html));
					System.out.println("Doc: "+appid+" is processing....");
					reader.readLine();			
					Document doc =Jsoup.parse(html, "UTF-8");

					Element etype=doc.select("td[itemprop=applicationCategory]").first();	
					String type=etype.text();					
					
					switch(type) {  
				    case "Game":
				    	gamelist.add(appid);
				    	gameBF.write(Integer.toString(appid));
				    	gameBF.newLine();
				    	break;
				    case "Downloadable Content":
				    	dlclist.add(appid);
				    	dlcBF.write(Integer.toString(appid));	
				    	dlcBF.newLine();
				    	break;
				    case "Application": 
				    	softwarelist.add(appid);
				    	softwareBF.write(Integer.toString(appid));
				    	softwareBF.newLine();
				    	break;
				    case "Video":
				    	videolist.add(appid);
				    	videoBF.write(Integer.toString(appid));
				    	videoBF.newLine();
				    	break;
				    default:
				    	otherlist.add(appid);
				    	otherBF.write(Integer.toString(appid));
				    	otherBF.newLine();
				}  
						
					reader.close();


				} catch(IOException e) {
						System.out.println("Exception when reading the file:" + e.getMessage());	
				}
				

			}
			
			for(int a:gamelist){
				System.out.println(a);
			}
			gameBF.flush();gameBF.close();gameWriter.close();
			dlcBF.flush();dlcBF.close();dlcWriter.close();
			softwareBF.flush();softwareBF.close();softwareWriter.close();
			videoBF.flush();videoBF.close();videoWriter.close();
			otherBF.flush();otherBF.close();otherWriter.close();			
		}
	}
	
	
	
	public static  int gettype(int appid) {
		int result=-1;
		if(gamelist.contains(appid))result=0;
		else if(dlclist.contains(appid))result=1;
		else if(softwarelist.contains(appid))result=2;
		else if(videolist.contains(appid))result=3;
		else if(otherlist.contains(appid))result=4;
		else System.out.println("Warning!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");	
		return result;
		
	}
	
	public static  List<App> getApplist(List<App> applist,int type){
		List<App> result=new ArrayList<App>();
		for(App app:applist){
			if(gettype(app.getappid())==type){
				result.add(app);
			}
		}
		return result;
		
	}
}
