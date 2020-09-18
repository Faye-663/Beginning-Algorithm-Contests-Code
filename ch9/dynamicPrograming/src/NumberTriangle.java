import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 数字三角形问题。 有一个由非负整数组成的三角形，第一行只有一个数，除了最下行之外每个数的左下方和右下方各有一个数，
 * 从第一行的数开始，每次可以往左下或右下走一格，直到走到最下行，把沿途经过的数全部加起来。如何走才能使得这个和尽量大？
 * 
 * 状态转移方程： d(i,j) = a(i,j) + max{d(i+1,j),d(i+1,j+1)}
 * 
 */
public class NumberTriangle {

	static int[][] a;
	static String[] strs;
	static int[][] d;

	// 时间效率太低，其原因在于重复计算,O(2^n)
	private static int recursion(int i, int j, int n) {
		if (i == n) {
			return a[i][j];
		} else {
			return a[i][j]
					+ Math.max(recursion(i + 1, j, n),
							recursion(i + 1, j + 1, n));
		}
	}

	// 递推计算,逆序枚举,O(n^2)
	private static void iteration(int n) {
		for (int j = 1; j <= n; j++) {
			d[n][j] = a[n][j];
		}
		for (int i = n - 1; i > 0; i--) {
			for (int j = 1; j <= i; j++) {
				d[i][j] = a[i][j] + Math.max(d[i + 1][j], d[i + 1][j + 1]);
			}
		}
	}

	// 记忆化搜索,需要记录每个状态是否已经计算过,O(n^2)
	private static int memoization(int i, int j, int n) {
		if (d[i][j] > 0) {
			return d[i][j];
		} else {
			if (i == n) {
				return a[i][j];
			} else {
				return d[i][j] = a[i][j]
						+ Math.max(memoization(i + 1, j, n),
								memoization(i + 1, j + 1, n));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while (!"0".equals(s = in.readLine())) {
			int n = Integer.parseInt(s);
			a = new int[n + 1][n + 1];
			d = new int[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				strs = in.readLine().split(" ");
				for (int j = 1; j <= i; j++) {
					a[i][j] = Integer.parseInt(strs[j - 1]);
				}
			}

			// int ans = recursion(1,1,n);

			// iteration(n);
			// int ans = d[1][1];

			int ans = memoization(1, 1, n);
			out.println(ans);

			out.println();
			out.flush();
		}
	}

}
