package guten;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class TextCleaner {
	File file;

	public TextCleaner(String fileName) {
		file = new File(fileName);
	}

	public ArrayList<String> cleanText() {
		ArrayList<String> chapters = new ArrayList<>();

		// positive lookahead and negative lookbehind
		String sentenceAnchors = "(?<=[,.!?;:-])(?!$)";

		String fileName = file.getName();
		File cleanedOut = new File(fileName.substring(0, fileName.indexOf(".")) + "-cleaned.txt");
		File chaptersOut = new File(fileName.substring(0, fileName.indexOf(".")) + "-chapters.txt");

    	try {
        	PrintWriter cleanedWriter = new PrintWriter(cleanedOut);
        	PrintWriter chaptersWriter = new PrintWriter(chaptersOut);
	    	Scanner sc = new Scanner(file);

    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine().replaceAll(sentenceAnchors, " ").replaceAll("\\p{P}", "").trim().replaceAll("\\s+", " ").toLowerCase();

    	    	cleanedWriter.println(curLine);

    	    	if (curLine.contains("chapter")) {
    	    		chaptersWriter.println(curLine);
    	    		chapters.add(curLine);
    	    	}
    	    }

    	    cleanedWriter.close();
    	    chaptersWriter.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return chapters;
	}
}