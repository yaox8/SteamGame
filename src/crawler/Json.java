package crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.App;
import common.Applist;


//translation from Json to java class
public class Json {
	public static List<App> TranslteJson(File TextFile) throws Exception {
		
		FileReader file=new FileReader(TextFile);

		Gson gson = new Gson();
		Applist apps= gson.fromJson(file ,Applist.class);
		List<App> result= apps.getapps();
        

		return result;
	}
	
	
}
