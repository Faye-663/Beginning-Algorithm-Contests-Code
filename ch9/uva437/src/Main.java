import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * DAG 最长路径
 * 
 * d(i,j) = max{d(k,l)+nums[i][j]}
 * 
 * O(n^2)
 */
public class Main {

	// 立方体长宽高
	static int[][] nums;
	// d[i][j]: 第i个立方体，以nums[i][j]做高。以该立方体为顶的最大高度。
	static int[][] d;

	private static int dp(int i, int j, int n) {
		// 已计算
		if (d[i][j] != 0) {
			return d[i][j];
		}

		// d[i][j]的长宽
		int[] xy1 = new int[2];
		int index = 0;
		for (int k = 0; k < 3; k++) {
			if (k == j) {
				continue;
			}
			xy1[index++] = nums[i][k];
		}

		int ans = nums[i][j];
		int[] xy2;
		for (int k = 0; k < n; k++) {
			for (int l = 0; l < 3; l++) {
				// 长宽
				xy2 = new int[2];
				index = 0;
				for (int m = 0; m < 3; m++) {
					if (m == l) {
						continue;
					}
					xy2[index++] = nums[k][m];
				}

				if ((xy1[0] < xy2[0] && xy1[1] < xy2[1])
						|| (xy1[1] < xy2[0] && xy1[0] < xy2[1])) {
					ans = Math.max(ans, dp(k, l, n) + nums[i][j]);
				}
			}
		}
		d[i][j] = ans;
		return ans;
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		int caseNo = 1;
		while (!"0".equals(s = in.readLine())) {
			// 处理输入
			int n = Integer.parseInt(s);
			nums = new int[n][3];
			d = new int[n][3];
			String[] strs;
			for (int i = 0; i < n; i++) {
				strs = in.readLine().split(" ");
				nums[i][0] = Integer.parseInt(strs[0]);
				nums[i][1] = Integer.parseInt(strs[1]);
				nums[i][2] = Integer.parseInt(strs[2]);
			}

			// dp
			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					ans = Math.max(ans, dp(i, j, n));
				}
			}

			// 输出
			out.println("Case " + caseNo++ + ": maximum height = " + ans);
			out.flush();

		}
	}

}
