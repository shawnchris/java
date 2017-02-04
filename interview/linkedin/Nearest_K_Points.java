package interview.linkedin;
import java.util.*;

public class Nearest_K_Points {
	class Point {
		int x, y;
		public Point(int x, int y) {this.x = x; this.y = y;}
	}
	
	public List<Point> findKNearest(List<Point> list, Point center, int k) {
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				int dist1 = (p1.x - center.x) * (p1.x - center.x) + (p1.y - center.y) * (p1.y - center.y);
				int dist2 = (p2.x - center.x) * (p2.x - center.x) + (p2.y - center.y) * (p2.y - center.y);
				return dist2 - dist1;
			}
		});
		
		for (int i = 0; i < k; i++) {
			pq.add(list.get(i));
		}
		
		for (int i = k; i < list.size(); i++) {
			Point p1 = list.get(i);
			Point p2 = pq.peek();
			int dist1 = (p1.x - center.x) * (p1.x - center.x) + (p1.y - center.y) * (p1.y - center.y);
			int dist2 = (p2.x - center.x) * (p2.x - center.x) + (p2.y - center.y) * (p2.y - center.y);
			if (dist1 < dist2) {
				pq.poll();
				pq.add(p1);
			}
		}
		
		return new ArrayList<Point>(pq);
	}
	
	public static void main(String[] args) {
		Nearest_K_Points nkp = new Nearest_K_Points();
		List<Point> list = new ArrayList<Point>();
		for (int i = 1; i < 10; i++) {
			list.add(nkp.new Point(i, i));
		}
		List<Point> result = nkp.findKNearest(list, nkp.new Point(0, 0), 5);
		for (int i = 0; i < 5; i++) {
			System.out.println(result.get(i).x + ", " + result.get(i).y);
		}
	}

}
