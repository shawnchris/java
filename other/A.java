
package other;

import java.util.*;

public class A {

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            int gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }

    // Function check whether a number
    // is prime or not
    public static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;

        // Check if n=2 or n=3
        if (n == 2 || n == 3)
            return true;

        // Check whether n is divisible by 2 or 3
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        // Check from 5 to square root of n
        // Iterate i by (i+6)
        for (int i = 5; i <= Math.sqrt(n); i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
    public int longestSubarray(int[] nums, int limit) {
        int left = 0, right, max = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (right = 0; right < nums.length; right++) {
            map.put(nums[right], 1 + map.getOrDefault(nums[right], 0));
            while (map.lastEntry().getKey() - map.firstEntry().getKey() > limit) {
                map.put(nums[left], map.get(nums[left] - 1));
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
    static class Ch {
        int attack;
        int defense;
        Ch (int attack, int defense) {
            this.attack = attack;
            this.defense = defense;
        }
    }
    public int numberOfWeakCharacters(int[][] properties) {
        List<Ch> list = new ArrayList<>();
        for (int[] p : properties) {
            list.add(new Ch(p[0], p[1]));
        }
        list.sort(Comparator.comparing(c -> c.attack));

        int maxDefense = Integer.MIN_VALUE;
        int maxAttack = Integer.MAX_VALUE;
        int res = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            Ch c = list.get(i);
            System.out.println(c.attack + "," + c.defense + ":" + maxAttack + "," + maxDefense);
            if (maxAttack > c.attack && maxDefense > c.defense) {
                res++;
            } else {
                maxAttack = Math.max(maxAttack, c.attack);
                maxDefense = Math.max(maxDefense, c.defense);
            }
        }

        return res;
    }

    private static int MOD = 1000000007;

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (String word: words1) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }
        for (String word: words2) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }

        int count = 0;
        for (String key: map1.keySet()) {
            if (map1.get(key) == 1 && map2.containsKey(key) && map2.get(key) == 1) {
                count++;
            }
        }
        return count;
    }

    public int minimumBuckets(String street) {
        int bCount = 0;
        int i = 0;
        while (i < street.length()) {
            int j = street.indexOf('H', i);
            if (noSpace(street, j)) return -1;
            if (canSave(street, j)) {
                bCount++;
                i = j + 3;
            } else {
                bCount++;
                i = j + 1;
            }
        }

        return bCount;
    }
    private boolean noSpace(String street, int index) {
        boolean noLeftSpace = index == 0 || street.charAt(index - 1) != '.';
        boolean noRightSpace = index == street.length() - 1 || street.charAt(index + 1) != '.';
        return noLeftSpace && noRightSpace;
    }
    private boolean canSave(String street, int index) {
        return index + 2 < street.length() && street.charAt(index + 2) == 'H';
    }

    public static void main(String[] args) {
        System.out.println(-1 % 4);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        A a = new A();
        System.out.println(a);
        List<String> cars = new ArrayList<>();
        cars.add("Volvo");
        cars.add("Ford");
        cars.add("BMW");
        cars.add("Volvo");
        cars.add("Ford");
        cars.add("Volvo");
        Map<String, Integer> counts = new HashMap<>();
        // Your code starts from here.
        // Should print 3.
        System.out.println(counts.get("Volvo"));
        // Should print 2.
        System.out.println(counts.get("Ford"));
        // Should print 1.
        System.out.println(counts.get("BMW"));
    }

    public int longestSlidingWindow(int[] nums, int target) {
        int max = -1;
        int runningSum = 0;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && runningSum < target) {
                runningSum += nums[j];
                if (runningSum == target) {
                    max = Math.max(max, j - i + 1);
                }
                j++;
            }

            runningSum -= nums[i];
            if (runningSum == target) {
                max = Math.max(max, j - i - 1);
            }
        }

        return max;
    }

    public int fromNegativeBase(String str, int negBase) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            res += Integer.valueOf(str.substring(str.length() - i - 1, str.length() - i)) * Math.pow(negBase, i);
        }
        return res;
    }

    public String toNegativeBase(int n, int negBase) {
        //  If n is zero then in any base it will be 0 only
        if (n == 0) return "0";

        String converted = "";
        while (n != 0) {
            // Get remainder by negative base, it can be negative also
            int remainder = n % negBase;
            n /= negBase;

            // if remainder is negative, add abs(base) to it and add 1 to n
            if (remainder < 0) {
                remainder += (-negBase);
                n += 1;
            }

            // convert remainder to string add into the result
            converted = remainder + converted;
        }

        return converted;
    }

    public int orangesRotting(int[][] A) {
        int m = A.length, n = A[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        int fresh = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 2) {
                    queue.add(i * n + j);
                    visited.add(i * n + j);
                } else if (A[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (queue.isEmpty()) {
            return -1;
        }
        if (fresh == 0) {
            return 0;
        }

        int step = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int r = p / n;
                int c = p % n;
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= m || nr < 0 || nc >= n || nc < 0 || A[nr][nc] != 1 || visited.contains(nr * n + nc)) {
                        continue;
                    }
                    A[nr][nc] = 2;
                    fresh--;
                    visited.add(nr * n + nc);
                    queue.add(nr * n + nc);
                }
            }
        }

        return fresh == 0 ? step - 1 : -1;
    }

    private int bfs(int[][] A, int[] e) {
        int m = A.length, n = A[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(e[0] * n + e[1]);
        visited.add(e[0] * n + e[1]);

        int step = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int p = queue.poll();
                int r = p / n;
                int c = p % n;

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr >= m || nr < 0 || nc >= n || nc < 0 || visited.contains(nr * n + nc)) continue;
                    if (A[nr][nc] == '+') continue;
                    if (nr == 0 || nr == m - 1 || nc == 0 || nc == n - 1) {
                        return step;
                    }
                    queue.add(nr * n + nc);
                    visited.add(nr * n + nc);
                    // System.out.println("Add [" + nr + "," + nc + "]" );
                }
            }
        }

        return -1;
    }

    public boolean sumGame(String num) {
        int leftSum = 0;
        int rightSum = 0;
        int leftQm = 0;
        int rightQm = 0;
        for (int i = 0; i < num.length(); i++) {
            if (i < num.length() / 2) {
                if (num.charAt(i) == '?') {
                    leftQm++;
                } else {
                    leftSum += Integer.parseInt(num.charAt(i) + "");
                }
            } else {
                if (num.charAt(i) == '?') {
                    rightQm++;
                } else {
                    rightSum += Integer.parseInt(num.charAt(i) + "");
                }
            }
        }
        return alice(leftSum, leftQm, rightSum, rightQm, new HashMap<>());
    }
    private boolean alice(int leftSum, int leftQm, int rightSum, int rightQm, Map<String, Boolean> cache) {
        if (leftQm == 0 && rightQm == 0 && leftSum != rightSum) {
            return true;
        }

        String signature = leftSum + "," + leftQm + "," + rightSum + "," + rightQm;
        if (cache.containsKey(signature)) {
            return cache.get(signature);
        }

        if (leftQm > 0) {
            for (int i = 0; i < 10; i++) {
                if (!bob(leftSum + i, leftQm - 1, rightSum, rightQm, cache)) {
                    cache.put(signature, true);
                    return true;
                }
            }
        }
        if (rightQm > 0) {
            for (int i = 0; i < 10; i++) {
                if (!bob(leftSum, leftQm, rightSum + i, rightQm - 1, cache)) {
                    cache.put(signature, true);
                    return true;
                }
            }
        }
        cache.put(signature, false);
        return false;
    }
    private boolean bob(int leftSum, int leftQm, int rightSum, int rightQm, Map<String, Boolean> cache) {
        if (leftQm == 0 && rightQm == 0 && leftSum == rightSum) {
            return true;
        }

        String signature = leftSum + "," + leftQm + "," + rightSum + "," + rightQm;
        if (cache.containsKey(signature)) {
            return cache.get(signature);
        }

        if (leftQm > 0) {
            for (int i = 0; i < 10; i++) {
                if (!alice(leftSum + i, leftQm - 1, rightSum, rightQm, cache)) {
                    cache.put(signature, true);
                    return true;
                }
            }
        }
        if (rightQm > 0) {
            for (int i = 0; i < 10; i++) {
                if (!alice(leftSum, leftQm, rightSum + i, rightQm - 1, cache)) {
                    cache.put(signature, true);
                    return true;
                }
            }
        }
        cache.put(signature, false);
        return false;
    }

    private Map<Integer, int[]> getMap() {
        Map<Integer, int[]> adj = new HashMap<>();
        adj.put(1, new int[]{6, 8});
        adj.put(2, new int[]{9, 7});
        adj.put(3, new int[]{4, 8});
        adj.put(4, new int[]{3, 9, 0});
        adj.put(5, new int[]{});
        adj.put(6, new int[]{1, 7, 0});
        adj.put(7, new int[]{6, 2});
        adj.put(8, new int[]{3, 1});
        adj.put(9, new int[]{4, 2});
        adj.put(0, new int[]{6, 4});
        return adj;
    }

    private void print(int[] a) {
        for (int i : a) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    private void print(int[][] a) {
        for (int[] b : a) {
            print(b);
        }
        System.out.println();
    }

    void printList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + ", ");
        }
        System.out.println();
    }

    private void printSet(Set<Integer> treeSet) {
        Iterator<Integer> i = treeSet.iterator();
        while (i.hasNext()) {
            System.out.print(i.next() + ",");
        }
        System.out.println();

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    static class Pair extends Object {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
//        @Override
//        public int hashCode() {
//            return
//        }
    }

    class UF {
        private int[] parent;
        private int[] rank;
        private int count;

        public UF(int numberOfElements) {
            parent = new int[numberOfElements];
            rank = new int[numberOfElements];
            count = numberOfElements;
            for (int i = 0; i < numberOfElements; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int p) {
            // my version of compression find
            if (parent[p] == p) return p;

            int q = p;
            while (parent[q] != q) {
                q = parent[q];
            }
            parent[p] = q;
            return q;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (rank[rootP] > rank[rootQ])
                parent[rootQ] = rootP;
            else if (rank[rootP] < rank[rootQ])
                parent[rootP] = rootQ;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }

        public int count() {
            return count;
        }

    }

    static class TrieNode {
        private static final int R = 26;
        public TrieNode[] next;
        public int isWord;
        public TrieNode() {
            next = new TrieNode[R];
            isWord = 0;
        }
    }

    private void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }

    // method to calculate gcd of two numbers
    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    static boolean colLine(int x1, int y1, int x2, int y2, int x3, int y3) {
        if ((y3 - y2) * (x2 - x1) ==
                (y2 - y1) * (x3 - x2))
            return true;
        else
            return false;
    }
}
