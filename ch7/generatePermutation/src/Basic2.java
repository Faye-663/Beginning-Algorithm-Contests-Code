import java.util.Scanner;


public class Basic2 {

	static int[] p;
	static int[] vis;   //判断i是否已用

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			
			p = new int[n];
			vis = new int[n+1];
			print_permutation(n, p, 0);

		}
	}

	/**
	 * 
	 * @param n
	 *            n的值
	 * @param result
	 *            已经确定的“前缀”序列
	 * @param cur
	 *            当前需要确定的元素位置
	 */
	private static void print_permutation(int n, int[] result, int cur) {
		// 递归边界,递归出口
		if (cur == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		} else {
			// 尝试在result[j]中填入各种整数i(1~n)
			for (int i = 1; i <= n; i++) {
				if (vis[i] == 0) {
					result[cur] = i;
					
					//设置使用标志
					vis[i] =i;
					print_permutation(n, result, cur + 1);
					//清除标志
					vis[i] = 0;
				}
			}
		}
	}

}
