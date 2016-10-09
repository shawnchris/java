package leetcode;

public class A416_Partition_Equal_Subset_Sum {
    public boolean canPartition(int[] nums) {
        // Calculate sum of the elements in array
        int sum = 0, n = nums.length;
        for (int i = 0; i < n; i++)
            sum += nums[i];
 
        // If sum is odd, there cannot be two subsets with equal sum
        if (sum % 2 != 0) return false;
 
        // Find if there is subset with sum equal to half of total sum
        return isSubsetSum (nums, n, sum/2);
    }
    private boolean isSubsetSum (int arr[], int n, int sum) {
        // Base Cases
        if (sum == 0) return true;
        if (n == 0 && sum != 0) return false;
 
        // If last element is greater than sum, then ignore it
        if (arr[n-1] > sum)
            return isSubsetSum (arr, n-1, sum);
 
        /* else, check if sum can be obtained by any of
           the following
        (a) including the last element
        (b) excluding the last element
        */
        return isSubsetSum (arr, n-1, sum) ||
               isSubsetSum (arr, n-1, sum-arr[n-1]);
    }
    
    //DP
    public boolean canPartition2(int[] nums) {
        int sum = 0, n = nums.length;
        int i, j;
 
        // Caculcate sum of all elements
        for (i = 0; i < n; i++)
            sum += nums[i];
 
        if (sum % 2 != 0)
            return false;
 
        boolean part[][]=new boolean[sum / 2 + 1][n + 1];
 
        // initialize top row as true
        for (i = 0; i <= n; i++)
            part[0][i] = true;
 
        // initialize leftmost column, except part[0][0], as 0
        for (i = 1; i <= sum / 2; i++)
            part[i][0] = false;
 
        // Fill the partition table in botton up manner
        for (i = 1; i <= sum / 2; i++)
        {
            for (j = 1; j <= n; j++)
            {
                part[i][j] = part[i][j-1];
                if (i >= nums[j-1])
                    part[i][j] = part[i][j] ||
                                 part[i - nums[j-1]][j-1];
            }
        }
 
        /* // uncomment this part to print table
        for (i = 0; i <= sum/2; i++)
        {
            for (j = 0; j <= n; j++)
                printf ("%4d", part[i][j]);
            printf("\n");
        } */
 
        return part[sum/2][n];
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
