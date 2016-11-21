package leetcode;

public class A158_Read_N_Characters_Given_Read4_II_Call_multiple_times {
	class Solution extends Reader4 {
	    /**
	     * @param buf Destination buffer
	     * @param n   Maximum number of characters to read
	     * @return    The number of characters read
	     */
	    char[] buf4 = new char[4];
	    int buf4Start = 0, buf4Len = 0;
	    
	    public int read(char[] buf, int n) {
	        int ptrEnd = 0, numRead = 4, numWrite = 0;
	        
	        if (buf4Len > 0) {
	            numWrite = Math.min(buf4Len, n);
	            copyBuf(buf4, buf, buf4Start, 0, numWrite);
	            ptrEnd = numWrite;
	            buf4Start += numWrite;
	            buf4Len -= numWrite;
	        }
	        
	        while (ptrEnd < n && numRead >= 4) {
	            numRead = read4(buf4);
	            if (ptrEnd + numRead < n) {
	                numWrite = numRead;
	            }
	            else {
	                numWrite = n - ptrEnd;
	                buf4Start = numWrite;
	                buf4Len = numRead - numWrite;
	            }
	            copyBuf(buf4, buf, 0, ptrEnd, numWrite);
	            ptrEnd += numWrite;
	        }
	        
	        return ptrEnd;
	    }
	    
	    private void copyBuf(char[] from, char[] to, int fromStart, int toStart, int len) {
	        for (int i = 0; i < len; i++) {
	            to[toStart + i] = from[fromStart + i];
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
