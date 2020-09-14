import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * 是可以按照选择排序的思想，以从大到小的顺序依次把每个数排到正确的位置。
 * 方法是
 * 先翻到最上面，然后翻到正确的位置。
 * 由于是按照从大到小的顺序处理，当处理第i大的煎饼时，是不会影响到第1, 2, 3,…, i-1大的煎饼的（它们已经正确地翻到了煎饼堆底部的i-1个位置上）。
 * @author 11495
 */
public class Main {

	static int[] nums;
	static ArrayList<Integer> indexs;

	private static void sort(int[] nums, int n, int cur) {
		if (n >= cur) {
			int num = nums[0];
			int index = 0;
			for (int i = 0; i < n - cur + 1; i++) {
				if (nums[i] > num) {
					num = nums[i];
					index = i;
				}
			}

			int[] temps = new int[n];
			if (index == n - cur) {
				sort(nums, n, cur + 1);
			} else if (index == 0) {
				for (int i = 0; i <= n - cur; i++) {
					temps[n - cur - i] = nums[i];
				}
				for (int i = n - 1; i > n - cur; i--) {
					temps[i] = nums[i];
				}
				indexs.add(cur);
				sort(temps, n, cur + 1);
			} else {
				for (int i = index; i >= 0; i--) {
					temps[index - i] = nums[i];
				}
				for (int i = index + 1; i < n; i++) {
					temps[i] = nums[i];
				}
				indexs.add(n - index);
				for (int i = 0; i <= n - cur; i++) {
					nums[n - cur - i] = temps[i];
				}
				indexs.add(cur);
				sort(nums, n, cur + 1);
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while ((s = in.readLine()) != null) {
			String[] strs = s.split(" ");
			int n = strs.length;
			nums = new int[n];
			indexs = new ArrayList();
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
			}

			for (int i = 0; i < n - 1; i++) {
				out.print(nums[i] + " ");
			}
			out.println(nums[n - 1]);

			sort(nums, n, 1);

			for (int i = 0; i < indexs.size(); i++) {
				out.print(indexs.get(i) + " ");
			}
			out.println(0);
			out.flush();
		}

	}

}
