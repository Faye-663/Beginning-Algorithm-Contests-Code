import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	/**
	 * ����һ������Ϊn������A1, A2,��, An�������������
	 * 
	 * O(n^3)
	 * 
	 */

	static int[] nums;

	// ö�٣�O��n^3)
	private static int n3(int n, int[] nums) {
		// max = nums[0] ������ 0 ����ֹ����Ϊ��
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
	 * ����������֮�͵�������ǰ׺��֮��
	 * O(n^2)
	 */
	private static int n2(int n, int[] nums) {
		int[] s = new int[n + 1];

		// ����ǰ׺��S
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
	 * ���η��� 
	 * �������⣺�������ʵ�����ֳ������⡣ 
	 * �ݹ���⣺�ݹ��������⡣ 
	 * �ϲ����⣺�ϲ�������Ľ�õ�ԭ����Ľ⡣
	 * 
	 * �����֡����ǰ����зֳ�Ԫ�ظ���������ȵ����룻 
	 * ���ݹ���⡱���Ƿֱ������ȫλ����������ȫλ���Ұ��������У�
	 * ���ϲ�������������λ����롢�յ�λ���Ұ� ��������������У���������������Ž�Ƚϡ�
	 * 
	 * O(nlogn)
	 * 
	 * ϸ�ڣ� 
	 * ��Χ��ʾ�� ������ҿ���������ʾһ����Χ���ô����ڴ�������ָʱ�Ƚ���Ȼ������[x,y)���ֳɵ���[x,m)��[m,y)������Ҫ���κεط��Ӽ�1��
	 * ���ֳ�Ԫ�ظ���������ȵ����롱ʱ�ֽ��ļ��㣺 
	 * ��x+(y-x)/2��ȷ���ֽ�����ǿ���������㡣
	 */

	// ��������������ҿ�����[x,y)�е����������
	private static int nlogn(int x, int y, int[] nums) {
		if (y - x == 1) {
			return nums[x];
		}
		// ���ε�һ�������ֳ�[x, m)��[m, y)
		int m = x + (y - x) / 2;
		// ���εڶ������ݹ����
		int max = Math.max(nlogn(x, m, nums), nlogn(m, y, nums));

		// ���ε��������ϲ�(1)�����ӷֽ�㿪ʼ��������������l
		int v, l, r;
		v = 0;
		l = nums[m - 1];
		for (int i = m - 1; i >= x; i--) {
			v += nums[i];
			l = Math.max(l, v);
		}

		// ���ε��������ϲ�(2)�����ӷֽ�㿪ʼ���ҵ����������r
		v = 0;
		r = nums[m];
		for (int i = m; i < y; i++) {
			v += nums[i];
			r = Math.max(r, v);
		}
		// ��������Ľ���l��r�Ƚ�
		return Math.max(max, r + l);
	}

	// ��jȷ��ʱ����S[j+1]-S[i]����൱ �ڡ�S[i]��С�������ֻ��Ҫɨ��һ�����飬ά����Ŀǰ����������СS�����ɡ�
	// O(n)
	private static int n(int n, int[] nums) {
		int[] s = new int[n + 1];
		for (int i = 0; i < n; i++) {
			s[i + 1] = s[i] + nums[i];
		}

		int max = nums[0];
		// ��СS������
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