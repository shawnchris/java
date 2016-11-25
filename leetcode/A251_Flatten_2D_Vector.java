package leetcode;
import java.util.*;

public class A251_Flatten_2D_Vector {
	class Vector2D implements Iterator<Integer> {
	    List<List<Integer>> vec2d;
	    int vecSize = -1, vecPtr = 0, elePtr = 0;
	    public Vector2D(List<List<Integer>> vec2d) {
	        this.vec2d = vec2d;
	        if (vec2d != null) {
	            vecSize = vec2d.size();
	            while (vecPtr < vecSize) {
	                if (vec2d.get(vecPtr) == null || vec2d.get(vecPtr).size() == 0) {
	                    vecPtr++;
	                }
	                else {
	                    break;
	                }
	            }
	        }
	    }

	    @Override
	    public Integer next() {
	        Integer result = vec2d.get(vecPtr).get(elePtr);
	        elePtr++;
	        if (elePtr == vec2d.get(vecPtr).size()) {
	            elePtr = 0;
	            vecPtr++;
	            while (vecPtr < vecSize) {
	                if (vec2d.get(vecPtr) == null || vec2d.get(vecPtr).size() == 0) {
	                    vecPtr++;
	                }
	                else {
	                    break;
	                }
	            }
	        }
	        return result;
	    }

	    @Override
	    public boolean hasNext() {
	        if (vecPtr >= vecSize) return false;
	        return true;
	    }
	}
}
