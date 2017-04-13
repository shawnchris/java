package ita.challenge2017;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Solutions {
	static int minimumMoves(int[] a, int[] m) {
		int result = 0, n = a.length;
		for (int i = 0; i < n; i++) {
			result += convert(a[i], m[i]);
		}
		return result;
	}

	private static int convert(int a, int b) {
		int sum = 0;
		while (a > 0) {
			int aa = a % 10;
			int bb = b % 10;
			sum += aa > bb ? aa - bb : bb - aa;
			a /= 10;
			b /= 10;
		}
		return sum;
	}

	abstract class Animal {
		protected boolean isMammal;
		protected boolean isCarnivorous;

		public Animal(boolean isMammal, boolean isCarnivorous) {
			this.isMammal = isMammal;
			this.isCarnivorous = isCarnivorous;
		}

		public boolean getIsMammal() {
			return this.isMammal;
		}

		public boolean getIsCarnivorous() {
			return this.isCarnivorous;
		}

		abstract public String getGreeting();

		public void printAnimal(String name) {
			System.out.println(
					"A " + name + " says '" + this.getGreeting() + "', is " + (this.getIsCarnivorous() ? "" : "not ")
							+ "carnivorous, and is " + (this.getIsMammal() ? "" : "not ") + "a mammal.");
		}
	}

	/**
	 * Dog class
	 **/
	class Dog extends Animal {
		public Dog() {
			super(true, true);
		}

		public String getGreeting() {
			return "ruff";
		}
	}

	/**
	 * Cow class
	 **/
	class Cow extends Animal {
		public Cow() {
			super(true, false);
		}

		public String getGreeting() {
			return "moo";
		}
	}

	/**
	 * Duck class
	 **/
	class Duck extends Animal {
		public Duck() {
			super(false, false);
		}

		public String getGreeting() {
			return "quack";
		}
	}

	public static JSONObject readJsonFromUrl(String url) {
		JSONObject json = null;
		try {
			InputStream is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			json = (JSONObject) new JSONParser().parse(sb.toString());
		} catch (Exception e) {
			// read failed, do nothing
		}
		return json;
	}
	
	static String[] getMovieTitles(String substr) {
		String url = "https://jsonmock.hackerrank.com/api/movies/search/?Title="
				+ substr + "&page=";
		long page = 1, total = 1;
		List<String> res = new ArrayList<>();
		
		while (page <= total) {
			JSONObject json = readJsonFromUrl(url + page);
			total = (Long) json.get("total_pages");
			JSONArray data = (JSONArray) json.get("data");
			for (Object movie : data) {
				String title = (String)((JSONObject) movie).get("Title");
				res.add(title);
			}
			page++;
		}
		
		Collections.sort(res);
		
		String[] result = new String[res.size()];
		for (int i = 0; i < res.size(); i++) {
			result[i] = res.get(i);
		}
		
		return result;
	}
	
	static long[] bitwiseEquations(long[] a, long[] b) {
		int n = a.length;
		long[] result = new long[n];
		
		for (int i = 0; i < n; i++) {
			for (long x = 0; x <= a[i] / 2; x++) {
				long y = a[i] - x;
				if ((x ^ y) == b[i]) {
					result[i] = 2 * x + 3 * y;
					break;
				}
			}
		}
		
		return result;
    }
	
	static int readingArticles(int[] intellectual, int[] pages, int p) {
		int[] dp = new int[p + 1];
        int n = pages.length;
        
        for (int i = 0; i < n; i++){
            for (int j = p; j >= pages[i] * 2; j--){
                if (dp[j] < dp[j - pages[i] * 2] + intellectual[i])
                	dp[j] = dp[j - pages[i] * 2] + intellectual[i];
            }
        }
        
        return dp[p];
    }
	
	static int getGCD(int a, int b) {
		if (a == 0 || b == 0) return a + b; // base case
		return getGCD(b, a % b);
	}
	
	static boolean hasRoute(Set[] graph, int start, int end) {
		if (start == end) return true;
		
		//BFS
		Queue<Integer> toVisit = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		toVisit.add(start);
		visited.add(start);
		while (!toVisit.isEmpty()) {
			int cur = toVisit.poll();
			if (cur == end) return true;
			for (Object o : graph[cur]) {
				Integer next = (Integer) o;
				if (!visited.contains(next)) {
					toVisit.add(next);
					visited.add(next);
				}
			}
		}
		
		return false;
	}
	
	static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
		// Build graph
		Set[] graph = new Set[n + 1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new HashSet<Integer>();
		}
		for (int i = 1; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (getGCD(i, j) > g) {
					graph[i].add(j);
					graph[j].add(i);
				}
			}
		}
		
		// Search routes
		int m = originCities.length;
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			if (hasRoute(graph, originCities[i], destinationCities[i])) {
				result[i] = 1;
			}
		}
		
		return result;
    }

	public static void main(String[] args) {
		/*
		String[] res;
		res = getMovieTitles("spiderman");
		for (String r : res) {
			System.out.println(r);
		}
		*/
		
		/*
		System.out.println(readingArticles(new int[] {3,2,2}, new int[] {3,2,2}, 9));
		System.out.println(readingArticles(new int[] {1,4,6,3}, new int[] {1,2,2,3}, 8));
		System.out.println(readingArticles(new int[] {1,4,2,5,3}, new int[] {2,6,4,7,1}, 13));
		*/
		
		/*
		int[] res = connectedCities(6, 0, new int[] {1,4,3,6}, new int[] {3,6,2,5});
		for (int r : res) {
			System.out.println(r);
		}
		res = connectedCities(6, 1, new int[] {1,2,4,6}, new int[] {3,3,3,4});
		for (int r : res) {
			System.out.println(r);
		}
		*/
		
		long[] res = bitwiseEquations(new long[] {4,3,57}, new long[] {2,4,49});
		for (long r : res) {
			System.out.println(r);
		}
		
	}

}
