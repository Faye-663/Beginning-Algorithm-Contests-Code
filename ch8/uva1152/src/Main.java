import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

	static int[] a, b, c, d, left, right, ans;

	private static int baseSearch(int[] nums, int left, int right, int v) {
		while (left < right) {
			int m = left + (right - left) / 2;
			if (nums[m] == v) {
				// 找到，再得到左边界和右边界,返回找到的个数
				int low = lowerBound(nums, left, right, v);
				int upper = upperBound(nums, left, right, v);
				return upper - low + 1;
			} else if (nums[m] > v) {
				right = m;
			} else {
				left = m + 1;
			}
		}

		return 0;
	}

	private static int lowerBound(int[] nums, int left, int right, int v) {
		while (left < right) {
			int m = left + (right - left) / 2;
			if (nums[m] >= v) {
				right = m;
			} else {
				left = m + 1;
			}
		}
		return left;
	}

	private static int upperBound(int[] nums, int left, int right, int v) {
		boolean flag = false;
		while (left < right) {

			int m = left + (right - left) / 2;
			if (nums[m] < v) {
				left = m + 1;
			} else if (nums[m] == v) {
				left = m + 1;
				flag = true;
			} else {
				right = m;
			}
		}
		if (flag) {
			return left - 1;
		} else {
			return left;
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int caseNo = Integer.parseInt(in.readLine());
		ans = new int[caseNo];
		while (caseNo-- > 0) {
			in.readLine();
			int n = Integer.parseInt(in.readLine());
			a = new int[n];
			b = new int[n];
			c = new int[n];
			d = new int[n];
			left = new int[n * n];
			right = new int[n * n];
			for (int i = 0; i < n; i++) {
				String[] strs = in.readLine().split(" ");
				a[i] = Integer.parseInt(strs[0]);
				b[i] = Integer.parseInt(strs[1]);
				c[i] = Integer.parseInt(strs[2]);
				d[i] = Integer.parseInt(strs[3]);
			}

			// 得到a+b
			int index = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					left[index++] = a[i] + b[j];
				}
			}
			// 得到-c-d
			index = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					right[index++] = -c[i] - d[j];
				}
			}

			// 排序
			Arrays.sort(left);
			Arrays.sort(right);

			// 在right中二分查找left中的元素
			int sum = 0;
			for (int i = 0; i < n * n; i++) {
				sum += baseSearch(right, 0, n * n, left[i]);
			}

			ans[caseNo] = sum;

		}

		for (int i = ans.length - 1; i > 0; i--) {
			out.println(ans[i]);
			out.println();
		}
		out.println(ans[0]);
		out.flush();
	}

}
