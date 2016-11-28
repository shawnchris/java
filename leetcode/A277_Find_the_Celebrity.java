package leetcode;

public class A277_Find_the_Celebrity {
    public int findCelebrity(int n) {
        int i = 0, j = n - 1;
        while (i != j) {
            if (knows(i, j)) { // i is not celebrity
                i++;
            }
            else { // j is not celebrity
                j--;
            }
        }
        
        for (j = 0; j < n; j++) {
            if (i == j) continue;
            if (!knows(j, i) || knows(i, j)) return -1;
        }
        
        return i;
    }
    
    // fake knows() function to make the program compile
    private boolean knows(int a, int b) {
		return false;
	}

}
