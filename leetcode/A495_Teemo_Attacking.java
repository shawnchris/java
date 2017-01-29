package leetcode;

public class A495_Teemo_Attacking {
    public int findPosisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) return 0;
        
        int result = 0, start = timeSeries[0], end = timeSeries[0] + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] > end) { // new interval
                result += end - start;
                start = timeSeries[i];
                end = timeSeries[i] + duration;
            }
            else { // merge
                end = timeSeries[i] + duration;
            }
        }
        result += end - start;
        
        return result;
    }
    
	public static void main(String[] args) {
		A495_Teemo_Attacking ta = new A495_Teemo_Attacking();
		System.out.println(ta.findPosisonedDuration(new int[] {1, 4}, 2));
		System.out.println(ta.findPosisonedDuration(new int[] {1, 2}, 2));
		System.out.println(ta.findPosisonedDuration(new int[] {1, 2}, 10000000));
	}

}
