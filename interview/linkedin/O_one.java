package interview.linkedin;
import java.util.*;

public class O_one<T> {
	Map<Integer, T> indexes = new HashMap<>();
	Map<T, Set<Integer>> words = new HashMap<>();
	int size = 0;
	
	public void add(T word) {
		indexes.put(size, word);
		
		Set<Integer> set = words.get(word);
		if (set == null) {
			set = new HashSet<Integer>();
		}
		set.add(size);
		words.put(word, set);
		
		size++;
	}
	
	public boolean remove(T word) {
		Set<Integer> set = words.get(word);
		if (set == null || set.size() == 0) return false;
		
		int index = set.iterator().next();
		set.remove(index);
		words.put(word, set);
		
		indexes.remove(index);
		if (index != size - 1) {
			T str = indexes.get(size - 1);
			indexes.remove(size - 1);
			indexes.put(index, str);
		}
		
		size--;
		return true;
	}
	
	public T randomRemove() {
		if (size <= 0) return null;
		
		Random rand = new Random();
		int index = rand.nextInt(size);
		T word = indexes.get(index);
		
		indexes.remove(index);
		if (index != size - 1) {
			T str = indexes.get(size - 1);
			indexes.remove(size - 1);
			indexes.put(index, str);
		}
		
		Set<Integer> set = words.get(word);
		set.remove(index);
		words.put(word, set);
		
		size--;
		return word;
	}
	
	public static void main(String[] args) {
		O_one<String> o = new O_one<>();
		o.add("cat");
		o.add("dog");
		o.add("cat");
		o.add("bird");
		o.add("fox");
		System.out.println(o.remove("cat"));
		System.out.println(o.randomRemove());
		System.out.println(o.randomRemove());
		System.out.println(o.randomRemove());
		System.out.println(o.randomRemove());
		System.out.println(o.randomRemove());
		System.out.println(o.randomRemove());
	}

}
