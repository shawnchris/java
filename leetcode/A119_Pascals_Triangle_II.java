package leetcode;
import java.util.*;

public class A119_Pascals_Triangle_II {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex < 0) return new ArrayList<Integer>();
        
		Integer[] array = new Integer[rowIndex + 1];
		for (int i = 0; i <= rowIndex; i++)
		    array[i] = 1;

		for(int i = 2; i <= rowIndex; i++)
			for (int j = i - 1; j > 0; j--)
				array[j] = array[j] + array[j - 1];

		return Arrays.asList(array);
    }
}
