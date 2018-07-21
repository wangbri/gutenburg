package guten;

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class TextParser {
	File file;

    public TextParser(String filename) {
		file = new File(filename);
    }

    public int getTotalNumberOfWords() {
    	ArrayList<String> words = new ArrayList<>();

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String[] splitString = sc.nextLine().split(" ");
    	    	for (String word : splitString) {
    	    		words.add(word);
    	    	}
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return words.size();
    }

    // TODO: do in constructor? but good to decouple classes
    public int getTotalUniqueWords() {
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

    	return words.size();
    }

    public ArrayList<Tup> get20MostFrequentWords() {
    	HashMap<String, Integer> words = new HashMap<>();
    	PriorityQueue<Tup> maxHeap = new PriorityQueue<>();

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();
    	    	if (!curLine.isEmpty()) {
	    	    	String[] splitString = curLine.split("\\s+");
	    	    	for (String word : splitString) {
	    	    		if (words.containsKey(word)) {
	    	    			words.put(word, words.get(word) + 1);
	    	    		} else {
	    	    			words.put(word, 1);
	    	    		}
	    	    	}
	    	    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	// System.out.println(words);

    	for (Map.Entry<String, Integer> entry : words.entrySet()) {
    		String curWord = entry.getKey();
    		int curCount = entry.getValue();
    		Tup tup = new Tup(curWord, curCount);

    		if (maxHeap.size() <= 5) {
    			maxHeap.add(tup);
    			// System.out.println("Adding " + tup);
    		} else if (curCount > maxHeap.peek().count) {
    			maxHeap.add(tup);
    			maxHeap.poll();
    			// System.out.println("Replacing " + maxHeap.poll() + " with " + tup);
    		}
    	}

    	// can't cast Object[] to Tup[] so toArray() doesn't work
    	ArrayList<Tup> top20 = new ArrayList<>();
    	while (maxHeap.size() > 0) {
    		top20.add(maxHeap.poll());
    	}

    	Collections.sort(top20);

    	return top20;
    }

    public HashSet<String> txtToHash(String fileName, int total) {
    	int count = 0;
    	HashSet<String> set = new HashSet<>();

    	try {
	    	Scanner sc = new Scanner(new File(fileName));
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();
    	    	if (!curLine.isEmpty()) {
    	    		set.add(curLine);
	    	    } else {
	    	   		continue;
	    	    }

    	    	if (count == total) {
    	    		break;
    	    	} else {
    	    		count += 1;
    	    	}
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return set;
    }

    public ArrayList<Tup> get20MostInterestingFrequentWords() {
    	HashMap<String, Integer> words = new HashMap<>();
    	PriorityQueue<Tup> maxHeap = new PriorityQueue<>();
    	HashSet<String> mostCommon = txtToHash("./1-1000.txt", 100);
    	System.out.println(mostCommon);

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();
    	    	if (!curLine.isEmpty()) {
	    	    	String[] splitString = curLine.split("\\s+");
	    	    	for (String word : splitString) {
	    	    		if (!mostCommon.contains(word) && word.length() > 0) {
		    	    		if (words.containsKey(word)) {
		    	    			words.put(word, words.get(word) + 1);
		    	    		} else {
		    	    			words.put(word, 1);
		    	    		}
		    	    	}
	    	    	}
	    	    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	// System.out.println(words);

    	for (Map.Entry<String, Integer> entry : words.entrySet()) {
    		String curWord = entry.getKey();
    		int curCount = entry.getValue();
    		Tup tup = new Tup(curWord, curCount);

    		if (maxHeap.size() <= 5) {
    			maxHeap.add(tup);
    			// System.out.println("Adding " + tup);
    		} else if (curCount > maxHeap.peek().count) {
    			maxHeap.add(tup);
    			maxHeap.poll();
    			// System.out.println("Replacing " + maxHeap.poll() + " with " + tup);
    		}
    	}

    	// can't cast Object[] to Tup[] so toArray() doesn't work
    	ArrayList<Tup> top20 = new ArrayList<>();
    	while (maxHeap.size() > 0) {
    		top20.add(maxHeap.poll());
    	}

    	Collections.sort(top20);

    	return top20;
    }

    public ArrayList<Tup> get20LeastFrequentWords() {
    	HashMap<String, Integer> words = new HashMap<>();
    	PriorityQueue<Tup> minHeap = new PriorityQueue<>(20, new TupComparator());

    	try {
	    	Scanner sc = new Scanner(file);
    	    while (sc.hasNextLine()) {
    	    	String curLine = sc.nextLine();
    	    	if (!curLine.isEmpty()) {
	    	    	String[] splitString = curLine.split("\\s+");
	    	    	for (String word : splitString) {
	    	    		if (word.length() > 0 ) {
		    	    		if (words.containsKey(word)) {
		    	    			words.put(word, words.get(word) + 1);
		    	    		} else {
		    	    			words.put(word, 1);
		    	    		}
		    	    	}
	    	    	}
	    	    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	// System.out.println(words);

    	for (Map.Entry<String, Integer> entry : words.entrySet()) {
    		String curWord = entry.getKey();
    		int curCount = entry.getValue();
    		Tup tup = new Tup(curWord, curCount);

    		if (minHeap.size() <= 5) {
    			minHeap.add(tup);
    			// System.out.println("Adding " + tup);
    		} else if (curCount > minHeap.peek().count) {
    			minHeap.add(tup);
    			minHeap.poll();
    			// System.out.println("Replacing " + maxHeap.poll() + " with " + tup);
    		}
    	}

    	// can't cast Object[] to Tup[] so toArray() doesn't work
    	ArrayList<Tup> bottom20 = new ArrayList<>();
    	while (minHeap.size() > 0) {
    		bottom20.add(minHeap.poll());
    	}

    	Collections.sort(bottom20);

    	return bottom20;
    }
}