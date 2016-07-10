package interview.tintri;
import java.util.*;

public class Solution {
	
/* Phone interview 1
	class Node {
		public int value;
		public Node next[2];
	};

	2 -> 7 -> 10
	2 -> 3 -> 7 -> 10
	1
	15

	Node orderedInsert( Node toInsert, Node list ) {
		Node dummyHead = new Node (Integer.MIN_VALUE);
		Node prev = dummyHead;
		dummyHead.next = list;
		boolean inserted = false;
		while (prev.next != null) {
			if (prev.next.value > toInsert.value) {
				Node temp = prev.next;
				prev.next = toInsert;
				toInsert.next = temp;
				inserted = true;
				break;
			}
			prev = prev.next;
		}
		if (!inserted) {
			prev.next = toInsert;
			toInsert.next = null;
		}
		return dummyHead.next;
	}

	class MultiList {
		public Node list1;
		public Node list2;
	};

	void insertElement( Node elem, boolean insertIntoList2, MultiList ml ) {
		Node dummyHead1 = new Node (Integer.MIN_VALUE);
		Node prev1 = dummyHead1;
		dummyHead1.next = ml.list1;
		boolean inserted1 = false;
		while (prev1.next != null) {
			if (prev1.next.value > elem.value) {
				Node temp = prev1.next;
				prev1.next = elem;
				elem.next[0] = temp;
				inserted = true;
				break;
			}
			prev1 = prev1.next;
		}
		if (!inserted1) {
			prev1.next = elem;
			elem.next[0] = null;
		}

		if (insertIntoList2) {
			Node dummyHead2 = new Node (Integer.MIN_VALUE);
			Node prev2 = dummyHead2;
			dummyHead2.next = ml.list2;
			boolean inserted2 = false;
			while (prev2.next != null) {
				if (prev2.next.value > elem.value) {
					Node temp = prev2.next;
					prev2.next = elem;
					elem.next[1] = temp;
					inserted2 = true;
					break;
				}
			prev2 = prev1.next;
			}
			if (!inserted1) {
				prev2.next = elem;
				elem.next[1] = null;
			}
			ml.list2 = dummyHead2.next;
		}

		ml.list1 = dummyHead1.next;
		
	}


	
	void insert( Node elem, MultiList list ) {
		int num = random(10);
		if ( num == 0 ) {
			insertElement( elem, true, list );
	} else {
		insertElement( elem, false, list );
	}
	}

	Node lookup( MultiList list, int value ) {
		Node prev = list.list2;
		Node tail = null;
		boolean found = false;
		while (prev.next[1] != null) {
			if (prev.next[1].value >= value) {
				tail = prev.next[1];
				break;
			}
			prev = prev.next[1];
		}
		
		if (tail != null && tail.value == value)
			return tail;
		while (prev != tail) {
			if (prev.value == value) {
				return prev;
			}
			prev = prev.next[0];
		}
		return null;
		
	}
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set[] set = new Set[20];
	}

}
