package leetcode;
import java.util.*;

public class A378_Kth_Smallest_Element_in_a_Sorted_Matrix {
    class Cell {
        int val, row, col;
        public Cell(int v, int r, int c) {
            this.val = v;
            this.row = r;
            this.col = c;
        }
    }
    class CellComparator implements Comparator<Cell> {
        public int compare(Cell c1, Cell c2) {
            return c1.val - c2.val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Cell> pq = new PriorityQueue<>(new CellComparator());
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            pq.add(new Cell(matrix[i][0], i, 0));
        }
        for (int i = 1; i < k; i++) {
            Cell c = pq.poll();
            if (c.col < n - 1) {
                pq.add(new Cell(matrix[c.row][c.col + 1], c.row, c.col + 1));
            }
        }
        return pq.poll().val;
    }
    
    public int kthSmallest2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> (a[0] - b[0]));
        
        for (int i = 0; i < m; i++)
        	pq.offer(new int[] {matrix[i][0], i, 0});
        
        for (int i = 0; i < k - 1; i++) {
            int[] t = pq.poll();
            if (t[2] + 1 < n)
                pq.offer(new int[] {matrix[t[1]][t[2] + 1], t[1], t[2] + 1});
        }
        
        return pq.poll()[0];
    }
    
    public static void main(String[] args) {
    	A378_Kth_Smallest_Element_in_a_Sorted_Matrix ks = new A378_Kth_Smallest_Element_in_a_Sorted_Matrix();
    	int[][] m = {{1,3,5},{6,7,12},{11,14,14}};
    	System.out.println(ks.kthSmallest2(m, 6));
    }
}
