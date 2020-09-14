import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	/**
	 * 给出一个长度为n的序列A1, A2,…, An，求最大连续和
	 * 
	 * O(n^3)
	 * 
	 */

	static int[] nums;

	// 枚举，O（n^3)
	private static int n3(int n, int[] nums) {
		// max = nums[0] 而不是 0 ，防止最大和为负
		int max = nums[0];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += nums[k];
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	/**
	 * s[i] = nums[0]+nums[1]+...+nums[i-1] 
	 * num[i]+nums[i+1]+...+nums[j] = s[j+1]-s[i]
	 * 连续子序列之和等于两个前缀和之差
	 * O(n^2)
	 */
	private static int n2(int n, int[] nums) {
		int[] s = new int[n + 1];

		// 递推前缀和S
		for (int i = 0; i < n; i++) {
			s[i + 1] = s[i] + nums[i];
		}

		int max = nums[0];
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				int sum = s[j] - s[i - 1];
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	/**
	 * 分治法： 
	 * 划分问题：把问题的实例划分成子问题。 
	 * 递归求解：递归解决子问题。 
	 * 合并问题：合并子问题的解得到原问题的解。
	 * 
	 * “划分”就是把序列分成元素个数尽量相等的两半； 
	 * “递归求解”就是分别求出完全位于左半或者完全位于右半的最佳序列；
	 * “合并”就是求出起点位于左半、终点位于右半 的最大连续和序列，并和子问题的最优解比较。
	 * 
	 * O(nlogn)
	 * 
	 * 细节： 
	 * 范围表示： 用左闭右开区间来表示一个范围，好处是在处理“数组分割”时比较自然：区间[x,y)被分成的是[x,m)和[m,y)，不需要在任何地方加减1。
	 * “分成元素个数尽量相等的两半”时分界点的计算： 
	 * 用x+(y-x)/2来确保分界点总是靠近区间起点。
	 */

	// 返回数组在左闭右开区间[x,y)中的最大连续和
	private static int nlogn(int x, int y, int[] nums) {
		if (y - x == 1) {
			return nums[x];
		}
		// 分治第一步：划分成[x, m)和[m, y)
		int m = x + (y - x) / 2;
		// 分治第二步：递归求解
		int max = Math.max(nlogn(x, m, nums), nlogn(m, y, nums));

		// 分治第三步：合并(1)——从分界点开始往左的最大连续和l
		int v, l, r;
		v = 0;
		l = nums[m - 1];
		for (int i = m - 1; i >= x; i--) {
			v += nums[i];
			l = Math.max(l, v);
		}

		// 分治第三步：合并(2)——从分界点开始往右的最大连续和r
		v = 0;
		r = nums[m];
		for (int i = m; i < y; i++) {
			v += nums[i];
			r = Math.max(r, v);
		}
		// 把子问题的解与l和r比较
		return Math.max(max, r + l);
	}

	// 当j确定时，“S[j+1]-S[i]最大”相当 于“S[i]最小”，因此只需要扫描一次数组，维护“目前遇到过的最小S”即可。
	// O(n)
	private static int n(int n, int[] nums) {
		int[] s = new int[n + 1];
		for (int i = 0; i < n; i++) {
			s[i + 1] = s[i] + nums[i];
		}

		int max = nums[0];
		// 最小S的索引
		int mins = 1;
		for (int i = 1; i <= n; i++) {
			if (s[i] < mins) {
				mins = i;
			}
			max = Math.max(max, s[i] - s[mins - 1]);
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while ((s = in.readLine()) != null) {
			String[] strs = s.split(" ");
			int n = strs.length;
			nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
			}

			long start, end;
			int max;

			// n^3
			start = System.currentTimeMillis();
			max = n3(n, nums);
			end = System.currentTimeMillis();
			out.write(max + "\n");
			out.println("n^3: " + (end - start));
			out.flush();

			// n^2
			start = System.currentTimeMillis();
			max = n2(n, nums);
			end = System.currentTimeMillis();
			out.write(max + "\n");
			out.println("n^2: " + (end - start));
			out.flush();

			// nlogn
			start = System.currentTimeMillis();
			max = nlogn(0, n, nums);
			end = System.currentTimeMillis();
			out.write(max + "\n");
			out.println("nlogn: " + (end - start));
			out.flush();

			// n
			start = System.currentTimeMillis();
			max = n(n, nums);
			end = System.currentTimeMillis();
			out.write(max + "\n");
			out.println("n: " + (end - start));
			out.flush();
		}

	}
}