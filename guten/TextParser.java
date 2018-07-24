package guten;

import java.io.File;
import java.util.Scanner;
import java.util.*;

public class TextParser {
	File textFile;
    ArrayList<String> chapters;

    public TextParser(String textFile, ArrayList<String> chapters) {
		this.textFile = new File(textFile);
        this.chapters = chapters;
    }

    public String cleanText(String text) {
        // positive lookahead and negative lookbehind
        String sentenceAnchors = "(?<=[,.!?;:-])(?!$)";

        return text.replaceAll(sentenceAnchors, " ").replaceAll("\\p{P}", "").trim().replaceAll("\\s+", " ").toLowerCase();
    }

    public int getTotalNumberOfWords() {
    	ArrayList<String> words = new ArrayList<>();

    	try {
	    	Scanner sc = new Scanner(textFile);
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
	    	Scanner sc = new Scanner(textFile);
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
	    	Scanner sc = new Scanner(textFile);
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

    		if (maxHeap.size() < 20) {
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
        Collections.reverse(top20);

    	return top20;
    }

    public HashSet<String> txtToHash(String fileName, int total) {
    	int count = 0;
    	HashSet<String> set = new HashSet<>();

    	try {
	    	Scanner sc = new Scanner(new File(fileName));
    	    while (sc.hasNextLine()) {
    	    	String curLine = cleanText(sc.nextLine());
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
    	HashSet<String> mostCommon = txtToHash("./1-1000.txt", 2000);
    	//System.out.println(mostCommon);

    	try {
	    	Scanner sc = new Scanner(textFile);
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

    		if (maxHeap.size() < 20) {
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
        Collections.reverse(top20);

    	return top20;
    }

    public ArrayList<Tup> get20LeastFrequentWords() {
    	HashMap<String, Integer> words = new HashMap<>();
    	PriorityQueue<Tup> minHeap = new PriorityQueue<>(20, new Comparator<Tup>() {
            @Override
            public int compare(Tup t1, Tup t2) {
                return t2.count - t1.count;
            }
        });

    	try {
	    	Scanner sc = new Scanner(textFile);
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

    		if (minHeap.size() < 20) {
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

    public ArrayList<Integer> getFrequencyOfWord(String inputWord) {
        String findWord = inputWord.toLowerCase();

        int chapterIndex = -1;
        int[] wordFrequencies = new int[chapters.size()];
        ArrayList<Integer> frequencyList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                String curLine = sc.nextLine();
                if (!curLine.isEmpty()) {
                    if (chapters.contains(curLine)) {
                        chapterIndex += 1;
                        continue;
                    }

                    String[] splitString = curLine.split("\\s+");
                    for (String word : splitString) {
                        if (word.equals(findWord) && chapterIndex > -1) {
                            wordFrequencies[chapterIndex] = wordFrequencies[chapterIndex] + 1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i : wordFrequencies) {
            frequencyList.add(i);
        }

        return frequencyList;
    }

    public int getChapterQuoteAppears(String inputQuote) {
        String findQuote = cleanText(inputQuote);
        String[] quoteWords = findQuote.split("\\s+");
        int chapterIndex = -1;
        int quoteIndex = 0;

        try {
            Scanner sc = new Scanner(textFile);
            while (sc.hasNextLine()) {
                String curLine = sc.nextLine();
                if (!curLine.isEmpty()) {
                    if (chapters.contains(curLine)) {
                        chapterIndex += 1;
                        continue;
                    }

                    String[] splitString = curLine.split("\\s+");
                    for (String word : splitString) {
                        if (word.equals(quoteWords[quoteIndex]) && chapterIndex > -1) {
                            quoteIndex += 1;

                            if (quoteIndex == quoteWords.length) {
                                return chapterIndex + 1;
                            }
                        } else {
                            quoteIndex = 0;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}