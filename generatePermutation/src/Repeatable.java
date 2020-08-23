import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * 输入数组P，并按字典序输出数组A各元素的所有全排列
 * 
 */

public class Repeatable {

	static int[] a = new int[100];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String s = in.nextLine();
			if ("0".equals(s)) {
				break;
			}

			String[] strs = s.split(" ");
			int[] p = new int[strs.length];
			for (int i = 0; i < strs.length; i++) {
				p[i] = Integer.parseInt(strs[i]);
			}

			// 对p排序
			Arrays.sort(p);
			print_permutation(p.length, p, a, 0);

		}
	}

	/**
	 * 
	 * @param n
	 *            p的长度
	 * @param p
	 *            p数组
	 * @param result
	 *            已经确定的“前缀”序列
	 * @param cur
	 *            当前需要确定的元素位置
	 */
	private static void print_permutation(int n, int[] p, int[] result, int cur) {
		// 递归边界,递归出口
		if (cur == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		} else {
			//在result中填入p数组中的数
			//确保P[i]和之前任一元素不相同
			for (int i = 0; i < n && (i == 0 || p[i] != p[i - 1]); i++) {
				//cp：p中值为p[i]的个数
				//cp：a中值为p[i]的个数
				int cp = 0;
				int ca = 0;

				for (int j = 0; j < n; j++) {
					if (p[i] == p[j]) {
						cp++;
					}
				}
				for (int j = 0; j < cur; j++) {
					if (p[i] == result[j]) {
						ca++;
					}
				}

				if (cp > ca) {
					result[cur] = p[i];
					print_permutation(n, p, result, cur + 1);
				}
			}
		}
	}

}
