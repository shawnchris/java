package leetcode;

public class A488_Zuma_Game {
	public static int findMinStep(String board, String hand) {
		if (board == null || board.length() == 0) return 0;
		
        char[] ball = {'R', 'Y', 'B', 'G', 'W'};
        String[] pattern = {"RR", "YY", "BB", "GG", "WW"};
        int[] count = new int[5];
        int left = hand.length();
        
        for (int i = 0; i < hand.length(); i++) {
        	char c = hand.charAt(i);
        	if (c == 'R') count[0]++;
        	else if (c == 'Y') count[1]++;
        	else if (c == 'B') count[2]++;
        	else if (c == 'G') count[3]++;
        	else if (c == 'W') count[4]++;
        }
        
        while (left > 0) {
        	// Search for reduce balls
        	String shortest = board;
        	int j = 0;
        	for (int i = 0; i < 5; i++) {
        		if (count[i] <= 0) continue;
        		int index = 0;
        		while (index >= 0 && index < board.length()) {
        			index = board.indexOf(pattern[i], index);
        			if (index < 0) continue;
        			String r = reduce(board.substring(0, index) + ball[i] + board.substring(index));
        			if (r.length() < shortest.length()) {
        				shortest = r;
        				j = i;
        			}
        			index++;
        		}
        	}
        	if (shortest.length() < board.length()) {
        		count[j]--;
        		board = shortest;
        		if (board.length() == 0)
        			return hand.length() - left - 1;
        	}
        	else {
        		for (int i = 0; i < board.length(); i++) {
        			char c = board.charAt(i);
        			if (c == 'R' && count[0] > 0 || c == 'Y' && count[1] > 0
        					|| c == 'B' && count[2] > 0 || c == 'G' && count[3] > 0
        					|| c == 'W' && count[4] > 0) { 
        				shortest = board.substring(0, i) + c + board.substring(i);
        				break; 
        			}
        		}
        		if (shortest.length() == board.length()) return -1;
        	}
        	left--;
        }
		
		return -1;
    }
	
	private static String reduce(String s) {
		return s;
	}
	public static void main(String[] args) {
		System.out.println(findMinStep("", ""));
		System.out.println(findMinStep("WRRBBW", "RB"));
		System.out.println(findMinStep("WWRRBBWW", "WRBRW"));
		System.out.println(findMinStep("G", "GGGGG"));
		System.out.println(findMinStep("RBYYBBRRB", "YRBGB"));
	}

}
