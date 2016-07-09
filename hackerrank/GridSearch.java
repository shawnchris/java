package hackerrank;

import java.util.*;

public class GridSearch {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) { // for each test case
            int R = in.nextInt();
            int C = in.nextInt();
            in.nextLine();
            String[] G = new String[R];
            for (int j = 0; j < R; j++)
                G[j] = in.nextLine();
            int r = in.nextInt();
            int c = in.nextInt();
            in.nextLine();
            String[] P = new String[r];
            for (int j = 0; j < r; j++)
                P[j] = in.nextLine();
            
            boolean found = false;
            for (int row = 0; row < R-r+1 && !found; row++) {
                for (int col = 0; col < C-c+1; col++) {
                    int k = 0;
                    for (; k < r; k++) {
                        if (!G[row+k].substring(col, col+c).equals(P[k]))
                            break;
                    }
                    if (k == r) {
                        found = true;
                        break;
                    }
                }
            }
            
            if (found)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
        in.close();
    }
}
/*
2
10 10
7283455864
6731158619
8988242643
3830589324
2229505813
5633845374
6473530293
7053106601
0834282956
4607924137
3 4
9505
3845
3530
15 15
400453592126560
114213133098692
474386082879648
522356951189169
887109450487496
252802633388782
502771484966748
075975207693780
511799789562806
404007454272504
549043809916080
962410809534811
445893523733475
768705303214174
650629270887160
2 2
99
99
*/
