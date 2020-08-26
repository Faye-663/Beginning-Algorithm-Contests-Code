import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {

	// 存储输入的图的信息
	static int[][] nums;
	static int[] vis;
	// 存储结点在排列中的索引
	static int[] letters;
	// 存储排列
	static int[] text;
	// 当前最小带宽排列
	static int[] ans;
	// 各结点的字母
	static int[] set1;
	// 当前最小带宽
	static int curbd;

	private static void f(int n, int[] letters, int cur, int[] text, int[] set1) {
		if (n == cur) {
			// 该排列的最小带宽
			int thisbd = 0;
			// 剪枝标志
			boolean flag = true;
			for (int i = 0; i < 8; i++) {
				// 遍历结点结束
				if (nums[i][0] == 0) {
					break;
				}
				// 理想情况>min，剪枝
				if (curbd <= (nums[i][1] + 1) / 2) {
					thisbd = 9;
					break;
				}
				// 该结点在该排列的索引
				int first = nums[i][0] - 65;
				int index1 = letters[first];

				for (int j = 2; j < 9; j++) {
					// 结束该结点相邻结点的遍历
					if (nums[i][j] == 0) {
						break;
					}
					// 该相邻结点的索引
					int last = nums[i][j] - 65;
					int index2 = letters[last];
					// 带宽
					int bd = Math.abs(index1 - index2);
					// 出现带宽>min，则剪枝
					if (curbd < bd) {
						thisbd = 9;
						flag = false;
						break;
					} else {
						thisbd = Math.max(thisbd, bd);
					}
				}
				if (!flag) {
					break;
				}
			}
			if (curbd > thisbd) {
				curbd = thisbd;
				// 设置排列
				ans = Arrays.copyOf(text, n);
			}
		// 生成全排列
		} else
			for (int i = 0; i < n; i++) {
				if (vis[i] == 0) {
					// 设置结点在排列中的索引
					letters[set1[i] - 65] = cur;
					text[cur] = set1[i];
					vis[i] = 1;
					f(n, letters, cur + 1, text, set1);
					vis[i] = 0;
				}
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		String line;
		while ((line = in.readLine()) != null) {
			// 初始化
			HashSet<Character> set = new HashSet();
			nums = new int[10][10];
			ArrayList<Integer> set2 = new ArrayList();
			letters = new int[30];

			if ("#".equals(line)) {
				break;
			}

			// 处理输入
			String[] temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				String s = temp[i];
				// 各结点相邻结点的个数+1
				int num1 = 0;
				for (int j = 0; j < s.length(); j++) {
					if (j == 1) {
						continue;
					}
					nums[i][j] = (int) s.charAt(j);
					num1++;
					char c = s.charAt(j);
					if (!set.contains(c)) {
						// 获取各结点的字母
						set2.add((int) c);
					}
					// 获取结点个数
					set.add(s.charAt(j));
				}
				// 各结点相邻结点的个数
				nums[i][1] = num1 - 1;
			}

			int n = set.size();
			set1 = new int[n + 5];
			// 排序，以便按字典序输出
			Collections.sort(set2);
			for (int i = 0; i < n; i++) {
				set1[i] = set2.get(i);
			}
			// 初始化
			text = new int[n + 5];
			ans = new int[n + 5];
			vis = new int[n + 5];
			curbd = 10;
			f(n, letters, 0, text, set1);
			// 输出
			for (int i = 0; i < n; i++) {
				out.print((char) ans[i] + " ");
			}
			out.println("-> " + curbd);
			out.flush();

		}

	}

}
