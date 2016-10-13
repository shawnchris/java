package leetcode;

public class A418_Sentence_Screen_Fitting {
    public static int wordsTyping(String[] sentence, int rows, int cols) {
    	int max = 0;
        for (String s : sentence)
        	max = Math.max(max, s.length());
        if (max > cols) return 0;
        
        int row = 0, col = 0, i = 0, result = 0;
        while (row < rows) {
        	while (col + sentence[i].length() <= cols) {
        		col += sentence[i].length();
        		//white space
        		if (col < cols) col++;
        		i++;
        		if (i == sentence.length) {
        			result++;
        			i = 0;
        			if (col == cols) {
        				result = result * (rows / (row + 1));
        				row = rows - rows % (row + 1) - 1;
        			}
        		}
        	}
        	row++;
        	col = 0;
        }
        
    	return result;
    }
    
	public static void main(String[] args) {
		System.out.println(wordsTyping(new String[] {"hello","world"},2, 8));
		System.out.println(wordsTyping(new String[] {"a", "bcd", "e"},3, 6));
		System.out.println(wordsTyping(new String[] {"I", "had", "apple", "pie"},4,5));
		System.out.println(wordsTyping(new String[] {"a", "e"}, 20000, 20000)); //100000000
		System.out.println(wordsTyping(new String[] {"f","p","a"}, 8, 7)); //10
	}

}
