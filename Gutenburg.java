import guten.TextParser;
import guten.TextCleaner;

public class Gutenburg {
    public static void main (String[] args) {
    	TextCleaner txtCleaner = new TextCleaner("./11.txt");
		txtCleaner.cleanText();

		TextParser txtParser = new TextParser("./11-cleaned.txt");
		//System.out.println(txtParser.getTotalNumberOfWords());
		//System.out.println(txtParser.getTotalUniqueWords());
		System.out.println(txtParser.get20MostFrequentWords());
		System.out.println(txtParser.get20MostInterestingFrequentWords());
		System.out.println(txtParser.get20LeastFrequentWords());
    }
}