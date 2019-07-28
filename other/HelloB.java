package other;

public class HelloB extends HelloA {
	public HelloB() {
	}

	{
		System.out.println("I'm B class");
	}
	static {
		System.out.println("static B");
	}

	public static void main(String[] args) {
		new HelloB();
		int i = 0;
		i = i++;
		System.out.println(i);
	}
}

class HelloA {
	public HelloA() {
	}

	{
		System.out.println("I'm A class");
	}
	static {
		System.out.println("static A");
	}
}
