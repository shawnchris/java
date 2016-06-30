package hackerrank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Amazon1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		boolean error = false;
		int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) { // for each row
            if (!in.hasNextLine()) { // rows < n
            	error = true;
            	break;
            }
            String str = in.nextLine().trim();
            String[] split = str.split("\\s+");
            if (split.length != n) { // cols < n
            	error = true;
            	break;
            }
            for (int j = 0; j < n; j++) { // copy into array
            	a[i][j] = Integer.parseInt(split[j]);
            }
        }
        /*
        
        */
        if (!error && n > 0) {
        	if (n == 1)
        		System.out.println(a[0][0]);
        	else {
        		int circles = n / 2;
        		for (int c = 0; c < circles; c++) {
        			int limit1 = c;
        			int limit2 = n - c - 1;
        			int x = c, y = c, d = 1;
        			int previous = a[y][x];
        	        for (int i = 0; i < (n - 2*c -1) * 4; i++) {
        	            if (d == 1) { // going right
        	                x++;
        	                if (x > limit2) {
        	                    x--;
        	                    d++;
        	                    y++;
        	                }
        	            }
        	            else if (d == 2) { // going down
        	                y++;
        	                if (y > limit2) {
        	                    y--;
        	                    d++;
        	                    x--;
        	                }
        	            }
        	            else if (d == 3) { // going left
        	                x--;
        	                if (x < limit1) {
        	                    x++;
        	                    d++;
        	                    y--;
        	                }
        	            }
        	            else { // going up
        	                y--;
        	                if (y < limit1) {
        	                    y++;
        	                    d = 1;
        	                    x++;
        	                }
        	            }
        	            int temp = a[y][x];
        	            a[y][x] = previous;
        	            previous = temp;
        	        }
        		}
        		
        		for (int i = 0; i < n; i++) {
                	for (int j = 0; j < n; j++) {
                		System.out.print(a[i][j] + " ");
                    }
                	System.out.println();
                }
        	}
        }
        else {
        	System.out.println("ERROR");
        }
        in.close();
    }
}