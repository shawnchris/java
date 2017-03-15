package other;

import java.util.function.Consumer;
import java.util.function.Supplier;

interface IF1 {
	default void print() {
		System.out.println("IF1");
	}
}

class IF1Impl implements IF1 {
	
}

class Invoice {
   public static String formatId(String oldId) {
      return oldId + "_Invoice";
   }
}

class SalesInvoice extends Invoice {
   public static String formatId(String oldId) {
      return oldId + "_SalesInvoice";
   }
}

public class A{
    static int total = 10;
    public void call() {
        int total = 5;
        System.out.println(this.total);
    }
    public static void main (String args []) {
        A a1 = new A();
        a1.call();
        
        Supplier<String> i = () -> "Car";
        Consumer<String> c = x -> System.out.print(x.toLowerCase());
        Consumer<String> d = x -> System.out.print(x.toUpperCase());
        c.andThen(d).accept(i.get());
        System.out.println();
        
	   String first = "first";
	   String second = new String("first");
	   "first".concat("second");
	   System.out.println(first.equals(second));
	   System.out.println(first == second);
	   System.out.println(first.equals("firstsecond"));
	   System.out.println(second == "first");
	   
	   System.out.println("Current JVM Heap Size:" + Runtime.getRuntime().totalMemory());
	   System.out.println("Maximum JVM Heap Size:" + Runtime.getRuntime().maxMemory());
	   System.out.println("Free JVM Heap Size:" + Runtime.getRuntime().freeMemory());
	   
	   Invoice invoice = new SalesInvoice();
	   System.out.println(invoice.formatId("1234"));
	   
	   Invoice invoice1 = new Invoice();
	   System.out.println(invoice1.formatId("1234"));
	   
	   SalesInvoice invoice2 = new SalesInvoice();
	   System.out.println(Invoice.formatId("1234"));
	   
	   SalesInvoice invoice3 = new SalesInvoice();
	   System.out.println(invoice3.formatId("1234"));
    }
}
