
public class Solution {
	public static int lengthOfLIS(int[] nums) {
		int n = nums.length;
		int[] d = new int[n];
		int ans = d[0] = 1;
		
		for(int i = 1;i<n;i++){
			d[i] = 1;
			for(int j = 0;j<i;j++){
				if(nums[j]<nums[i]){
					d[i] = Math.max(d[i]-1, d[j])+1;
				}
			}
		}
		for(int i = 0;i<n;i++){
			ans = Math.max(ans, d[i]);
		}
		return ans;
    }
	public static void main(String[] args) {
		int[] nums = {10,9,2,5,3,7,101,18};
		System.out.println(lengthOfLIS(nums));

	}

}
