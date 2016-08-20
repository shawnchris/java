package cc189;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class LockObject {
	ReentrantLock lock1 = new ReentrantLock();
	ReentrantLock lock2 = new ReentrantLock();
	
	public boolean lock1then2(String thread) {
		boolean result = false;
		try {
			System.out.println(thread + ": Trying to obtain lock1...");
			lock1.lock();
			System.out.println(thread + ": Obtained lock1!");
			Thread.sleep(1000);
			System.out.println(thread + ": Trying to obtain lock2...");
			if (lock2.tryLock((new Random()).nextInt(500), TimeUnit.MILLISECONDS)) {
				System.out.println(thread + ": Obtained lock2!");
				System.out.println(thread + ": My job is done!");
				result = true;
			}
			else {
				System.out.println(thread + ": I give up...");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (lock1.isHeldByCurrentThread())
				lock1.unlock();
			if (lock2.isHeldByCurrentThread())
				lock2.unlock();
		}
		return result;
	}
	
	public boolean lock2then1(String thread) {
		boolean result = false;
		try {
			System.out.println(thread + ": Trying to obtain lock2...");
			lock2.lock();
			System.out.println(thread + ": Obtained lock2!");
			Thread.sleep(1000);
			System.out.println(thread + ": Trying to obtain lock1...");
			if (lock1.tryLock((new Random()).nextInt(500), TimeUnit.MILLISECONDS)) {
				System.out.println(thread + ": Obtained lock1!");
				System.out.println(thread + ": My job is done!");
				result = true;
			}
			else {
				System.out.println(thread + ": I give up...");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (lock1.isHeldByCurrentThread())
				lock1.unlock();
			if (lock2.isHeldByCurrentThread())
				lock2.unlock();
		}
		return result;
	}

}

class Worker implements Runnable {
	LockObject lo;
	boolean seq;
	public Worker (LockObject lo, boolean seq) {
		this.lo = lo;
		this.seq = seq;
	}
	@Override
	public void run() {
		if (seq)
			while (!lo.lock1then2(Thread.currentThread().getName()));
		else
			while (!lo.lock2then1(Thread.currentThread().getName()));
	}
}

public class C15Q00 {

	public static void main(String[] args) {
		LockObject lo = new LockObject();
		Thread t1 = new Thread(new Worker(lo, true));
		Thread t2 = new Thread(new Worker(lo, false));
		t1.start();
		t2.start();
	}

}
