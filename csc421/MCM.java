package csc421;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Matrix-chain multiplication
 * @author Shan Gao
 *
 */
public class MCM {
	
	private void Matrix_Chain_Order(int[] p) {
		int n = p.length - 1;
		int[][] m = new int[n][n];
		int[][] s = new int[n][n];
		
		for (int i=0; i<n; i++)
			m[i][i] = 0;
		
		for (int l=2; l<=n; l++) {
			for (int i=1; i<=n-l+1; i++) {
				int j = i + l -1;
				m[i-1][j-1] = Integer.MAX_VALUE;
				for (int k=i; k<=j-1; k++) {
					int q = m[i-1][k-1] 
							+ m[k+1-1][j-1] 
									+ p[i-1]*p[k]*p[j];
					if (q < m[i-1][j-1]) {
						m[i-1][j-1] = q;
						s[i-1][j-1] = k;
					}
				}
			}
		}
		System.out.println("The optimal number of multiplications is " + m[0][n-1]);
		System.out.print("The optimal parenthes is ");
		Print_Optimal_Parenthes(s, 1, n);
	}
	
	private void Print_Optimal_Parenthes(int[][] s, int i, int j) {
		if (i==j)
			System.out.print("A"+i);
		else {
			System.out.print("(");
			Print_Optimal_Parenthes(s, i, s[i-1][j-1]);
			Print_Optimal_Parenthes(s, s[i-1][j-1]+1, j);
			System.out.print(")");
		}
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer> ap = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("MCMinput"));
			boolean firstline = true;
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				if (firstline) {
					ap.add(Integer.parseInt(split[0]));
					firstline = false;
				}
				ap.add(Integer.parseInt(split[1]));
			};
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.print("The sequence of dimensions is < ");
		int n = ap.size();
		int[] p = new int[n];
		for (int i=0; i<n; i++) {
			p[i] = ap.get(i);
			System.out.print(p[i]+" ");
		}
		System.out.println(">");
		
		MCM mcm = new MCM();
		mcm.Matrix_Chain_Order(p);
	}

}
