package interview.linkedin;
import java.util.StringTokenizer; 
import java.util.Arrays;

public class Library {
	/** This is the interface that represents nested lists. 
	 * You should not implement it, or speculate about its implementation. */ 
	interface NestedInteger { 
		/** @return true if this NestedInteger holds a single integer, rather than a nested list */ 
		boolean isInteger();
		/** @return the single integer that this NestedInteger holds, if it holds a single integer 
		 * * Return null if this NestedInteger holds a nested list */ 
		Integer getInteger(); 
		/** @return the nested list that this NestedInteger holds, if it holds a nested list 
		 * * Return null if this NestedInteger holds a single integer */ 
		NestedInteger[] getList(); 
		int getCount(); 
	}
	public int depthSum (NestedInteger input, int count) {
		int result = 0;
		for (NestedInteger n : input.getList()) {
			if (n.isInteger())
				result += n.getInteger() * count;
			else
				result += depthSum(n, count + 1);
		}
		return result;
	}
	
	public double myPow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double result = 1;
        double base = x;
        for (; n > 0; n = n >> 1) {
            if (n % 2 == 1) {
                result = result * base;
            }
            base = base * base;
        }
        return result;
    }
	public double myPow2(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        double result = 1;
        for (int i = 0; i < n; i++) {
        	result *= x;
        }
        return result;
    }
	
	public boolean isNumber(String s) {
		return s.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
	
	public interface Triangle { 
		/** Three segments of lengths A, B, C form a triangle iff 
		 * A + B > C 
		 * B + C > A 
		 * A + C > B 
		 * e.g. 
		 * 6, 4, 5 can form a triangle 
		 * 10, 2, 7 can't
		 * Given a list of segments lengths algorithm should find at least one triplet of segments that form a triangle (if any). 
		 * Method should return an array of either: 
		 * - 3 elements: segments that form a triangle (i.e. satisfy the condition above) 
		 * - empty array if there are no such segments */ 
		int[] getTriangleSides(int[] segments);
	}
	public class TriangleImpl implements Triangle {
		public int[] getTriangleSides(int[] segments) {
			int i, j , k, n = segments.length;
			int[] ret = null; 
			if (n < 3) return ret; 
			Arrays.sort(segments); 
			for (k = n - 1; k - 2 >= 0; k--) {
				if ( k != n - 1 && segments[k] == segments[k+1]) continue; 
				j = k - 1;
				i = k - 2; 
				if (segments[i] + segments[j] > segments[k]) { 
					ret= new int[] {segments[i], segments[j], segments[k]}; 
					break; 
				} 
			} 
			return ret;
		} 
	}
	
	/*
	int shortestDistance(vector<string>& words, string word1, string word2) {
		 4         int n = words.size(), idx1 = -1, idx2 = -1, dist = INT_MAX;
		 5         for (int i = 0; i < n; i++) {
		 6             if (words[i] == word1) idx1 = i;
		 7             else if (words[i] == word2) idx2 = i;
		 8             if (idx1 != -1 && idx2 != -1)
		 9                 dist = min(dist, abs(idx1 - idx2));
		10         }
		11         return dist;
		12     }
	*/
	public int shortestDistance(String[] words, String word1, String word2) {
		int dist = Integer.MAX_VALUE;
		int len = words.length;
		int idx1 = -1, idx2 = -1;
		for (int i = 0; i < len; i++) {
			if (words[i] == word1) idx1 = i;
			if (words[i] == word2) idx2 = i;
			if (idx1 != -1 && idx2 != -1)
				dist = Math.min(dist, Math.abs(idx1 - idx2));
		}
		
		return dist;
	}
	
	class Interval implements Comparable<Interval> {
		public int start;
		public int end;
		@Override
		public int compareTo(Interval i2) {
			return this.start - i2.start;
		}
		public Interval(int start, int end) {
			this.start = start;
			this.end =  end;
		}
	}
	public int countOverlap(int[][] intervals) {
		int result = 0;
		int len = intervals.length;
		Interval[] iArray = new Interval[len];
		for (int i = 0; i < len; i++) {
			iArray[i] = new Interval(intervals[i][0], intervals[i][1]);
		}
		Arrays.sort(iArray);
		int start = iArray[0].start, end = iArray[0].end;
		
		for (int i = 1; i < len; i++) {
			if (iArray[i].start > end) { //broken, calculate result
				result += (end - start);
				start = iArray[i].start;
				end = iArray[i].end;
			}
			else { //overlap with previous intervals, combine them
				end = Math.max(end, iArray[i].end);
			}
		}
		result += (end - start);
		
		return result;
	}
	
	int counter;
	public void printFactors(int number) {
		counter = 0;
		System.out.println(number + " * 1"); 
		printFactors("", number, number);
		System.out.println(counter);
	}
	private void printFactors(String expression, int dividend, int previousFactor) {
		for (int factor = dividend / 2; factor >= 2; factor--) {
			if (dividend % factor == 0 && factor <= previousFactor) {
				int nextDividend = dividend / factor;
				if (nextDividend <= factor)
					System.out.println(expression + factor + " * " + nextDividend);
				printFactors(expression + factor + " * ", nextDividend, factor);
			}
			counter++;
		}
	}

	public String justify(String s, int len) {
		if(s.length() == len) return s;
		if(s.length() > len) {
			System.out.println("length of string is greater than line-justify length");
			return s;
		}
		int count = 0;
		String[] st = s.split("\\s+");
		int pureLen = 0;
		for (int i = 0; i < st.length; i++)
			pureLen += st[i].length();
		count = st.length - 1;
		int totalSpacing = len - pureLen;
		int individualSpacing = totalSpacing / count;
		int padding = totalSpacing % count;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < st.length; i++) {
			sb.append(st[i]);
			if(i < st.length - 1) {
				for(int j = 0; j < individualSpacing; j++)
					sb.append(" ");
			}
			if(padding > 0) {
				sb.append(" ");
				padding--;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Library l = new Library();
		l.printFactors(128);
		
		String test = "Hello this is a    test string";
		String result = l.justify(test,40);
		System.out.println(result + " " + result.length()); 
		System.out.println(l.justify(test,32));
		Interval[] i = new Interval[10];
	}
	
}
