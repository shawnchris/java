package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shawngao on 9/5/17.
 */
public class A638_Shopping_Offers {
  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    Map<List<Integer>, Integer> memo = new HashMap();
    return shopping(price, special, needs, memo);
  }

  private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo) {
    if (memo.containsKey(needs)) return memo.get(needs);

    int j = 0, res = dot(needs, price);
    for (List<Integer> s : special) {
      ArrayList<Integer> clone = new ArrayList<>(needs);
      for (j = 0; j < needs.size(); j++) {
        int diff = clone.get(j) - s.get(j);
        if (diff < 0) break;
        clone.set(j, diff);
      }
      if (j == needs.size())
        res = Math.min(res, s.get(j) + shopping(price, special, clone, memo));
    }

    memo.put(needs, res);
    return res;
  }

  private int dot(List<Integer> a, List<Integer> b) {
    int sum = 0;
    for (int i = 0; i < a.size(); i++) {
      sum += a.get(i) * b.get(i);
    }
    return sum;
  }
  //what?
}
