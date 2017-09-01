package leetcode;

import java.util.List;
import java.util.Stack;

public class A636_Exclusive_Time_of_Functions {
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        if (logs == null || logs.size() == 0) return result;

        int id = 0, time = 0;
        Stack<Integer> stack = new Stack<>();
        for (String log : logs) {
            String[] arr = log.split("\\:");
            if (arr[1].equals("start")) {
                if (!stack.isEmpty()) {
                    id = stack.peek();
                    result[id] += Integer.parseInt(arr[2]) - time;
                }
                stack.push(Integer.parseInt(arr[0]));
                time = Integer.parseInt(arr[2]);
            }
            else { // end
                id = stack.pop();
                result[id] += Integer.parseInt(arr[2]) - time + 1;
                time = Integer.parseInt(arr[2]) + 1;
            }
        }

        return result;
    }
}
