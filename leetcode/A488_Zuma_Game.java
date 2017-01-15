package leetcode;
import java.util.*;

public class A488_Zuma_Game {
	public int findMinStep(String board, String hand) {
        List<Character> boardList = new ArrayList<Character>();
        for (char c : board.toCharArray()) {
            boardList.add(c);
        }
        Map<Character,Integer> handMap = new HashMap<>();
        handMap.put('R',0);
        handMap.put('Y',0);
        handMap.put('B',0);
        handMap.put('G',0);
        handMap.put('W',0);
        for (char h : hand.toCharArray()) {
            handMap.put(h, handMap.get(h) + 1);
        }
        return find(boardList, handMap);
    }
    
    private int find(List<Character> board, Map<Character, Integer> hand) {
        cleanupBoard(board);
        if (board.size() == 0) return 0;
        if (empty(hand)) return -1;
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i<board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size() - 1 || board.get(i+1) != c) {
                int missing = 3 - count;
                if (hand.get(c) >= missing) {
                    hand.put(c, hand.get(c) - missing);
                    List<Character> smallerBoard = new ArrayList<>(board);
                    for (int j = 0; j<count; j++) {
                        smallerBoard.remove(i-j);
                    }
                    int smallerFind = find(smallerBoard, hand);
                    if ( smallerFind != -1 ) {
                        min = Math.min(smallerFind + missing, min);
                    }
                    hand.put(c, hand.get(c) + missing);
                }
                count = 0;
            }
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
    
    private void cleanupBoard(List<Character> board) {
        int count = 0;
        boolean cleaned = false;
        for (int i = 0; i<board.size(); i++) {
            char c = board.get(i);
            count++;
            if (i == board.size() - 1 || board.get(i+1) != c) {
                if (count >= 3) {
                    for (int j = 0; j<count; j++) {
                        board.remove(i-j);
                    }
                    cleaned = true;
                    break;
                }
                count = 0;
            }
        }
        if (cleaned) {
            cleanupBoard(board);
        }
    }
    
    private boolean empty(Map<Character,Integer> hand) {
        for (int val : hand.values()) {
            if (val > 0) return false;
        }
        return true;
    }
    
	public static void main(String[] args) {
		A488_Zuma_Game zg = new A488_Zuma_Game();
		System.out.println(zg.findMinStep("", ""));
		System.out.println(zg.findMinStep("WRRBBW", "RB"));
		System.out.println(zg.findMinStep("WWRRBBWW", "WRBRW"));
		System.out.println(zg.findMinStep("G", "GGGGG"));
		System.out.println(zg.findMinStep("RBYYBBRRB", "YRBGB"));
	}

}
