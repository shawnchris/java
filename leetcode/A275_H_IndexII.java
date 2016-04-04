package leetcode;

public class A275_H_IndexII {

	public int hIndex(int[] citations) {
        int len = citations.length;
        if (len == 0) return 0;
        
        if (citations[len - 1] < 1) return 0;
        if (citations[0] >= len) return len;
        
        int start = 0, end = len - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (len - mid <= citations[mid]) { // search left
                end = mid;
            }
            else { // search right
                start = mid;
            }
        }
        
        if (len - start > citations[start])
            return len - start - 1;
        else
            return len - end - 1;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
