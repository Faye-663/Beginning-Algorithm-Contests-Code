import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 嵌套矩形问题。 
 * 有n(n>0)个矩形，每个矩形可以用两个整数a、b描述，表示它的长和宽。
 * 你的任务是选出尽量多的矩形排成一行，使得除了最后一个之外，每一个矩形都可以嵌套在下一个矩形内。
 * 如果有多解，矩形编号的字典序应尽量小。
 * 
 * 如果矩形X可以嵌套在矩形Y里，就从X到Y连一条有向边。这个有向图是无环的，因为一个矩形无法直接或间接地嵌套在自己内部。
 * 换句话说，它是一个DAG。所要求的便是DAG上 的最长路径。
 * 
 * d(i)表示从结点i出发的最长路长度 d(i) = max{d(j)+1 |(i,j) in E}, E为边集
 */
public class DAG_NestedRectangle {

	// d[i]表示从结点i出发的最长路径的长度
	static int[] d;
	// g为邻接矩阵
	// nums存储各矩阵信息
	static int[][] g, nums;

	private static int dp(int i, int n) {
		int ans = d[i];
		// 已计算，直接返回
		if (ans > 0) {
			return ans;
		}

		ans = 1;
		for (int j = 0; j < n; j++) {
			if (g[i][j] == 1) {
				ans = Math.max(ans, dp(j, n) + 1);
			}
		}
		return d[i] = ans;
	}

	// 如果有多个最优解，矩形编号的字典序应最小，选择最大d[i]所对应的i
	private static void print_ans(int i, int n) {
		System.out.printf("%d ", i);
		for (int j = 0; j < n; j++) {
			if (g[i][j] == 1 && d[i] == d[j] + 1) {
				print_ans(j, n);
				break;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while (!"0".equals(s = in.readLine())) {
			int n = Integer.parseInt(s);
			d = new int[n + 2];
			g = new int[n + 2][n + 2];
			nums = new int[n + 2][2];
			for (int i = 0; i < n; i++) {
				String[] strs = in.readLine().split(" ");
				nums[i][0] = Integer.parseInt(strs[0]);
				nums[i][1] = Integer.parseInt(strs[1]);
			}
			// 生成邻接矩阵
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if ((nums[i][0] < nums[j][0] && nums[i][1] < nums[j][1])
							|| (nums[i][0] < nums[j][1] && nums[i][1] < nums[j][0])) {
						g[i][j] = 1;
					}
				}
			}

			int ans = dp(0, n);
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (d[i] > ans) {
					ans = d[i];
					index = i;
				}
			}
			out.println(ans);
			out.println();
			out.flush();
			print_ans(index, n);

		}

	}

}
