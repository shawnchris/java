package leetcode;
import java.util.*;

public class A210_Course_ScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        
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
        int[] result = new int[numCourses];
        while(!noDependency.isEmpty()) {
            Integer c = noDependency.poll();
            for (Integer i : children[c]) {
                inCount[i]--;
                if (inCount[i] == 0)
                    noDependency.add(i);
            }
            result[finished] = c;
            finished++;
        }
        
        return finished == numCourses ? result : new int[0];
    }
}
