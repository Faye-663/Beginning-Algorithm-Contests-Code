import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 本题要求最小、最大两个值，记忆化搜索就必须写两个。 在这种情况下，用递推更加方便
 * 
 * @author 11495
 * 
 */
public class DAG_CoinProblem_1 {

	static int[] v, maxd, mind;
	static String[] temp;
	static BufferedReader in = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int[] max_coin, min_coin;

	// 字典序打印
	private static void print_ans(int[] d, int s, int n) {
		for (int i = 1; i <= n; i++) {
			if (s >= v[i] && d[s] == d[s - v[i]] + 1) {
				out.print(v[i] + " ");
				print_ans(d, s - v[i], n);
				break;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		String str;
		while (!"0".equals(str = in.readLine())) {
			int n = Integer.parseInt(str);
			temp = in.readLine().split(" ");
			v = new int[n + 2];
			for (int i = 1; i <= n; i++) {
				v[i] = Integer.parseInt(temp[i - 1]);
			}

			// 迭代求解
			int s = Integer.parseInt(in.readLine());
			maxd = new int[s + 2];
			mind = new int[s + 2];

			// 初始化赋值，标记未计算
			maxd[0] = mind[0] = 0;
			for (int i = 1; i < maxd.length; i++) {
				maxd[i] = Integer.MIN_VALUE;
				mind[i] = Integer.MAX_VALUE / 2;
			}

			// 递推
			// for (int i = 1; i <= s; i++) {
			// for (int j = 1; j <= n; j++) {
			// if (i >= v[j]) {
			// mind[i] = Math.min(mind[i], mind[i - v[j]] + 1);
			// maxd[i] = Math.max(maxd[i], maxd[i - v[j]] + 1);
			// }
			// }
			// }

			// 记录步骤的递推
			min_coin = new int[s + 1];
			max_coin = new int[s + 1];
			for (int i = 1; i <= s; i++) {
				for (int j = 1; j <= n; j++) {
					if (i >= v[j]) {
						if (mind[i] > mind[i - v[j]] + 1) {
							mind[i] = mind[i - v[j]] + 1;
							min_coin[i] = v[j];
						}
						if (maxd[i] < maxd[i - v[j]] + 1) {
							maxd[i] = maxd[i - v[j]] + 1;
							max_coin[i] = v[j];
						}
					}
				}
			}

			//输出步骤
			out.println(mind[s] + " " + maxd[s]);
			while (s > 0) {
				out.print(min_coin[s] + " ");
				s -= min_coin[s];
			}
			// print_ans(mind, s, n);
			// out.println();
			// print_ans(maxd, s, n);
			out.flush();

		}

	}
}
