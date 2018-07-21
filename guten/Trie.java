package guten;

import java.util.*;

public class Trie {
	TrieNode root;

	public Trie() {
		root = new TrieNode(' ');
	}

	public void insert(String word) {
		if (search(word)) {
			return;
		}

		TrieNode current = root;
		TrieNode prev = null;

		// iterate over each character and add each character to Trie
		for (char ch : word.toCharArray()) {
			prev = current;
			TrieNode child = current.getChild(ch); // get matching child if exists

			if (child != null) {
				current = child;
				child.parent = prev;
			} else {
				current.children.add(new TrieNode(ch));
				current = current.getChild(ch);
				current.parent = prev;
			}
		}

		current.isEnd = true; // mark ending of word
	}

	public boolean search(String word) {
		TrieNode current = root;

		for (char ch : word.toCharArray()) {
			TrieNode child = current.getChild(ch);

			if (child != null) {
				current = child;
			} else {
				return false;
			}
		}

		if (current.isEnd) {
			return true;
		}

		return false;
	}
}

class TrieNode {
	char data;
	LinkedList<TrieNode> children;
	TrieNode parent;
	boolean isEnd;

	public TrieNode(char data) {
		this.data = data;
		children = new LinkedList<>();
		isEnd = false;
	}

	// returns first child node with data
	public TrieNode getChild(char data) {
		if (children != null) {
			for (TrieNode child : children) {
				if (child.data == data) {
					return child;
				}
			}
		}

		return null;
	}

	// returns a list of words formed by the children, recursively
	public List<String> getWords() {
		List<String> wordList = new ArrayList<>();

		if (isEnd) {
			wordList.add(toString());
		}

		if (children != null) {
			for (TrieNode child : children) {
				wordList.addAll(child.getWords());
			}
		}

		return wordList;
	}

	// recursively builds string of current traversal
	public String toString() {
		if (parent == null) {
			return "";
		} else {
			return parent.toString() + String.valueOf(data);
		}
	}
}


















