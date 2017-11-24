package other;


import java.util.*;
/*
问题描述：
类似 leetcode 282. Expression Add Operators  但是 不用考虑 *号
Given a string that contains only digits 0-9 and a target value,
return the number of ways to add binary operators (not unary) + or -
between the digits so they evaluate to the target value.
要求 返回添加opeartor的方案个数   signature是: public int addOperatorsNum(String num, int target)
*/

public class Mianaddoperator {
  public static void main(String[] args) {
    Solution1 solu = new Solution1();
    String num ="111111111";
    int target = 3;
    System.out.println("The number of way to add binary operators is: ");
    System.out.println(solu.addOperatorsNum(num, target));
    System.out.println();
    System.out.println("All possibilities to add binary operators are: ");
    List<String> result = solu.addOperators(num, target);
    System.out.println(result.size());
    result.stream().forEach(System.out::println);
  }
}


class Solution1 {
  int count = 0;

  public int addOperatorsNum(String num, int target) {
    if(num == null || num.length() == 0) return 0;
    return helper(num, target, 0, 0, new HashMap<>());
  }

  public int helper(String num, int target, int pos, long eval, Map<String, Integer> map){
    String key = pos + ":" +  eval;
    Integer way = map.get(key);
    if (way != null) {
      return way;
    }
    if(pos == num.length()){
      if(target == eval)
        return 1;
      return 0;
    }
    int curWay = 0;
    for(int i = pos; i < num.length(); i++){
      if(i != pos && num.charAt(pos) == '0') break;
      long cur = Long.parseLong(num.substring(pos, i + 1));
      if(pos == 0){
        curWay += helper(num, target, i + 1, cur, map);
      }
      else{
        curWay += helper(num, target, i + 1, eval + cur, map);
        curWay += helper(num, target, i + 1, eval - cur, map);
      }
    }
    map.put(key, curWay);
    return curWay;
  }


  public List<String> addOperators(String num, int target) {
    List<String> rst = new ArrayList<String>();
    if(num == null || num.length() == 0) return rst;
    helper(rst, "", num, target, 0, 0);
    System.out.println(count);
    return rst;
  }

  public void helper(List<String> rst, String path, String num, int target, int pos, long eval){
    if(pos == num.length()){
      count++;
      if(target == eval)
        rst.add(path);
      return;
    }
    for(int i = pos; i < num.length(); i++){
      if(i != pos && num.charAt(pos) == '0') break;
      long cur = Long.parseLong(num.substring(pos, i + 1));
      if(pos == 0){
        helper(rst, path + cur, num, target, i + 1, cur);
      }
      else{
        helper(rst, path + "+" + cur, num, target, i + 1, eval + cur);
        helper(rst, path + "-" + cur, num, target, i + 1, eval - cur);
      }
    }
  }
}