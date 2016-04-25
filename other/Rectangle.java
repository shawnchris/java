package other;

import java.util.Scanner;

public class Rectangle {
	public static boolean overlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        int left = Math.max(x1, x2);
        int right = Math.max(Math.min(x1 + w1, x2 + w2), left);
        int bottom = Math.max(y1, y2), top = Math.max(Math.min(y1 + h1, y2 + h2), bottom);
        return (right >=left) && (top >= bottom);
    }
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()) {
			String[] para = in.nextLine().split("\\s+");
			int x1 = Integer.parseInt(para[0]);
			int y1 = Integer.parseInt(para[1]);
			int w1 = Integer.parseInt(para[2]);
			int h1 = Integer.parseInt(para[3]);
			int x2 = Integer.parseInt(para[4]);
			int y2 = Integer.parseInt(para[5]);
			int w2 = Integer.parseInt(para[6]);
			int h2 = Integer.parseInt(para[7]);
			System.out.println(overlap(x1, y1, w1, h1, x2, y2, w2, h2));
		}
		in.close();
	}
}
