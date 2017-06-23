package datastructure;

/**
 * Created by shawngao on 6/23/17.
 */

public class MyLinkedHashMap {

    //public LinkedList<int> idxArr;
    private ListNode head, tail;
    public HashEntry[] eleArr;
    private int capacity = 251; //Some random prime number

    //Constructor
    public MyLinkedHashMap() {
        //eleArr = new HashEntry[capacity];
        /*for (int i = 0; i < capacity; i++)
        {
            eleArr[i] = null;
        }*/
        //idxArr = new LinkedList<int>();
        // create dummy head and tail
        head = new ListNode(0);
        tail = new ListNode(0);
        Clear();
    }

    //Add
    public void Add(int key, int value) {
        int hash = key % capacity;
        while (eleArr[hash] != null && eleArr[hash].GetKey() != key) {
            hash = (hash + 1) % capacity;
        }
        eleArr[hash] = new HashEntry(key, value, addToList(hash));
        //idxArr.AddFirst(hash);
    }

    //Search
    public int Get(int key) {
        int hash = key % capacity;
        while (eleArr[hash] != null && eleArr[hash].GetKey() != key) {
            hash = (hash + 1) % capacity;
        }
        //Not exist
        if (eleArr[hash] == null)
            return -1;
        else
            return eleArr[hash].GetValue();
    }

    //Clear, is it a true O(1) time?
    public void Clear() {
        //var newEleArr = new HashEntry[capacity];
        //var newIdxArr = new LinkedList<int>();
        eleArr = new HashEntry[capacity];
        head.next = tail;
        tail.prev = head;
    }

    //Delete
    public void Delete(int key) {
        int hash = key % capacity;

        // remove the list node in O(1) time
        ListNode toBeDelete = eleArr[hash].listNode;
        toBeDelete.prev.next = toBeDelete.next;
        toBeDelete.next.prev = toBeDelete.prev;

        eleArr[hash] = null;
    }

    //Iterator
    public void Iterate() {
        ListNode ptr = head.next;
        while (ptr != tail) {
            //Print or do something
            System.out.println("Key: " + eleArr[ptr.val].GetKey() + "Value: " + eleArr[ptr.val].GetValue());
        }
    }

    private ListNode addToList(int hash) {
        ListNode newNode = new ListNode(hash);
        // insert it to the end of the list (or head if you prefer)
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;

        return newNode;
    }
}

class HashEntry {
    private int key;
    private int value;
    ListNode listNode;

    public HashEntry(int k, int v, ListNode n) {
        key = k;
        value = v;
        listNode = n;
    }

    public int GetKey() {
        return key;
    }

    public int GetValue() {
        return value;
    }
}

class ListNode {
    int val;
    ListNode prev;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}