package leetcode;

import java.util.*;

/**
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be
 * represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs
 * similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *     They have the same length (i.e., the same number of words)
 *     sentence1[i] and sentence2[i] are similar.
 *
 * Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example,
 * if the words a and b are similar, and the words b and c are similar, then a and c are similar.
 */
public class A737_Sentence_Similarity_II {
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        Map<String, Set<String>> map = createMap(similarPairs);
        for (int i = 0; i < sentence1.length; i++) {
            if (!similar(map, sentence1[i], sentence2[i])) {
                return false;
            }
        }
        return true;
    }
    private Map<String, Set<String>> createMap(List<List<String>> similarPairs) {
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String s1 = pair.get(0);
            String s2 = pair.get(1);
            if (!map.containsKey(s1) && !map.containsKey(s2)) {
                Set<String> set = new HashSet<>();
                set.add(s1);
                set.add(s2);
                map.put(s1, set);
                map.put(s2, set);
            } else if (map.containsKey(s1) && map.containsKey(s2)) {
                if (map.get(s1) != map.get(s2)) {
                    Set<String> set1 = map.get(s1);
                    Set<String> set2 = map.get(s2);
                    set1.addAll(set2);
                    for (String s : set2) {
                        map.put(s, set1);
                    }
                }
            } else {
                Set<String> set = map.containsKey(s1) ? map.get(s1) : map.get(s2);
                set.add(s1);
                set.add(s2);
                map.put(s1, set);
                map.put(s2, set);
            }
        }
        return map;
    }
    private boolean similar(Map<String, Set<String>> map, String s1, String s2) {
        if (map.containsKey(s1)) {
            return map.get(s1).contains(s2);
        }
        if (map.containsKey(s2)) {
            return map.get(s2).contains(s1);
        }
        return s1.equals(s2);
    }
}
