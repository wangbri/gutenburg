package guten;

import java.util.*;

public class TextCleaner {
	File file;

	public TextCleaner(String fileName) {
		file = new File(fileName);
	}

	public void cleanText() {
		File fileOut = new File(file.getName + "-cleaned.txt");
        PrintWriter out = (new PrintWriter(new FileWriter(f2)));

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();
    	    	if (!curLine.isEmpty()) {
	    	    	String[] splitString = curLine.split("\\s+");
	    	    	for (String word : splitString) {
	    	    		if (!words.containsKey(word)) {
	    	    			words.put(word, 1);
	    	    		}
	    	    	}
	    	    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}