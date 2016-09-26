package leetcode;
import java.util.*;

public class A388_Longest_Absolute_File_Path {
    public int lengthLongestPath(String input) {
        int result = 0;
        if (input == null || input.length() == 0) return result;
        
        Stack<String> stack = new Stack<>();
        String parent = "";
        String[] lines = input.split("\\n");
        
        for (String line : lines) {
        	int indent = countIndent(line);
        	
        	// get the right parent directory
        	while (stack.size() > indent) {
        		String dir = stack.pop();
        		parent = parent.substring(0, parent.length() - dir.length() - 1);
        	}
        	
        	String name = line.substring(indent);
        	if (isFile(name)) {
        		result = Math.max(result, parent.length() + name.length());
        	}
        	
        	stack.push(name);
        	parent += "/" + name;
        }
    	
        return result;
    }
    
    private int countIndent(String s) {
    	int result = 0, i = 0;
    	while (i < s.length() && s.charAt(i) == '\t') {
    		i++;
    		result++;
    	}
    	return result;
    }
    
    private boolean isFile(String s) {
    	int dotIndex = s.indexOf('.');
    	return dotIndex > 0;
    }
    
	public static void main(String[] args) {
		A388_Longest_Absolute_File_Path la = new A388_Longest_Absolute_File_Path();
		/*
		 *dir
		 *	subdir1
		 *  subdir2
		 *		file.ext
		 */
		System.out.println(la.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext")); //20
		//32
		System.out.println(la.lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
	}

}
