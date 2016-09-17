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
}
