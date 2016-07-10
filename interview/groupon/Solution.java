package interview.groupon;
import java.util.*;

/* 1st phone interview
 * 151	Reverse Words in a String
 */

/* 2nd phone interview
 * Write a method to parse strings
 *
 * Input = "This is a test. Another one here. sdfd fgf hhh."
 * Return = [{1=>"This", 2=>"is"..}, {1=>"Another", 2=>"one" ...}]
 * 
 * 1. split(".") => String[] [0] 
 * 2. loop each of the string split("\\s+") => 
 *
 * 1. normal cases;
 * 2. corner cases; - null, short, long "co, ab.d. adfl;lasdjflj" "asdfsdffffffffffffffffffffsdfasdfasdfasdfasdf"
 * 3. multi stops, white space "................................" "                                               "
 * 4. "\\" "\\." " " \\s"
 */

class Solution {
  public static List<Map<Integer, String>> parser(String str) {
    if (str == null) return null;
    List<Map<Integer, String>> result = new ArrayList<Map<Integer,String>>();
    
    String[] sp1 = str.split("\\.");
    for (String sentence : sp1) {
      String[] sp2 = sentence.split("\\s+");
      Map<Integer, String> map = new HashMap<>();
      int counter = 0;
      for (String word : sp2) {
        if (word.length() == 0) continue;
        counter++;
        map.put(counter, word);
      }
      result.add(map);
    }
    
    return result;
    
  }
  
  public static void main(String[] args) {
    //Solution sl = new Solution();
    String test1 = "This is a test. Another one here. sdfd fgf hhh.";
    List<Map<Integer, String>> result = parser(test1);
    for (Map<Integer, String> map : result) {
      for (Integer i : map.keySet()) {
        System.out.println("Key: " + i + " Value: " + map.get(i));
      }
    }
  }
  
  /* Onsite interviews:
   * 1. Binary Tree creation
   * 2. Palindrome Integer
   */
}

// Jarryn Vannatta
// Jeromy Warnick
//
// Alvin Hao - Interviewer
