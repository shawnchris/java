package leetcode;
import java.util.*;

public class A464_Can_I_Win {
	Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;
        
        map = new HashMap<>();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }
    
    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) return false;
        int key = format(used);
        if(!map.containsKey(key)){
            // try every unchosen number as next step
            for(int i=1; i<used.length; i++){
                if(!used[i]){
                    used[i] = true;
                    // check whether this lead to a win (i.e. the other player lose)
                    if(!helper(desiredTotal-i)){
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
   
    // transfer boolean[] to an Integer 
    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
	
	public static void main(String[] args){
		A464_Can_I_Win game = new A464_Can_I_Win();
		System.out.println("\nPlayer-"+(game.canIWin(20, 210)?"1":"2")+" wins!");
	}

}
