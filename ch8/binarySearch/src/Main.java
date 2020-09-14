import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 二分查找，查找范围
 * 
 * 用心体会左闭右开区间的使用方法和上下界函数的实现细节。 [left,right)
 * 
 * 用left + (right - left) / 2来确保分界点总是靠近区间起点。
 * 
 */
public class Main {

	static int[] nums;

	private static int baseSearch(int[] nums, int left, int right, int v) {
		while (left < right) {
			int m = left + (right - left) / 2;
			if (nums[m] == v) {
				return m;
			} else if (nums[m] > v) {
				right = m;
			} else {
				left = m + 1;
			}
		}

		return -1;
	}

	// 当v存在时返回它出现的第一个位置。如果不存在，返回这样一个下标i：在此处插入v后序列仍然有序。
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

	// 当v存在时返回它出现的最后一个位置,如果不存在，返回这样一个下标i：在此处插入v后序列仍然有序。
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

		String s;
		while ((s = in.readLine()) != null) {
			// 处理输入
			String[] strs = s.split(" ");
			int n = strs.length;
			nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
			}

			int v = Integer.parseInt(in.readLine());

			int ans = baseSearch(nums, 0, n, v);
			out.println(ans);

			ans = lowerBound(nums, 0, n, v);
			out.println(ans);

			ans = upperBound(nums, 0, n, v);
			out.println(ans);
			out.flush();

		}
	}

}
