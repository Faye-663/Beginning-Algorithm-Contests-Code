import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 * �ǿ��԰���ѡ�������˼�룬�ԴӴ�С��˳�����ΰ�ÿ�����ŵ���ȷ��λ�á�
 * ������
 * �ȷ��������棬Ȼ�󷭵���ȷ��λ�á�
 * �����ǰ��մӴ�С��˳�����������i��ļ��ʱ���ǲ���Ӱ�쵽��1, 2, 3,��, i-1��ļ���ģ������Ѿ���ȷ�ط����˼���ѵײ���i-1��λ���ϣ���
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
