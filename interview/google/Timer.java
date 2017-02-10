package interview.google;

public class Timer {
	private static long start_time;
	static public void start() {
		start_time = System.currentTimeMillis();
		System.out.println("Timer started at: " + start_time);
	}
	static public void end() {
		long end_time = System.currentTimeMillis();
		System.out.println("Timer stopped at: " + end_time + ", ms elapsed: " + (end_time - start_time));
	}
}
