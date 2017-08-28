package analysis;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class StopWordList {
	
	private final String stopWordFileName = "stopword.txt";
	
	private Set<String> wordSet = new HashSet<>();

	private static StopWordList instance = new StopWordList();

	private StopWordList() {
		try {
			File stopWordFile = new File(stopWordFileName);
			BufferedReader reader = new BufferedReader(new FileReader(stopWordFile));
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if(line.isEmpty()) continue;
				wordSet.add(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static StopWordList getInstance() {
		return instance;
	}

	public boolean contains(String str) {
		return wordSet.contains(str);
	}

}

