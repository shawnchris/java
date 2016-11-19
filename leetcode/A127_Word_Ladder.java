package leetcode;
import java.util.*;

public class A127_Word_Ladder {
	public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        Set<String> used = new HashSet<>();
        
        queue.offer(beginWord);
        used.add(beginWord);
        int step = 0;
        
        while (!queue.isEmpty()) {
        	step++;
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		String current = queue.poll();
        		// Permutate next possible word
        		for (int j = 0; j < current.length(); j++) {
        			char[] ca = current.toCharArray();
        			for (int k = 0; k < 26; k++) {
        				ca[j] = (char)('a' + k);
        				String next = new String(ca);
        				if (next.equals(endWord)) {
        					return step + 1;
        				}
        				if (wordList.contains(next) && !used.contains(next)) {
        					used.add(next);
        					queue.add(next);
        					System.out.println("Step: " + step + " next: " + next);
        				}
        			}
        		}
        	}
        }
        
        return 0;
	}
	
	public static void main(String[] args) {
		String beginWord = "hit";
		String endWord = "cog";
		Set<String> wordList = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));
		System.out.println(ladderLength(beginWord, endWord, wordList));
	}

}
