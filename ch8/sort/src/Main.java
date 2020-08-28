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
	 * �鲢���� 
	 * �������⣺�����зֳ�Ԫ�ظ���������ȵ����롣 
	 * �ݹ���⣺������Ԫ�طֱ����� 
	 * �ϲ����⣺�����������ϲ���һ���� O(nlogn)
	 * 
	 * @param temp
	 *            ��ʱ��źϲ���Ľ��
	 * 
	 *��������� 
	 *����������
	 * 
	 *���ںϲ������Ǵ�С������еģ����ұߵ�nums[j]���Ƶ�temp��ʱ��
	 *��߻�û���ü����Ƶ�temp����Щ������������б�nums[j]������� 
	 *��ʱ���ۼ����м������Ԫ�ظ���m-p���ɡ�
	 */
	private static int mergeSort(int[] nums, int x, int y, int[] temp) {
		if (y - x > 1) {
			// ����
			int m = x + (y - x) / 2;
			// �ݹ����
			int a = mergeSort(nums, x, m, temp);
			int b = mergeSort(nums, m, y, temp);

			// �ϲ�
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
	 * �������⣺������ĸ���Ԫ�����ź�ֳ����������֣�ʹ����ߵ�����Ԫ�ض�С�ڻ�����ұߵ�����Ԫ�ء� 
	 * �ݹ���⣺�����������ֱַ�����
	 * �ϲ����⣺���úϲ�����Ϊ��ʱ�����Ѿ���ȫ���� 
	 * o(nlogn)
	 * 
	 * 
	 * ����ѡ�����⡣����n��������һ��������k��1��k��n���������Щ������С���������ĵ�k�������磬k=1������Сֵ����
	 * 
	 * �����ڿ�������ġ����֡�����������A[p��r]���ֳ���A[p��q]��A[q+1��r]������Ը�����ߵ�Ԫ�ظ���q-p+1��k�Ĵ�С��ϵֻ����߻����ұߵݹ���⡣
	 */
	private static void quickSort(int[] nums, int left, int right) {
		if (left < right) {
			// ѡȡ�Ƚϱ�־�����<= temp, �ұ�>temp
			int mid = nums[left];
			int i = left;
			int j = right - 1;

			// ��Ҫȫ�̱�֤i<j
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

			// ����Ԫ�����м䣨�Ƚ�ֹͣ��Ԫ�ػ���
			nums[left] = nums[i];
			nums[i] = mid;

			// �ݹ�ʱ�����ܴ���nums[i]�����Ƚϱ�־Ԫ��
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
			// ��������
			String[] strs = s.split(" ");
			int n = strs.length;
			nums = new int[n];
			nums1 = new int[n];
			temp = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
				nums1[i] = Integer.parseInt(strs[i]);
			}

			// �鲢���� �����
			int tot = mergeSort(nums, 0, n, temp);
			n = nums.length;
			out.println("�鲢����");
			for (int i = 0; i < n - 1; i++) {
				out.print(nums[i] + " ");
			}
			out.print(nums[n - 1] + "\n");
			out.println(tot);

			// ��������
			quickSort(nums1, 0, n);
			out.println("��������");
			for (int i = 0; i < n - 1; i++) {
				out.print(nums1[i] + " ");
			}
			out.print(nums[n - 1] + "\n");
			out.flush();
		}

	}

}
