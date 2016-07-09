package com.glassdoor;

public class JObject {

	public static void main(String[] args) {
		Integer i1 = 1;
		Integer i2 = 1;
		Double d1 = 3.14;
		Double d2 = 3.14;
		String s1 = "abc";
		String s2 = "abc";
		int[] a1 = new int[]{1,2,3};
		int[] a2 = new int[]{1,2,3};
		
		System.out.println(i1.hashCode());
		System.out.println(i2.hashCode());
		System.out.println(i1.equals(i2));
		
		System.out.println(d1.hashCode());
		System.out.println(d2.hashCode());
		System.out.println(d1.equals(d2));
		
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1.equals(s2));
		
		System.out.println(a1.hashCode());
		System.out.println(a2.hashCode());
		System.out.println(a1.equals(a2));
		
	}

}
