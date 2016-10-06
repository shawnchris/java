package cc189;

class FizzBuzzThread2 extends Thread{
	private static Object lock = new Object();
	protected static int current = 1;
	private int max;
	private boolean div3, div5;
	private String toPrint;
	
	public FizzBuzzThread2(boolean div3, boolean div5, int max, String toPrint) {
		this.div3 = div3;
		this.div5 = div5;
		this.max = max;
		this.toPrint = toPrint;
	}
	
	public void run() {
		while(true) {
			synchronized(lock) {
				if (current > max)
					return;
				
				if ((current % 3 == 0) == div3 && (current % 5 == 0) == div5) {
					if (toPrint.length() > 0)
						System.out.print("\n" + toPrint + "\n");
					else
						System.out.print("\n" + current + "\n");
					current++;
					lock.notifyAll();
				}
				else {
					try {
						//System.out.print("Not my turn...");
						lock.wait();
					}
					catch (InterruptedException e) {
						
					}
				}
			}
		}
	}
}
public class C15Q07_FizzBuzz {
	public static void fizzbuzz(int n) {
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				System.out.println("FizzBuzz");
			} else if (i % 3 == 0) {
				System.out.println("Fizz");
			} else if (i % 5 == 0) {
				System.out.println("Buzz");
			} else {
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		//fizzbuzz(100);
		int n = 100;
		Thread[] threads = new Thread[] {
				new FizzBuzzThread2(true, true, n, "FizzBuzz"),
				new FizzBuzzThread2(true, false, n, "Fizz"),
				new FizzBuzzThread2(false, true, n, "Buzz"),
				new FizzBuzzThread2(false, false, n, "")
		};
		
		for (Thread t :  threads) {
			t.start();
		}
	}
}
