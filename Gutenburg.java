import guten.*;
import java.util.*;

public class Gutenburg {
    public static void main (String[] args) {
    	TextCleaner txtCleaner = new TextCleaner("./11.txt");
		ArrayList<String> chapters = txtCleaner.cleanText();

		TextParser txtParser = new TextParser("./11-cleaned.txt", chapters);
		System.out.println(txtParser.getTotalNumberOfWords());
		System.out.println(txtParser.getTotalUniqueWords());

		System.out.println(txtParser.get20MostFrequentWords());
		System.out.println(txtParser.get20MostInterestingFrequentWords());
		System.out.println(txtParser.get20LeastFrequentWords());

		System.out.println(txtParser.getFrequencyOfWord("queen"));
		System.out.println(txtParser.getChapterQuoteAppears("'Soo--oop of the e--e--evening, Beautiful, beautiful Soup!"));

		TextGenerator txtGenerator = new TextGenerator("./11-cleaned.txt");
		System.out.println(txtGenerator.generateSentence("the", 20));

		TextCompletor txtCompletor = new TextCompletor("./11-cleaned.txt");
		System.out.println(txtCompletor.getAutocompleteSentence("wit"));
    }
}