package guten;

import java.util.*;

public class Tup implements Comparable<Tup> {
	public String word;
	public int count;

	public Tup(String word, int count) {
		this.word = word;
		this.count = count;
	}

	@Override
	public int compareTo(Tup t) {
		return this.count - t.count;
	}

	@Override
	public String toString() {
		return "[" + this.word + "," + this.count + "]";
	}
}