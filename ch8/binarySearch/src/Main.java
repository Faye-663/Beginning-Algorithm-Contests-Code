import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * ���ֲ��ң����ҷ�Χ
 * 
 * �����������ҿ������ʹ�÷��������½纯����ʵ��ϸ�ڡ� [left,right)
 * 
 * ��left + (right - left) / 2��ȷ���ֽ�����ǿ���������㡣
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

	// ��v����ʱ���������ֵĵ�һ��λ�á���������ڣ���������һ���±�i���ڴ˴�����v��������Ȼ����
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

	// ��v����ʱ���������ֵ����һ��λ�õĺ���һ��������ڣ���������һ���±�i���ڴ˴�����v��������Ȼ����
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
			// ��������
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
