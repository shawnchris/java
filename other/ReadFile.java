package other;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class ReadFile {
	public static Point[] readFromFile(final String fileName) {
		List<Point> temp = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] sp = line.split(",");
		    	if (sp.length < 3) continue;
		    	temp.add(new Point(
		    			Integer.parseInt(sp[1].trim()), 
		    			Integer.parseInt(sp[2].trim())));
		    }
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Point[] result = new Point[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			result[i] = temp.get(i);
		}
		
		return result;
	}
	public static void main(String[] args) {
		Point[] r = readFromFile("/Users/sgao/test.txt");
		for (int i = 0; i < r.length; i++) {
			System.out.println(r[i].x + " " + r[i].y);
		}
	}

}
