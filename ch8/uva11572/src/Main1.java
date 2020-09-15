import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 用一个map求出last[i]，即下标i的“上一个相同元素的下标”。
 * 
 * Map 
 * O(nlogn)
 */
public class Main1 {

	static int[] ans, nums, last;
	static Map<Integer, Integer> cur;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int caseNo = Integer.parseInt(in.readLine());
		int aa = caseNo;
		ans = new int[caseNo];
		while (caseNo-- > 0) {
			int n = Integer.parseInt(in.readLine());
			nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(in.readLine());
			}

			// 从左至右，记录nums的各元素的最新位置
			cur = new HashMap<Integer, Integer>();
			// last[i]，记录nums[0~i]中nums[i]元素上一次出现的位置，用于判断是否可以继续向右延伸
			last = new int[n];
			for (int i = 0; i < n; i++) {
				if (cur.containsKey(nums[i])) {
					last[i] = cur.get(nums[i]);
				} else {
					last[i] = -1;
				}
				cur.put(nums[i], i);
			}

			int l = 0;
			int r = 0;
			int count = 0;
			// r<n
			while (r < n) {
				// 向右延伸，直至出现重复
				while (r < n && last[r] < l) {
					r++;
				}
				// 比较
				count = Math.max(count, r - l);
				// 移除nums[l],l++
				l++;

			}
			ans[caseNo] = count;

		}

		for (int i = aa - 1; i >= 0; i--) {
			out.println(ans[i]);
		}
		out.flush();
	}

}
