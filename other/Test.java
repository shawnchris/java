package other;

import java.util.*;
class Plant {
	
}
class Tree extends Plant {
	
}
enum ItemType {
	COMPANY_WIDE("Company"),
	DEPARTMENTAL("Departmental"),
	PROJECT_SPECIFIC("Project");
	
	private String itemCode;
	
	private ItemType(String dbCode) {
		this.itemCode = dbCode;
	}
	
	public String getItemCode() {
		return this.itemCode;
	}
}
public class Test {
	interface Auto {
		public boolean drive();
	}
	class Car implements Auto {
		public boolean drive() {
			// write your code of how to drive a car
			return false;
		}
	}
	class Truck implements Auto {
		public boolean drive() {
			// write your code of how to drive a truck
			return false;
		}
	}

	class Test1 {
		public boolean test(Auto a) {
			return a.drive();
		}
	}


	public static void main(String[] args) {
		/*Integer i1 = 1;
		Integer i2 = 2;
		System.out.println(i1.compareTo(i2));*/
		for (int i = 0; i < 10; i++) {
			System.out.println("i = " + i + " : i & -i = " + (i & -i));
		}
		List<Integer> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(9);
		list1.add(8);
		
		System.out.println(list1);
		
		List<Integer> list2 = list1.subList(1, 2);
		list2.clear();
		System.out.println(list1);
		
		list2.add(7);
		System.out.println(list1);
		
		Test tt = new Test();
		Car car = tt.new Car();
		Truck truck = tt.new Truck();
		Test1 t = tt.new Test1();
		t.test(car);
		t.test(truck);
		
		List<Tree> trees = new ArrayList<Tree>();
		trees.add(new Tree());
		List<? extends Plant> plants = trees;
		
		ItemType type2 = ItemType.valueOf("PROJECT_SPECIFIC");
		
		ItemType type4 = ItemType.values()[0];
		
		ItemType type1 = ItemType.DEPARTMENTAL;
		
		System.out.println(type2);
		System.out.println(type4);
		System.out.println(type1);
	}
	
}
