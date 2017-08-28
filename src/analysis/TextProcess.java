package analysis;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.App;


public class TextProcess {
	public static List<Count> textProcess(List<App> applist) throws Exception {
		StopWordList stopWordList = StopWordList.getInstance();
		
		Map<String, Integer> map=new HashMap<String,Integer>(); 
		for (App app : applist){
			

            String name=app.getname();
            

            name=name.toLowerCase();
            
                        
            name=name.replaceAll("[^a-zA-Z]", " ");
			String[] strs = name.split("\\s+");
			for(String str:strs){
				if(!str.equals("")&&!stopWordList.contains(str)&&str.length()>1){
					

					if(map.containsKey(str)){
						map.put(str, map.get(str)+1);
					}else{
						map.put(str, 1);
					}
				}
			}
		}
		
		ArrayList<Count> countList= new ArrayList<Count>();
		for(String str:map.keySet()){
			countList.add(new Count(str,map.get(str)));
		}
		
		Collections.sort(countList,new Comparator<Count>(){
			public int compare(Count o1, Count o2) {
				return o2.getCount()-o1.getCount();
			}  
		});
		
		return countList;
	}
	
	public static void print(List <Count> countList,int rank) {
		for(int i=0;i<rank;i++){
			
			Count temp=countList.get(i);
			//System.out.print((i+1)+ ". ");
			//temp.print();
			System.out.println(temp.getToken());


			
		}
	}
	
	public static int check(List <Count> countList,String term) {
		for(int i=0;i<countList.size();i++){
			if(countList.get(i).getToken().equals(term)){
				System.out.println(term+":rank "+i+", "+countList.get(i).getCount()+" times");
				
				return i+1;
			}else{
				System.out.println("Term doesn't exist.");
				return 0;
			}
		}
		return -1;
	}
}
