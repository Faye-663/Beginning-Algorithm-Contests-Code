import java.util.Scanner;

/**
 * 按字典序从小到大的顺序输出1~n的所有排列
 * 
 * 先输出所有以1开头的排列（这一步是递归调用），然后输出以2开头的排列（又是递归调用），接着...最后...
 */
public class Basic {

	static int[] p = new int[100];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
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
				boolean flag = true;
				for (int j = 0; j < cur; j++) {
					// 如果i已经在result[0]~result[j-1]出现过 则不能再选
					if (result[j] == i) {
						flag = false;
					}
				}
				if (flag) {
					result[cur] = i;
					print_permutation(n, result, cur + 1);
				}
			}
		}
	}

}
