import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 考虑最左边的村庄。如果需要买酒，即a1>0，则一定有劳动力从村庄2往左运给村庄1，
 * 而不管这些酒是从哪里来的（可能就是村庄2产的，也可能是更右边的村庄运到村庄2的）。 
 * 这样，问题就等价于只有村庄2～n，且第2个村庄的需求为a1+a2的情形。
 * 不难发现，ai<0时 这个推理也成立（劳动力同样需要|ai|个单位）
 * @author 11495
 */
public class Main {

	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while (!"0".equals(s = in.readLine())) {
			int n = Integer.parseInt(s);
			nums = new int[n];
			String[] strs = in.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
			}

			long count = 0L;
			for (int i = 0; i < n - 1; i++) {
				count += Math.abs(nums[i]);
				nums[i + 1] = nums[i + 1] + nums[i];
			}
			out.println(count);
			out.flush();
		}
	}

}
