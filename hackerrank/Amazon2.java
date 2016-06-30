package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Pair {
	int y;
	int x;
	Pair (int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class Amazon2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = Integer.parseInt(in.nextLine());
		HashMap<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();
		boolean error = false;
		for (int i = 0; i < n; i++) {
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
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(split[j]);
				List<Pair> list = map.get(num);
				if (list == null)
					list = new ArrayList<Pair>();
				list.add(new Pair(i, j));
				map.put(num, list);
			}
		}
		int distance = in.nextInt();
		boolean found = false;
		if (!error) {
			for (Integer key : map.keySet()) {
				List<Pair> list = map.get(key);
				if (list.size() < 2)
					continue;
				else {
					for (int i = 0; i < list.size() - 1 && !found; i++) {
						for (int j = i + 1; j < list.size(); j++) {
							if (Math.abs(list.get(i).y - list.get(j).y) + Math.abs(list.get(i).x - list.get(j).x) <= distance) {
								found = true;
								break;
							}
						}
					}
				}
			}
		}
		
		if (found)
			System.out.println("YES");
		else
			System.out.println("NO");
		
		in.close();
	}

}

/*
4
1 2 3 4
5 6 7 8
9 10 11 12
13 14 15 16
3

3
1 2 3
4 5 6
5 8 9
2


 */
