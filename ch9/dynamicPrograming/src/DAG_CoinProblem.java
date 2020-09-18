import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 有n种硬币，面值分别为V1, V2, …, Vn，每种都有无限多。 
 * 给定非负整数S，可以选用多少个硬币，使得面值之和恰好为S？
 * 输出硬币数目的最小值和最大值。
 * 
 * 则初始状态为S，目标状态为0。若当前在状态i，每使用一个硬币j，状态便转移到i－Vj
 * 
 * 固定起点、终点
 * 
 * d(i)表示从结点i出发到结点0的最长路径长度 d[i] = max{d[i-v[j]]+1}
 * 
 */
public class DAG_CoinProblem {

	// v,硬币面值
	// d[i]表示从结点i出发到结点0的最长(短)路径长度
	static int[] v, d;
	static String[] temp;

	private static int dp(int s, int n) {
		if (s == 0) {
			return 0;
		}
		int ans = d[s];
		// 已计算（可以用vis[]数组来记录是否计算过）
		if (ans != -1) {
			return ans;
		} else {
			// 赋最大(小）值，便于判断是否合法
			ans = Integer.MAX_VALUE / 2;
			// ans Integer.MIN_VALUE;
			for (int i = 1; i <= n; i++) {
				// 方法参数s为非负整数
				if (s - v[i] >= 0)
					ans = Math.min(ans, dp(s - v[i], n) + 1);
				// ans = Math.max(ans, dp(s - v[i], n) + 1);
			}
			return d[s] = ans;
		}

	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String str;
		while (!"0".equals(str = in.readLine())) {
			int n = Integer.parseInt(str);
			temp = in.readLine().split(" ");
			v = new int[n + 2];
			for (int i = 1; i <= n; i++) {
				v[i] = Integer.parseInt(temp[i - 1]);
			}

			int s = Integer.parseInt(in.readLine());
			d = new int[s + 2];
			// 初始化赋值为 -1 ，表示未计算过
			// 因为值可能为 0，所以赋值为-1
			for (int i = 0; i < d.length; i++) {
				d[i] = -1;
			}

			int ans = dp(s, n);
			// 判断是否合法
			// if (ans < 0) {
			if (ans >= Integer.MAX_VALUE / 2) {
				out.println("null");
			} else {
				out.println(ans);
			}
			out.flush();
		}

	}

}
