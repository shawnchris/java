package hackerrank;

import java.util.*;

public class DiagonalDifference {
	public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int sum1 = 0, sum2 = 0, d1 = 0, d2 = d1 + size - 1;
        for (int i = 0; i < size * size; i++) {
            int num = in.nextInt();
            if (i == d1) {
                sum1 += num;
                d1 = d1 + size + 1;
            }
            if (i == d2 && i != size * size - 1) {
                sum2 += num;
                d2 = d2 + size - 1;
            }
        }
        System.out.println(Math.abs(sum1 - sum2));
        in.close();
    }

}
/*
3
11 2 4
4 5 6
10 8 -12

15
*/