package algs63;
import  stdlib.*;

/* ***********************************************************************
 *  Compilation:  javac SuffixArray.java
 *  Execution:    java SuffixArray < input.txt
 *
 *  A data type that computes the suffix array of a string.
 *
 *  % java SuffixArray < abra.txt
 *    i ind lcp rnk  select
 *  ---------------------------
 *    0  11   -   0  !
 *    1  10   0   1  A!
 *    2   7   1   2  ABRA!
 *    3   0   4   3  ABRACADABRA!
 *    4   3   1   4  ACADABRA!
 *    5   5   1   5  ADABRA!
 *    6   8   0   6  BRA!
 *    7   1   3   7  BRACADABRA!
 *    8   4   0   8  CADABRA!
 *    9   6   0   9  DABRA!
 *   10   9   0  10  RA!
 *   11   2   2  11  RACADABRA!
 *
 *  WARNING: This program assumes that the {@code substring()} method takes
 *  constant time and space. Beginning with Oracle / OpenJDK Java 7, Update 6,
 *  the substring method takes linear time and space in the size of the
 *  extracted substring. Do NOT use this code with such versions.
 *
 *  See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  for more details.
 *
 *************************************************************************/

import java.util.Arrays;

public class SuffixArray {
	private final String[] suffixes;
	private final int N;

	public SuffixArray(String s) {
		N = s.length();
		suffixes = new String[N];
		for (int i = 0; i < N; i++)
			suffixes[i] = s.substring(i);
		Arrays.sort(suffixes);
	}

	// size of string
	public int length() { return N; }

	// index of ith sorted suffix
	public int index(int i) { return N - suffixes[i].length(); }

	// ith sorted suffix
	public String select(int i) { return suffixes[i]; }

	// number of suffixes strictly less than query
	public int rank(String query) {
		int lo = 0, hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = query.compareTo(suffixes[mid]);
			if      (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}

	// length of longest common prefix of s and t
	private static int lcp(String s, String t) {
		int N = Math.min(s.length(), t.length());
		for (int i = 0; i < N; i++)
			if (s.charAt(i) != t.charAt(i)) return i;
		return N;
	}

	// longest common prefix of suffixes(i) and suffixes(i-1)
	public int lcp(int i) {
		return lcp(suffixes[i], suffixes[i-1]);
	}

	// longest common prefix of suffixes(i) and suffixes(j)
	public int lcp(int i, int j) {
		return lcp(suffixes[i], suffixes[j]);
	}

	public static void main(String[] args) {
		String s = StdIn.readAll().replaceAll("\n", " ").trim();
		SuffixArray suffix = new SuffixArray(s);


		StdOut.println("  i ind lcp rnk  select");
		StdOut.println("---------------------------");

		for (int i = 0; i < s.length(); i++) {
			int index = suffix.index(i);
			String ith = "\"" + s.substring(index, Math.min(index + 50, s.length())) + "\"";
			// String ith = suffix.select(i);
			int rank = suffix.rank(ith);
			if (i == 0) {
				StdOut.format("%3d %3d %3s %3d  %s\n", i, index, "-", rank, ith);
			}
			else {
				int lcp = suffix.lcp(i, i-1);
				StdOut.format("%3d %3d %3d %3d  %s\n", i, index, lcp, rank, ith);
			}
		}

	}

}
