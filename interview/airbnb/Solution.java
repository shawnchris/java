package interview.airbnb;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  private boolean isPalindrome (String s) {
    if (s == null) return false;
    int len = s.length();
    if (len == 0 || len == 1) return true;
    for (int i = 0; i < len / 2; i++) {
      char c1 = s.charAt(i);
      char c2 = s.charAt(len - i - 1);
      if (c1 != c2)
        return false;
    }
    return true;
  }
  
  private String reverse (String s) {
    String result = "";
    for (int i = 0; i < s.length(); i++) {
      result = s.substring(i, i+1) + result;
    }
    return result;
  }
  
  private List<List<String>> findPalindromePair(String[] dict) {
    List<List<String>> result = new ArrayList<List<String>>();
    HashSet<String> set = new HashSet<String>();
    for (int i = 0; i < dict.length; i++)
      set.add(dict[i]);
    for (int i = 0; i < dict.length; i++) {
      String s = dict[i];
      for (int j = 1; j <= s.length(); j++) {
        String sub = reverse(s.substring(0, j));
        if (set.contains(sub)) {
          if (isPalindrome(s + sub)) {
            List<String> res = new ArrayList<String>();
            res.add(s);
            res.add(sub);
            result.add(res);
          }
        }
      }
    }
    return result;
  }
  
  public static void main(String[] args) {
    Solution sl = new Solution();
    System.out.println(sl.reverse("abcd"));
    String[] dict = {"race", "car"};
    String[] dict2 = {"cat", "dog", "elephant", "tac", "aire", "gigeria"};
    List<List<String>> r = sl.findPalindromePair(dict2);
    for (List<String> l : r) {
      for (String s : l)
        System.out.print(s + " ");
      System.out.println();
    }
  }
}


// Hello Willem!
// Hi Shan!

// aa, => aaaa
// abab

// Given a word, is this word a palindrome?
// Examples: "bob", "gig", "abba"
// "b_ob"

// Given a list of unique words, find the pairs of words that, when concatenated, will form a palindrome. For example:
// ["gab", "cat", "bag", "alpha" ] 
//   => ["gab", "bag"], ["bag", "gab"]


// key(abg),  value(bag, gab, gba)
// ["gab", "gba"]

// ["race", "car"] "racecar"

// Longerword.indexOf(Shorterword) = 0


// ["gab", "cat", "bag", "alpha", "race", "car" ] => ["gab", "bag"], ["bag", "gab"], ["race", "car"]
//  ["cat", "dog", "elephant", "tac", "aire", "gigeria"] => ["cat", "tac"], ["tac", "cat"], ["aire", "gigeria"]
//  ["cat", "dog", "elephant", "tac", "cath"] => ["cat", "tac"], ["tac", "cat"], ["cath", "tac"]