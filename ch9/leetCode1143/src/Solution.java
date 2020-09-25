
public class Solution {

	 public static int longestCommonSubsequence(String text1, String text2) {
		 int m = text1.length();
		 int n = text2.length();
		 int[][] d = new int[m+1][n+1];
		 
		 for(int i = 1;i<=m;i++){
			 for(int j = 1;j<=n;j++){
				 if(text1.charAt(i-1) == text2.charAt(j-1)){
					 d[i][j] = d[i-1][j-1]+1;
				 }else{
					 d[i][j] = Math.max(d[i-1][j], d[i][j-1]);
				 }
			 }
		 }
		 
		 return d[m][n];
	    }
	public static void main(String[] args) {
		String text1 = "abc";
		String text2 = "dcweadsfbefccc" ;
		System.out.print(longestCommonSubsequence(text1, text2));

	}

}
