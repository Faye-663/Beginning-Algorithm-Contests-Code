import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * d(i,j) = min{d(i,j+1), d((i-1+m)%m,j+1), d((i+1)%m,j+1)} + nums(i,j)
 * 
 */
public class Main {

	static int[][] d, nums;
	static int[] indexs;

	public static void main(String[] args) throws IOException {

		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		Scanner in1 = new Scanner(System.in);

		while (in1.hasNext()) {
			// 输入
			int m = in1.nextInt();
			int n = in1.nextInt();
			d = new int[m][n];
			nums = new int[m][n];
			indexs = new int[n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					nums[i][j] = in1.nextInt();
				}
			}

			// 初始化d
			for (int i = 0; i < m; i++) {
				d[i][n - 1] = nums[i][n - 1];
			}

			// 逆推
			for (int j = n - 2; j >= 0; j--) {
				for (int i = 0; i < m; i++) {
					if (d[(i - 1 + m) % m][j + 1] <= d[i][j + 1]
							&& d[(i - 1 + m) % m][j + 1] <= d[(i + 1 + m) % m][j + 1]) {
						d[i][j] = d[(i - 1 + m) % m][j + 1] + nums[i][j];
					} else if (d[i][j + 1] <= d[(i - 1 + m) % m][j + 1]
							&& d[i][j + 1] <= d[(i + 1 + m) % m][j + 1]) {
						d[i][j] = d[i][j + 1] + nums[i][j];
					} else {
						d[i][j] = d[(i + 1 + m) % m][j + 1] + nums[i][j];
					}
				}
			}

			int ans = d[0][0];
			int min_index = 0;
			for (int i = 1; i < m; i++) {
				if (d[i][0] < ans) {
					ans = d[i][0];
					min_index = i;
				}
			}

			// 字典序最小
			indexs[0] = min_index;
			int aaa = 0;
			// 使用字符串数组 10<2
			String[] bbb = new String[3];
			for (int i = 0; i < n - 1; i++) {
				for (int j = -1; j <= 1; j++) {
					aaa = (min_index + j + m) % m;
					if (d[aaa][i + 1] == d[min_index][i] - nums[min_index][i]) {
						bbb[j + 1] = String.valueOf(aaa);
					} else {
						bbb[j + 1] = "9999";
					}
				}
				Arrays.sort(bbb);
				min_index = Integer.parseInt(bbb[0]);
				indexs[i + 1] = min_index;
			}

			// 输出
			for (int i = 0; i < n - 1; i++) {
				out.print(indexs[i] + 1 + " ");
			}
			out.println(indexs[n - 1] + 1);
			out.println(ans);
			out.flush();
		}
	}

}
