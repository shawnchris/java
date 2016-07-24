package leetcode;

public class A048_Rotate_Image {
	public void rotate(int[][] matrix) {
        int len = matrix.length;
        if (len < 2) return;
        
        for (int y = 0; y < len/2; y++)
            for (int x = y; x < len-y-1; x++) {
                int temp = matrix[y][x];
                temp = move(matrix, temp, x, len-y-1);
                temp = move(matrix, temp, len-y-1, len-x-1);
                temp = move(matrix, temp, len-x-1, y);
                matrix[y][x] = temp;
            }
    }
    
    private int move(int[][] matrix, int num, int y, int x) {
        int temp = matrix[y][x];
        matrix[y][x] = num;
        return temp;
    }
}
