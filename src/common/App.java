package common;

import java.util.Date;
import java.util.List;

//Simple version of App class,used for Steam Json API translation 
public class App {
	private int appid;
	private String name;
	
	
	public App(int appid, String name) {
		this.appid = appid;
		this.name = name;
	}
	
	public int getappid() {
		return appid;
	}
	
	
	public String getname() {
		return name;
	}
	
	
}
