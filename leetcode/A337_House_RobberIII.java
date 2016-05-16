package leetcode;

public class A337_House_RobberIII {
    class Result {
        int rob;
        int norob;
        Result() { rob = 0; norob = 0;}
    }
    
    private Result helper(TreeNode root) {
        Result r = new Result();
        if (root == null)
            return r;
        
        Result lr = helper(root.left);
        Result rr = helper(root.right);
        
        r.rob = root.val + lr.norob + rr.norob;
        r.norob = Math.max(lr.rob, lr.norob) + Math.max(rr.rob, rr.norob);
        
        return r;
    }
    
    public int rob(TreeNode root) {
        if (root == null) return 0;
        Result r = helper(root);
        return Math.max(r.rob, r.norob);
    }
}
