package depaul.csc421;

public class LCS {

	public void LCS_length (String x, String y, int m, int n, int[][] c, char[][] b) {
		for (int i=1; i<=m; i++) {
			for (int j=1; j<=n; j++) {
				if (x.charAt(i-1) == y.charAt(j-1)) {
					c[i][j] = c[i-1][j-1]+1;
					b[i][j] = 'D';
				}
				else if (c[i-1][j] >= c[i][j-1]) {
					c[i][j] = c[i-1][j];
					b[i][j] = 'U';
				}
				else {
					c[i][j] = c[i][j-1];
					b[i][j] = 'L';
				}
			}
		}
	}
	
	public void print_LCS (char[][] b, String x, int i, int j) {
		if (i==0 || j==0)
			return;
		if (b[i][j] == 'D') {
			print_LCS(b, x, i-1, j-1);
			System.out.print(x.charAt(i-1)+" ");
		}
		else if (b[i][j] == 'U') {
			print_LCS(b, x, i-1, j);
		}
		else {
			print_LCS(b, x, i, j-1);
		}
	}
	
	public static void main(String[] args) {
		//String x = "10010101";
		//String y = "010110110";
		String x = "NEDCATELYNROBBSANSAARYABRANRICKONJON";
		String y = "TYWINJAIMECERSEITYRIONJOFFREYTOMMENMYRCELLA";
		x = "NECERSYRIONJON";
		y = "AEMONAERYSDAENERYSVISERYSRHAEGAR";
		int m = x.length();
		int n = y.length();
		int[][] c = new int[m+1][n+1];
		char[][] b = new char[m+1][n+1];
		for (int i=1; i<=m; i++)
			c[i][0] = 0;
		for (int j=1; j<=n; j++)
			c[0][j] = 0;
		
		LCS lcs = new LCS();
		lcs.LCS_length(x, y, m, n, c, b);
		System.out.print("The LCS is ");
		lcs.print_LCS(b, x, m, n);

	}

}
