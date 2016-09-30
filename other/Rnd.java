package other;
import java.util.*;
public class Rnd {

	public static void main(String[] args) {
		int max = 403;
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			System.out.println(rand.nextInt(max + 1));
		}

	}

}
