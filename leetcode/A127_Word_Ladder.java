package leetcode;
import java.util.*;

public class A127_Word_Ladder {
	static class Node {
        String word;
        Node parent;
        public Node(String w) { this.word = w; parent = null; }
    }
    public static int ladderLength2(String beginWord, String endWord, Set<String> wordList) {
        if (beginWord.equals(endWord)) return 1;
        
        Set<String> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(beginWord));
        visited.add(beginWord);
        
        boolean found = false;
        int level = 1;
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            for (int i = 0; i < size && !found; i++) {
                Node node = queue.poll();
                for (int j = 0; j < node.word.length() && !found; j++) {
                	char[] array = node.word.toCharArray();
                    for (int k = 0; k < 26; k++) {
                        array[j] = (char)('a' + k);
                        String next = new String(array);
                        if (next.equals(endWord)) {
                            printPath(node);
                            System.out.println(next);
                            found = true;
                            break;
                        }
                        if (wordList.contains(next) && !visited.contains(next)) {
                            Node newNode = new Node(next);
                            newNode.parent = node;
                            queue.add(newNode);
                            visited.add(next);
                        }
                    }
                }
            }
            level++;
        }
        
        if (found) return level;
        return 0;
    }
    
    private static void printPath(Node node) {
        if (node.parent != null) printPath(node.parent);
        System.out.print(node.word + "->");
    }
    
    
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
        					//System.out.println("Step: " + step + " next: " + next);
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
		System.out.println(ladderLength2(beginWord, endWord, wordList));
	}

}
