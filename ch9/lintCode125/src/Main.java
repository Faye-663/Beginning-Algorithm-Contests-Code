public class Main {

	/**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
	public static int backPackII(int m, int[] A, int[] V) {
		// write your code here
		int n = A.length;
		//d[i][j]:前i个物品，装满j容量的包，最大值
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				d[i][j] = d[i - 1][j];
				if (j >= A[i - 1]) {
					d[i][j] = Math.max(d[i][j], d[i - 1][j - A[i - 1]]
							+ V[i - 1]);
				}
			}
		}
		return d[n][m];
	}

	//数组d为一维
//	public static int backPackII(int m, int[] A, int[] V) {
//		// write your code here
//		int n = A.length;
//		int[] d = new int[m + 1];
//		for (int i = 1; i <= n; i++) {
			//需要逆推，在计算d(i,j)之前，d[j]里保存的就是f(i－ 1 , j)的值
//			for (int j = m; j >= 0; j--) {
//				if (j >= A[i - 1]) {
//					d[j] = Math.max(d[j], d[j - A[i - 1]] + V[i - 1]);
//				}
//			}
//			return d[m];
//		}
//	}

	public static void main(String[] args) {
		int m = 10;
		int[] a = { 2, 3, 5, 7 };
		int[] v = { 1, 5, 2, 4 };
		System.out.println(backPackII(m, a, v));

	}

}
