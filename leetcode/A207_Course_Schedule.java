package leetcode;
import java.util.*;

public class A207_Course_Schedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return true;
        
        int[] inCount = new int[numCourses];
        Set<Integer>[] children = new Set[numCourses];
        for (int i = 0; i < numCourses; i++) {
            children[i] = new HashSet<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
        	int parent = prerequisites[i][1];
        	int child = prerequisites[i][0];
        	if (!children[parent].contains(child)) {
        		inCount[child]++;
        		children[parent].add(child);
        	}
        }
        
        Queue<Integer> noDependency = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inCount[i] == 0)
                noDependency.add(i);
        }
        
        int finished = 0;
        while(!noDependency.isEmpty()) {
            Integer c = noDependency.poll();
            for (Integer i : children[c]) {
                inCount[i]--;
                if (inCount[i] == 0)
                    noDependency.add(i);
            }
            finished++;
        }
        
        return finished == numCourses;
    }
    
    public static void main(String[] args) {
    	int[][] test1 = {{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}};
    	System.out.println(canFinish(10, test1));
    }
}
