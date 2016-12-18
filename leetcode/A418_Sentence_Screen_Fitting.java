package leetcode;

public class A418_Sentence_Screen_Fitting {
	public static int wordsTyping2(String[] sentence, int rows, int cols) {
        if (rows <=0 || cols <= 0) return 0;
        
        int result = 0, i = 0, j = 0, len = 0;
        while (i < rows) {
            len += sentence[j].length();
            if (len > cols) {
                len = 0; i++; j--; // new line
            }
            else {
                len++; // space;
            }
            
            j++;
            if (j == sentence.length) {
                j = 0; result++; // new round
            }
            
            if (len == 0 && j == 0) { // i rows can fit r times of sentence
                result = rows / i * result;
                i = rows - rows % i;
            }
        }
        
        return result;
    }
	
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
		System.out.println(wordsTyping2(new String[] {"hello","world"},2, 8)); //1
		System.out.println(wordsTyping2(new String[] {"a", "bcd", "e"},3, 6)); //2
		System.out.println(wordsTyping2(new String[] {"I", "had", "apple", "pie"},4,5)); //1
		System.out.println(wordsTyping2(new String[] {"a", "e"}, 20000, 20000)); //100000000
		System.out.println(wordsTyping2(new String[] {"f","p","a"}, 8, 7)); //10
	}

}
