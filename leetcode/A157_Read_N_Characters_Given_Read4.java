package leetcode;

public class A157_Read_N_Characters_Given_Read4 {
	
	class Solution extends Reader4 {
	    /**
	     * @param buf Destination buffer
	     * @param n   Maximum number of characters to read
	     * @return    The number of characters read
	     */
	    public int read(char[] buf, int n) {
	        int ptrEnd = 0, numRead = 4, numWrite = 0;
	        char[] buf4 = new char[4];
	        while (ptrEnd < n && numRead >= 4) {
	            numRead = read4(buf4);
	            if (ptrEnd + numRead < n) {
	                numWrite = numRead;
	            }
	            else {
	                numWrite = n - ptrEnd;
	            }
	            copyBuf(buf4, buf, ptrEnd, numWrite);
	            ptrEnd += numWrite;
	        }
	        
	        return ptrEnd;
	    }
	    
	    private void copyBuf(char[] from, char[] to, int start, int len) {
	        for (int i = 0; i < len; i++) {
	            to[start + i] = from[i];
	        }
	    }
	}
	
	// Fake class to make code compile
	class Reader4 {
		public int read4(char[] buf) {
			return 0;
		}
	}
}
