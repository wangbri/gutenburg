package guten;

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class TextGenerator {
	File file;

	public TextGenerator(String fileName) {
		file = new File(fileName);
	}

	public String getWordAfter(String word, int numAfter) {
    	HashMap<String, Integer> wordAppearances = new HashMap<>();
    	Boolean getNextWord = false;
    	String nextWord = "";

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();

    	    	if (!curLine.isEmpty() && curLine.contains(word)) {
    	    		List<String> splitString = Arrays.asList(curLine.split("\\s+"));

    	    		if (splitString.size() > 0) {
    	    			nextWord = splitString.get(0); // store first word

			    	    if (getNextWord) {
			    	    	if (wordAppearances.containsKey(nextWord)) {
			    	    		wordAppearances.put(nextWord, wordAppearances.get(nextWord) + 1);
			    	    	} else {
			    	    		wordAppearances.put(nextWord, 1);
			    	    	}

			    	    	getNextWord = false;
			    	    }

		    	    	int wordIndex = splitString.indexOf(word);

		    	    	// if the word is at the end of the current line, grab the first word from next line
		    	    	if (wordIndex == splitString.size() - 1) {
		    	    		getNextWord = true;
		    	    	} else {
		    	    		nextWord = splitString.get(wordIndex + 1);

		    	    		if (wordAppearances.containsKey(nextWord)) {
		    	    			wordAppearances.put(nextWord, wordAppearances.get(nextWord) + 1);
		    	    		} else {
		    	    			wordAppearances.put(nextWord, 1);
		    	    		}
		    	    	}
    	    		}
	    	    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	String maxWord = "";
    	int maxCount = 0;
    	ArrayList<Tup> wordsAppearances = new ArrayList<>(); 

	  	for (Map.Entry<String, Integer> entry : wordAppearances.entrySet()) {
    		String curWord = entry.getKey().replaceAll("\\s", "");
    		int curCount = entry.getValue();

    		if (curWord.length() > 0 && !curWord.equals(word)) 
    			wordsAppearances.add(new Tup(curWord, curCount));
    	}

    	Collections.sort(wordsAppearances);

    	if (wordsAppearances.size() > 0) {
    		return wordsAppearances.get(numAfter - 1).word;
    	} else {
    		return "";
    	}
	}

	public String generateSentence(String word, int length) {
		HashMap<String, Integer> wordsAfterCount = new HashMap<>();
		StringBuilder sentence = new StringBuilder();
		String curWord = word; //"she";
		sentence.append(curWord);

		for (int i = 0; i < length; i++) {

			// prevent loops in sentence structure, more randomness "the but the but the.."
			if (!wordsAfterCount.containsKey(curWord)) {
				wordsAfterCount.put(curWord, 1);
			} else {
				wordsAfterCount.put(curWord, wordsAfterCount.get(curWord) + 1);
			}

			String nextWord = getWordAfter(curWord, wordsAfterCount.get(curWord));
			sentence.append(" " + nextWord);
		}

		return sentence.toString();
	}
}