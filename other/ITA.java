package other;
import java.util.*;

public class ITA {
	class Comparator {
	    public Comparator() {
	        
	    }
	    public boolean compare(String s1, String s2) {
	        return s1.equals(s2);
	    }
	    public boolean compare(int i1, int i2) {
	        return i1 == i2;
	    }
	    public boolean compare(int[] a, int[] b) {
	        if (a == null && b == null) return true;
	        if (a == null || b == null) return false;
	        if (a.length != b.length) return false;
	        for (int i = 0; i < a.length; i++) {
	            if (a[i] != b[i]) return false;
	        }
	        return true;
	    }
	}
	
    static int playlist(String[] songs, int k, String q) {
        if (songs[k] == q) return 0;
        int res1 = 0, res2 = 0, idx = k;
        while(true) {
            idx++;
            res1++;
            if (idx == songs.length) {
                idx = 0;
            }
            if(songs[idx].equals(q)) {
                break;
            }
        }
        idx = k;
        while(true) {
            idx--;
            res2++;
            if (idx < 0) {
                idx = songs.length - 1;
            }
            if(songs[idx].equals(q)) {
                break;
            }
        }
        return res1 <= res2 ? res1 : res2;
    }
    
    static String mergeStrings(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        int len = lenA <= lenB ? lenA : lenB;
        
        for(int i = 0; i < len; i++) {
            sb.append(a.charAt(i));
            sb.append(b.charAt(i));
        }
        
        if (lenA < lenB) {
            sb.append(b.substring(len));
        }
        else if (lenA > lenB) {
            sb.append(a.substring(len));
        }
        
        return sb.toString();

    }
    
    static long calculateAmount(int[] prices) {
        int cheapest = prices[0];
        long total = prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= cheapest) {
                cheapest = prices[i];
            }
            else {
                total += prices[i] - cheapest;
            }
        }
        
        return total;

    }
    
    static void commonSubstring(String[] a, String[] b) {
        for (int i = 0; i < a.length; i++) {
            //each bit for a char
            int[] bit = new int[26];
            boolean found = false;
            for (int j = 0; j < a[i].length(); j++) {
                bit[a[i].charAt(j) - 'a'] = 1;
            }
            for (int j = 0; j < b[i].length(); j++) {
                if (bit[b[i].charAt(j) - 'a'] == 1) {
                    found = true;
                    continue;
                }
            }
            if (found) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }

    }
    
    static int ironMan(int[] p) {
    	int life = 1;
    	for (int i = p.length - 1; i >= 0; i--) {
    		//Dynamic programming problem
    		//let life[i] stand for minimum life of IronMan before enter room i.
    		//life[i] >= 1 and life[i] >= life[i+1] - p[i]
    		life = Math.max(1, life - p[i]);
    	}
    	return life;
    }
    
    static int collect_max(int[][] mat) {
    	if (mat == null) return 0;
    	int m = mat.length;
    	if (m == 0) return 0;
    	int n = mat[0].length;
    	if (n == 0) return 0;
    	
    	int result = 0;
    	
    	int[][] value = new int[m][n];
    	char[][] direction = new char[m][n];
    	
    	//First round, traverse from top left to bottom right
    	//Init
    	value[0][0] = mat[0][0];
    	for (int col = 1; col < n; col++) {
    		if (mat[0][col] == -1 || mat[0][col - 1] == -1) {
    			value[0][col] = -1;
    		}
    		else {
    			value[0][col] = value[0][col - 1] + mat[0][col];
    			direction[0][col] = 'L';
    		}
    	}
    	for (int row = 1; row < m; row++) {
    		if (mat[row][0] == -1 || mat[row - 1][0] == -1) {
    			value[row][0] = -1;
    		}
    		else {
    			value[row][0] = value[row - 1][0] + mat[row][0];
    			direction[row][0] = 'U';
    		}
    	}
    	//Traverse
    	for (int row = 1; row < m; row++) {
    		for (int col = 1; col < n; col++) {
    			if (mat[row - 1][col] == -1 && mat[row][col - 1] == -1
    					|| mat[row][col] ==  -1) {
    				value[row][col] = -1;
    			}
    			else {
    				value[row][col] = Math.max(value[row - 1][col], value[row][col - 1]) + mat[row][col];
    				direction[row][col] = value[row - 1][col] > value[row][col - 1] ? 'U' : 'L';
    			}
    		}
    	}
    	//Check result
    	result = value[m - 1][n - 1];
    	if (result == -1) return 0;
    	//Remove collected diamonds
    	int i = m - 1, j = n - 1;
    	mat[i][j] = 0;
    	while (i > 0 || j > 0) {
    		if (direction[i][j] == 'U') {
    			i--;
    		}
    		else {
    			j--;
    		}
    		mat[i][j] = 0;
    	}
    	
    	//Second round, traverse from bottom right to top left
    	//Init
    	value = new int[m][n];
    	for (int col = n - 2; col >= 0; col--) {
    		if (mat[m - 1][col] == -1 || mat[m - 1][col + 1] == -1) {
    			value[m - 1][col] = -1;
    		}
    		else {
    			value[m - 1][col] = value[m - 1][col + 1] + mat[m - 1][col];
    		}
    	}
    	for (int row = m - 2; row >= 0; row--) {
    		if (mat[row][n - 1] == -1 || mat[row + 1][n - 1] == -1) {
    			value[row][n - 1] = -1;
    		}
    		else {
    			value[row][n - 1] = value[row + 1][n - 1] + mat[row][n - 1];
    		}
    	}
    	//Traverse
    	for (int row = m - 2; row >= 0; row--) {
    		for (int col = n - 2; col >= 0; col--) {
    			if (mat[row + 1][col] == -1 && mat[row][col + 1] == -1
    					|| mat[row][col] ==  -1) {
    				value[row][col] = -1;
    			}
    			else {
    				value[row][col] = Math.max(value[row + 1][col], value[row][col + 1]) + mat[row][col];
    			}
    		}
    	}
    	
    	return value[0][0] + result;
    }

	
	public static void main(String[] args) {
		String[] songs = {"wheniseeyouagain","borntorun","nothingelsematters","cecelia"};
		System.out.println(playlist(songs, 1, "cecelia"));
		
		System.out.println(ironMan(new int[] {-5,4,-2,3,1,-1,-6,-1,0,5}));
		System.out.println(ironMan(new int[] {-5,4,-2,3,1}));
		System.out.println(ironMan(new int[] {-5,4,-2,3,1,-1,-6,-1,0,-5}));
		
		int[][] mat1 = {
				{0,1,-1},
				{1,0,-1},
				{1,1,1}
		};
		System.out.println(collect_max(mat1));
		int[][] mat2 = {
				{0,1,1},
				{1,0,1},
				{1,1,1}
		};
		System.out.println(collect_max(mat2));
		int[][] mat3 = {
				{0,1,1},
				{1,0,-1},
				{1,1,-1}
		};
		System.out.println(collect_max(mat3));

	}
	
	

}
