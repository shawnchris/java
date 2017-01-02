package datastructure;

public class BinaryIndexTree {
    public static void main(String args[]){
        int input[] = {1,2,3,4,5,6,7};
        BITree bt = new BITree(input);
        System.out.println(bt.getSum(6));
        bt.update(2, 5);
        System.out.println(bt.getSum(6));
        
        int[][] input2d = {
        		{3,0,1,4,2},
        		{5,6,3,2,1},
        		{1,2,0,1,5},
        		{4,1,0,1,7},
        		{1,0,3,0,5}};
        BITree2D bt2d = new BITree2D(input2d);
        System.out.println(bt2d.getSum(4, 3));
        bt2d.update(3, 3, 3);
        System.out.println(bt2d.getSum(4, 3));
    }

}

class BITree {
	private int[] tree;
	private int[] freq;
	
	public BITree (int[] input) {
		freq = new int[input.length];
		tree = new int[input.length + 1];
		for(int i = 0; i < input.length; i++){
            update(i, input[i]);
        }
	}
	
	public void update(int index, int value) {
		int delta = value - freq[index];
		freq[index] = value;
		
		index += 1;
		while (index < tree.length) {
            tree[index] += delta;
            index += (index & -index);
        }
	}
	
	public int getSum(int index) {
        int sum = 0;
        index += 1;
        while (index > 0) {
            sum += tree[index];
            index -= (index & -index);
        }
        return sum;
	}
}

class BITree2D {
	private int[][] tree;
	private int[][] freq;
	
	public BITree2D (int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		freq = new int[rows][cols];
		tree = new int[rows + 1][cols + 1];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				update(i, j, input[i][j]);
			}
		}
	}
	
	public void update(int row, int col, int value) {
		int delta = value - freq[row][col];
		freq[row][col] = value;
		
		row += 1; col += 1;
		while (row < tree.length) {
			int col2 = col;
			while (col2 < tree[0].length) {
				tree[row][col2] += delta;
				col2 += (col2 & -col2);
			}
			row += (row & -row);
        }
	}
	
	public int getSum(int row, int col) {
        int sum = 0;
        row += 1; col += 1;
        while (row > 0) {
        	int col2 = col;
        	while (col2 > 0) {
        		sum += tree[row][col2];
        		col2 -= (col2 & -col2);
        	}
        	row -= (row & -row);
        }
        return sum;
	}
}
