package guten;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class TextCleaner {
	File file;

	public TextCleaner(String fileName) {
		file = new File(fileName);
	}

	public void cleanText() {
		// positive lookahead and negative lookbehind
		String sentenceAnchors = "(?<=[,.!?;:-])(?!$)";

		String fileName = file.getName();
		File fileOut = new File(fileName.substring(0, fileName.indexOf(".")) + "-cleaned.txt");

    	try {
        	PrintWriter out = new PrintWriter(fileOut);
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine().replaceAll(sentenceAnchors, " ").replaceAll("\\p{P}", "").toLowerCase();
    	    	out.println(curLine);
    	    }

    	    out.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
}