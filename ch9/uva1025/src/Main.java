import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	static int[] t;
	static int[] m1t, m2t;
	// has_train[t][i][0]表示时刻t，在车站i是否有往右开的火车
	// has_train[t][i][1]记录的是往左开的火车。
	static int[][][] has_train;
	static int[][] d;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		int caseNo = 1;
		while (!"0".equals(s = in.readLine())) {
			// 处理输入
			int n = Integer.parseInt(s);
			int T = Integer.parseInt(in.readLine());
			t = new int[n + 1];
			d = new int[T + 1][n + 1];
			has_train = new int[T + 1000][n + 1][2];
			String[] strs = in.readLine().split(" ");
			for (int i = 1; i < n; i++) {
				t[i] = Integer.parseInt(strs[i - 1]);
			}
			int m1 = Integer.parseInt(in.readLine());
			m1t = new int[m1];
			strs = in.readLine().split(" ");
			for (int i = 0; i < m1; i++) {
				m1t[i] = Integer.parseInt(strs[i]);
			}
			int m2 = Integer.parseInt(in.readLine());
			m2t = new int[m2];
			strs = in.readLine().split(" ");
			for (int i = 0; i < m2; i++) {
				m2t[i] = Integer.parseInt(strs[i]);
			}

			// 处理has_train
			// 对于每一站，看每列火车到的时间
			int temp = 0;
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j < m1; j++) {
					has_train[m1t[j] + temp][i][0] = 1;
				}
				temp += t[i];
			}
			temp = 0;
			for (int i = n; i >= 1; i--) {
				for (int j = 0; j < m2; j++) {
					has_train[m2t[j] + temp][i][1] = 1;
				}
				temp += t[i - 1];
			}

			// 初始化赋值
			// d[T][i]（i不等于n）为极大值
			for (int i = 1; i <= n; i++) {
				d[T][i] = Integer.MAX_VALUE / 2;
			}
			// 边界条件:d[T][n]=0
			d[T][n] = 0;

			// d[i][j]表示时刻i，你在车站j，最少还需要等待多长时间。
			// d[i][j] = min{d[i+1][j]+1,d[i+t[j-1]][j-1],d[i+t[j]][j+1]}
			// 决策1：等1分钟。 决策2：搭乘往右开的车（如果有）。 决策3：搭乘往左开的车（如果有）。
			for (int i = T - 1; i >= 0; i--) {
				for (int j = 1; j <= n; j++) {
					d[i][j] = d[i + 1][j] + 1;
					if (j < n && has_train[i][j][0] == 1 && i + t[j] <= T) {
						d[i][j] = Math.min(d[i][j], d[i + t[j]][j + 1]);
					}
					if (j > 1 && has_train[i][j][1] == 1 && i + t[j - 1] <= T) {
						d[i][j] = Math.min(d[i][j], d[i + t[j - 1]][j - 1]);
					}
				}
			}

			// 输出
			if (d[0][1] >= Integer.MAX_VALUE / 2) {
				out.println("Case Number " + caseNo++ + ": impossible");
			} else {
				out.println("Case Number " + caseNo++ + ": " + d[0][1]);
			}
			out.flush();

		}

	}

}
