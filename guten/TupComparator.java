package guten;

import java.util.*;

public class TupComparator implements Comparator<Tup> {

	@Override
	public int compare(Tup t1, Tup t2) {
		return t2.count - t1.count;
	}
}