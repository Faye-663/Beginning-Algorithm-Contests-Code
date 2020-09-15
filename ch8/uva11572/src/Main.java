import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 可以从R=0开始不断增加R，相当于把所求序列的右端点往右延伸。 
 * 当无法延伸（即A[R+1]在子序列A[L～R]中出现过）时，只需增大L，并且继续延伸R。
 * 既然当前的A[L～R]是可行解，L增大之后必然还是可行解，所以不必减少R，继续增大即可。
 * 
 * Set
 * O(nlogn)
 */
public class Main {

	static int[] ans, nums;
	static Set<Integer> check;

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

			int l = 0;
			int r = 0;
			check = new HashSet<Integer>();
			int count = 0;
			// r<n
			while (r < n) {
				// 向右延伸，直至出现重复
				while (r < n && !check.contains(nums[r])) {
					check.add(nums[r++]);
				}
				// 比较
				count = Math.max(count, r - l);
				// 移除nums[l],l++
				check.remove(nums[l++]);

			}
			ans[caseNo] = count;

		}

		for (int i = aa - 1; i >= 0; i--) {
			out.println(ans[i]);
		}
		out.flush();

	}

}
