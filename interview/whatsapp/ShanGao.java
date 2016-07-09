import java.util.Arrays;
import java.util.Random;

public class ShanGao {

	/* The inner class "ListNode" created for the 1st question:
	 * 1. Create a new datatype for a singly linked list, that holds integers.
	 * Do not re-use any existing linked list datatype from your language.
	 */
	class ListNode {
		int val;
		ListNode next;
		
		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}
	
	/* The private function "merge" created for the 2nd question:
	 * 2. Write a function that merges two sorted linked lists into a single,
	 * sorted linked list. This should be done in-place - the input linked
	 * lists are mutated into the resulting linked list.
	 * 
	 * @param l1, l2 - head nodes of two sorted list to be merged.
	 * @return head node of merged list.
	 */
	ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
    
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
    
        if (l1 != null)
            p.next = l1;
    
        if (l2 != null)
            p.next = l2;
    
        return dummyHead.next;
    }
	
	/* The public function "sortList" created for the 3rd question:
	 * 3. Use the merge function from step 2 to produce a full merge-sort 
	 * algorithm against an arbitrary input linked list. This should also be 
	 * done in-place.
	 * 
	 * @param head - head node of the list to be sorted.
	 * @return head node of sorted list.
	 */
	public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
    
        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;
    
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
    
        prev.next = null;
    
        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
    
        // step 3. merge l1 and l2
        return merge(l1, l2);
    }
	 
	/* Test cases are placed in the "main" function for the 4th question:
	 * 4. Provide some code to exercise the above sorting function, and verify
	 * the results are correct.  Test code that checks the result is better
	 * than just printing out the list. Ideally, the test cases will print
	 * PASS or FAIL.
	 */
	
	public static void main(String[] args) {
		ShanGao sg = new ShanGao();
		ListNode list;
		int[] test1 = new int[] {2,3,5,8,1,4,6,7};
		list = sg.sortList(sg.generateList(test1));
		if (sg.assertequal(test1, list))
			System.out.println("Test case 1: PASS");
		else
			System.out.println("Test case 1: FAIL");
		
		int[] test2 = new int[] {0};
		list = sg.sortList(sg.generateList(test2));
		if (sg.assertequal(test2, list))
			System.out.println("Test case 2: PASS");
		else
			System.out.println("Test case 2: FAIL");
		
		int[] test3 = new int[] {1, 1};
		list = sg.sortList(sg.generateList(test3));
		if (sg.assertequal(test3, list))
			System.out.println("Test case 3: PASS");
		else
			System.out.println("Test case 3: FAIL");
		
		int[] test4 = new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE};
		list = sg.sortList(sg.generateList(test4));
		if (sg.assertequal(test4, list))
			System.out.println("Test case 4: PASS");
		else
			System.out.println("Test case 4: FAIL");
		
		int[] test5 = new int[1000];
		Random rnd = new Random();
		for (int i = 0; i < 1000; i++)
			test5[i] = rnd.nextInt(10000);
		list = sg.sortList(sg.generateList(test5));
		if (sg.assertequal(test5, list))
			System.out.println("Test case 5: PASS");
		else
			System.out.println("Test case 5: FAIL");

	}
	
	/*
	 * Helper function to de-serialize array to linked list.
	 * 
	 * @param nums - array to be de-serialized.
	 * @return head node of de-serialized list.
	 */
	public ListNode generateList(int[] nums) {
		ListNode head = new ListNode(0);
		ListNode p = head;
		for (int i = 0; i < nums.length; i++) {
			p.next = new ListNode(nums[i]);
			p = p.next;
		}
		return head.next;
	}
	
	/*
	 * Helper function to assert the result of the sorted list.
	 * 
	 * @param nums - original array.
	 * @param head - head node of sorted array.
	 * @return true or false based on the assertion result
	 */
	public boolean assertequal(int[] nums, ListNode head) {
		Arrays.sort(nums);
		int len = nums.length, i = 0;

		while (i < len && head != null) {
			if (nums[i] != head.val) {
				return false;
			}
			i++;
			head = head.next;
		}
		if (i != len || head != null)
			return false;
		
		return true;
	}
}
