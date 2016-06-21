package leetcode;
import java.util.*;

public class A138_Copy_List_with_Random_Pointer {
    Map<Integer, RandomListNode> map = new HashMap<Integer, RandomListNode>();
    
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        if (map.containsKey(head.label)) return map.get(head.label);
        
        RandomListNode newNode = new RandomListNode(head.label);
        map.put(newNode.label, newNode);
        
        newNode.random = copyRandomList(head.random);
        newNode.next = copyRandomList(head.next);
        
        return newNode;
    }
}
