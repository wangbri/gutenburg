package guten;

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class TextCompletor {
	Trie trie;
	File file;

	public TextCompletor(String fileName) {
		file = new File(fileName);
		trie = new Trie();	

		// TODO: more efficient to iterate as list or store in file and scan each line?
		// insert each word into Trie
    	HashMap<String, Integer> words = new HashMap<>();

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

    	for (String word : words.keySet()) {
    		trie.insert(word);
    	}
	}

	// traverse to last node associated with last char in prefix and return all subtrees
	public List<String> getAutocompleteSentence(String prefix) {
		TrieNode current = trie.root;
		for(char ch : prefix.toCharArray()) {
			current = current.getChild(ch);

			if (current == null) {
				return new ArrayList<String>();
			}
		}

		List<String> wordList = current.getWords();
		wordList.remove(prefix);

		return wordList;
	}
}