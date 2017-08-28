package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHtml {
	public static String getHtml(String urlString) throws InterruptedException {
		try {
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString);
			System.out.println("Try to connect: "+url);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				html.append(temp).append("\n");
			}
			br.close();
			isr.close();
			System.out.println("success");
			
			return html.toString();
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String readHtml(String urlString,int line) {
		try {
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String result="";
			for(int i=0;i<line;i++){
				result=br.readLine();
			}
			br.close();
			isr.close();
			return result;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void write(int id,String html,String dir) {
		File dirOfHtmlPages=new File(dir);
		if(!dirOfHtmlPages.exists() && !dirOfHtmlPages.isDirectory())
			dirOfHtmlPages.mkdirs();
		
		String htmlFilePath = dirOfHtmlPages + "/" + String.valueOf(id);
		File htmlFile = new File(htmlFilePath);
		FileWriter htmlFileWriter = null;
	
		try {
			htmlFile.createNewFile();
			htmlFileWriter = new FileWriter(htmlFilePath, true);

			htmlFileWriter.write(html);
		
			htmlFileWriter.flush();
			htmlFileWriter.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
