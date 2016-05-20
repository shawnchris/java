package leetcode;
import java.util.*;
/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file. The
 * return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file. By using the read4
 * API, implement the function int read(char *buf, int n) that reads n
 * characters from the file. Note: The read function will be called many times.
 */
public class L158_Read_N_Characters_Given_Read4II {
	Queue<Character> remain = new LinkedList<>();
	private int read4(char[] buf) {
		return buf.length;
	}
    public int read(char[] buf, int n) {
    	int i = 0;
    	while(i < n && !remain.isEmpty()){
            buf[i] = remain.poll();
            i++;
		}
		for (; i < n; i += 4) {
			char[] tmp = new char[4];
			int len = read4(tmp);
			if (len > n - i) {
				System.arraycopy(tmp, 0, buf, i, n - i);
				for (int j = n - i; j < len; j++) {
					remain.offer(tmp[j]);
				}

			} else {
				System.arraycopy(tmp, 0, buf, i, len);
			}

			if (len < 4)
				return Math.min(i + len, n);
		}
		return n;
    }
}
