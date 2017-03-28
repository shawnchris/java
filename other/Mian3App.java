package other;

import java.util.*;

//请设计个扫雷游戏的地雷分布图，给一个h,w和m. 生成一个高度h，宽度w，总共m颗雷的矩阵。要求m颗雷随机分布。


//看了有个水塘抽样的例子，然后觉得挺像的，就写了一下不知道对不对，也不知道怎么验证这个雷摆的是否随机
/*
My idea is 
generate the total size by h * w;
construct a list to store the position for bomb.
the question can be transformed to get k samples from 0(inclusive) to size(exclusive).
My design:
iterate from 0(inclusive) to size(exclusive)(index is i) -> get the probability of choosing index i as one of the position to place the bomb. 
	how to choose:(detail)
	for the index i, 
		if the index is smaller than count, add to the list
		else it should have the probability of count / (i + 1) to be as one of the samples.	
finally, we generate the result array, mark the positions recorded in list as 1(bomb) and mark other elements as 0.



time complexity is O(w * h)
space complexity is O(w * h)
*/
public class Mian3App{
	public static void main(String[] args) {
		int[][] b = new MySolution().generateBomb(6,  8, 10);
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				System.out.print(b[i][j] + "  ");
			}
			System.out.println();
		}
	}
}

class MySolution {
	public int[][] generateBomb(int h,int w,int count){
		int size=h*w;
		List<Integer> locations = new ArrayList<>();
		for(int i=0;i<count;i++){
			locations.add(i);
		}
		for(int i=count;i<size;i++){
			int j = (int)(Math.random() * (i + 1)); 
			if(j < count) {
				locations.set(j, i);
			} 
		}
		int[][] res=new int[h][w];
		for(int i=0;i<locations.size();i++){
			int x = (locations.get(i)) / w;
			int y = (locations.get(i)) % w;
			res[x][y]=1; //    x*w + y
		}
		return res;
	}
}

