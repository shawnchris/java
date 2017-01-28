package leetcode;

public class A492_Construct_the_rectangle {
    public int[] constructRectangle(int area) {
        int[] result = new int[2];
        
        int w = (int) Math.sqrt(area);
        for (; w >= 1; w--) {
            if (area % w == 0) {
                result[0] = area / w;
                result[1] = w;
                break;
            }
        }
        
        return result;
    }
    
	public static void main(String[] args) {
		A492_Construct_the_rectangle ce = new A492_Construct_the_rectangle();
		print(ce.constructRectangle(4));
		print(ce.constructRectangle(5));
		print(ce.constructRectangle(10000000));
		print(ce.constructRectangle(100));
	}
	
	private static void print (int[] r) {
		System.out.println(r[0] + " " + r[1]);
	}

}
