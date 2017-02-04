package interview.linkedin;

public interface Triangle { 
	/** Three segments of lengths A, B, C form a triangle iff 
	 * A + B > C 
	 * B + C > A 
	 * A + C > B 
	 * e.g. 
	 * 6, 4, 5 can form a triangle 
	 * 10, 2, 7 can't
	 * Given a list of segments lengths algorithm should find at least one triplet of segments that form a triangle (if any). 
	 * Method should return an array of either: 
	 * - 3 elements: segments that form a triangle (i.e. satisfy the condition above) 
	 * - empty array if there are no such segments */ 
	int[] getTriangleSides(int[] segments);
}
