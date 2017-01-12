package leetcode;
import java.util.*;

public class A288_Unique_Word_Abbreviation {
	public class ValidWordAbbr {
	    Map<String, String> map;
	    public ValidWordAbbr(String[] dictionary) {
	        map = new HashMap<String, String>();
	        for (String word : dictionary) {
	            String abb = getAbbreviation(word);
	            if (map.containsKey(abb)) {
	                if (!map.get(abb).equals(word)) { // more than 1 word
	                    map.put(abb, "");
	                }
	            }
	            else {
	                map.put(abb, word);
	            }
	        }
	    }

	    public boolean isUnique(String word) {
	        String abb = getAbbreviation(word);
	        return !map.containsKey(abb) || map.get(abb).equals(word);
	    }
	    
	    private String getAbbreviation(String word) {
	        if (word.length() < 3) return word;
	        return word.substring(0, 1) + word.substring(1, word.length() - 1).length()
	        		+ word.substring(word.length() - 1);
	    }
	}
	public static void main(String[] args) {
		A288_Unique_Word_Abbreviation uwa = new A288_Unique_Word_Abbreviation();
		//["igprlgmj","xyitppjcakbxzsgntet"]
		String[] dict = {"hello","hallo","hello","zzzg","akvzveklsk","pfziulaweuudrr","jccd","jrvzwkufpmze","cwfvhh","elmjdcysa","tdokjqfvrfjbwb","silsrnnvold","vqh","qyqnua","emwydo","aiehnrev","mplvtbipycf","cyxyde","ldyr","gtuvflak","xahpmzaj","pnb","wjneqv","hjh","crqw","uscswjlgemxqpp","mpnwvjgfrpzxjyxtasp","uwayurfgopyogumfm","ncbmyrbfejger","lozobnm","mwzfsezjkfvuumh","wxhkgtlqfvdto"};
		ValidWordAbbr vw = uwa.new ValidWordAbbr(dict);
		System.out.println(vw.isUnique("hallo"));
		System.out.println(vw.isUnique("xyitppjcakbxzsgntet"));
		/*
		String[] dict = {"pelnlwwkfa","ayyrq","may","zzzg","akvzveklsk","pfziulaweuudrr","jccd","jrvzwkufpmze","cwfvhh","elmjdcysa","tdokjqfvrfjbwb","silsrnnvold","vqh","qyqnua","emwydo","aiehnrev","mplvtbipycf","cyxyde","ldyr","gtuvflak","xahpmzaj","pnb","wjneqv","hjh","crqw","uscswjlgemxqpp","mpnwvjgfrpzxjyxtasp","uwayurfgopyogumfm","ncbmyrbfejger","lozobnm","mwzfsezjkfvuumh","wxhkgtlqfvdto"};
		ValidWordAbbr vw = uwa.new ValidWordAbbr(dict);
		System.out.println(vw.isUnique("igprlgmj"));
		System.out.println(vw.isUnique("xyitppjcakbxzsgntet"));
		*/
	}

}
