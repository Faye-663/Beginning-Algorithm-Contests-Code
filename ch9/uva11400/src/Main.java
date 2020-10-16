import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

/**
 * n种灯泡 V：电压 K：电压费用 C：灯单价 L：灯个数
 * 
 * 对于灯泡a1和a2，a1可以被a2替换的条件是：1) v2>v1 2)a2一定存在于最优解中(保证K2不被省去) 3)C2*L1 - C1*L1 -K1<0
 * 
 * 假设前i－1个灯泡经过最优的替换后灯泡序列为a1，……，aj，aj＋1，……ak（电压非降序排列）
 * 经分析aj和aj＋1的关系一定满足：（1）Cj＋1*Lj－Cj*Lj－Kj>0 
 * 现在我们考虑用灯泡 ai替换的情况，假设bj可以被ai替换，那么有（2）Ci*Lj－Cj*Lj－Kj<0 
 * 由不等式（1）（2）可知 Ci < Cj＋1，即Ci*Lj＋1－Cj＋1*Lj＋1－Kj+1<0, bj＋1一定可以被ai替换， 
 * 换句话说，在i－1个灯泡的最优替换序列a1～aj～bk中，如果aj＋1可以被ai替换，那么aj+1～ak的所有灯泡都可以被ai替换；
 * a1～aj个灯泡的最小费用已经算出为d[j]。这样我们就得出了ai的替换方法：
 * 前j个灯泡用之前计算出的最优方案d[j]购买，剩下j＋1～i个灯泡全用ai替换
 * 
 * d[j]=min{d[j]+c[i]*(s[i]-s[j])+k[i]}
 */
public class Main {

	static int[][] nums;
	static int[] d, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while (!"0".equals(s = in.readLine())) {
			// 输入
			int n = Integer.parseInt(s);
			nums = new int[n][4];
			d = new int[n + 1];
			sum = new int[n + 1];
			for (int i = 0; i < n; i++) {
				String[] strs = in.readLine().split(" ");
				for (int j = 0; j < 4; j++) {
					nums[i][j] = Integer.parseInt(strs[j]);
				}
			}
			// 二维数组排序
			Arrays.sort(nums, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			// s[i] = 前i种灯个数和
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					sum[i + 1] += nums[j][3];
				}
			}

			// d[i] = 前i种灯的最优解
			d[1] = nums[0][3] * nums[0][2] + nums[0][1];
			for (int i = 2; i <= n; i++) {
				d[i] = Integer.MAX_VALUE;
				for (int j = 0; j < i; j++) {
					d[i] = Math.min(d[i], d[j] + (sum[i] - sum[j])
							* nums[i - 1][2] + nums[i - 1][1]);
				}
			}

			out.println(d[n]);
			out.flush();
		}

	}

}
