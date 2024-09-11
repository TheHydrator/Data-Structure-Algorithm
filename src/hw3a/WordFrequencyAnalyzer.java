package hw3a;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;  // Import the File class

import algs31.SequentialSearchST;


public class WordFrequencyAnalyzer {
	/**********************************************************************************/
	/* You are not allowed to add any fields to this class beyond the one given below */
	/* You may only read in from the file once.  This means you may only use a single */
	/* word reader object.                                                            */
	/**********************************************************************************/
	
	// Maintain a counter for each word in the text.
	SequentialSearchST<String, Integer> counters;
	
	/**
	 * Stores a count of the number of times any word appears in a file.  The file is
	 * read in exactly once at the time time this object is constructed.
	 * 
	 * @param filename the name of the file on which to count all word occurrences.
	 */
	public WordFrequencyAnalyzer(String filename) {
		// Initialize the counters
		counters = new SequentialSearchST<String, Integer>();
		// Read in the file
		WordReader wr = new WordReader(filename);
		// Read in the file

		while (wr.hasNextWord()) {
			String word = wr.nextWord();
			word = word.toLowerCase();// Read next word and convert to lowercase
			if (counters.contains(word)) {
				counters.put(word, counters.get(word) + 1);
			} else {
				counters.put(word, 1);
			}
			
		}
	}

	public class WordReader {
		private Scanner scanner;
	
		public WordReader(String filename) {
			try {
				scanner = new Scanner(new File(filename));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public boolean hasNextWord() {
			return scanner.hasNext();
		}
	
		public String nextWord() {
			return scanner.next();
		}
	
		public void close() {
			scanner.close();
		}
	}
	
	
	/**
	 * Returns the number of times a given word appears in the file from which this
	 * object was created.
	 * 
	 * @param word the word to count
	 * @return the number of times <code>word</code> appears.
	 */
	public int getCount(String word) {
		word = word.toLowerCase();
		if (counters.contains(word)) {
			return counters.get(word);
		}
		//throw new RuntimeException("Not implemented.");
		return 0;
	}
	
	/**
	 * Returns the maximum frequency over all words in the file from which this
	 * ojbect was created.
	 * 
	 * @return the maximum frequency of any word in the the file.
	 */
	public int maxCount() {
		int maxCount = 0;
		for (String wordString : counters.keys()) {
			int count = counters.get(wordString);
			if (count > maxCount) {
				maxCount = count;
			}
			
		}
		//throw new RuntimeException("Not implemented.");
		return maxCount;
	}
}
