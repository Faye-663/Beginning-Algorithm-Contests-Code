import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	static int[] nums;
	static int[] temp;
	static int[] nums1;

	/**
	 * 归并排序 
	 * 划分问题：把序列分成元素个数尽量相等的两半。 
	 * 递归求解：把两半元素分别排序。 
	 * 合并问题：把两个有序表合并成一个。 O(nlogn)
	 * 
	 * @param temp
	 *            临时存放合并后的结果
	 * 
	 *逆序对问题 
	 *分治三步法
	 * 
	 *由于合并操作是从小到大进行的，当右边的nums[j]复制到temp中时，
	 *左边还没来得及复制到temp的那些数就是左边所有比nums[j]大的数。 
	 *此时在累加器中加上左边元素个数m-p即可。
	 */
	private static int mergeSort(int[] nums, int x, int y, int[] temp) {
		if (y - x > 1) {
			// 划分
			int m = x + (y - x) / 2;
			// 递归求解
			int a = mergeSort(nums, x, m, temp);
			int b = mergeSort(nums, m, y, temp);

			// 合并
			int p = x;
			int q = m;
			int i = x;
			int tot = 0;
			while (p < m || q < y) {
				if (q >= y) {
					temp[i++] = nums[p++];
				} else if (p >= m) {
					temp[i++] = nums[q++];
					tot += m - p;
				} else if (nums[p] <= nums[q]) {
					temp[i++] = nums[p++];
				} else {
					temp[i++] = nums[q++];
					tot += m - p;
				}
			}

			for (i = x; i < y; i++) {
				nums[i] = temp[i];
			}
			return a + b + tot;
		}
		return 0;
	}

	/**
	 * 划分问题：把数组的各个元素重排后分成左右两部分，使得左边的任意元素都小于或等于右边的任意元素。 
	 * 递归求解：把左右两部分分别排序。
	 * 合并问题：不用合并，因为此时数组已经完全有序。 
	 * o(nlogn)
	 * 
	 * 
	 * 快速选择问题。输入n个整数和一个正整数k（1≤k≤n），输出这些整数从小到大排序后的第k个（例如，k=1就是最小值）。
	 * 
	 * 假设在快速排序的“划分”结束后，数组A[p…r]被分成了A[p…q]和A[q+1…r]，则可以根据左边的元素个数q-p+1和k的大小关系只在左边或者右边递归求解。
	 */
	private static void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			// 选取比较标志，左边<= temp, 右边>temp
			int mid = nums[left];
			int i = left;
			int j = right - 1;

			// 需要全程保证i<j
			while (i < j) {
				while (i < j && nums[j] > mid) {
					j--;
				}
				while (i < j && nums[i] <= mid) {
					i++;
				}
				if (i < j) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}

			// 将首元素与中间（比较停止）元素互换
			nums[left] = nums[i];
			nums[i] = mid;

			// 递归时，不能带上nums[i]，即比较标志元素
			quickSort(nums, left, i);
			quickSort(nums, i + 1, right);
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
			nums1 = new int[n];
			temp = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
				nums1[i] = Integer.parseInt(strs[i]);
			}

			// 归并排序 逆序对
			int tot = mergeSort(nums, 0, n, temp);
			n = nums.length;
			out.println("归并排序：");
			for (int i = 0; i < n - 1; i++) {
				out.print(nums[i] + " ");
			}
			out.print(nums[n - 1] + "\n");
			out.println(tot);

			// 快速排序
			quickSort(nums1, 0, n);
			out.println("快速排序：");
			for (int i = 0; i < n - 1; i++) {
				out.print(nums1[i] + " ");
			}
			out.print(nums[n - 1] + "\n");
			out.flush();
		}

	}

}
