import guten.TextParser;

public class Gutenburg {
    public static void main (String[] args) {
		TextParser txtParser = new TextParser("./11.txt");
		//System.out.println(txtParser.getTotalNumberOfWords());
		//System.out.println(txtParser.getTotalUniqueWords());
		System.out.println(txtParser.get20MostFrequentWords());
		System.out.println(txtParser.get20MostInterestingFrequentWords());
		System.out.println(txtParser.get20LeastFrequentWords());
    }
}