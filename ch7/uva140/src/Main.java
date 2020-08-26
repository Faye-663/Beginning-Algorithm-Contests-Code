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

	// �洢�����ͼ����Ϣ
	static int[][] nums;
	static int[] vis;
	// �洢����������е�����
	static int[] letters;
	// �洢����
	static int[] text;
	// ��ǰ��С��������
	static int[] ans;
	// ��������ĸ
	static int[] set1;
	// ��ǰ��С����
	static int curbd;

	private static void f(int n, int[] letters, int cur, int[] text, int[] set1) {
		if (n == cur) {
			// �����е���С����
			int thisbd = 0;
			// ��֦��־
			boolean flag = true;
			for (int i = 0; i < 8; i++) {
				// ����������
				if (nums[i][0] == 0) {
					break;
				}
				// �������>min����֦
				if (curbd <= (nums[i][1] + 1) / 2) {
					thisbd = 9;
					break;
				}
				// �ý���ڸ����е�����
				int first = nums[i][0] - 65;
				int index1 = letters[first];

				for (int j = 2; j < 9; j++) {
					// �����ý�����ڽ��ı���
					if (nums[i][j] == 0) {
						break;
					}
					// �����ڽ�������
					int last = nums[i][j] - 65;
					int index2 = letters[last];
					// ����
					int bd = Math.abs(index1 - index2);
					// ���ִ���>min�����֦
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
				// ��������
				ans = Arrays.copyOf(text, n);
			}
		// ����ȫ����
		} else
			for (int i = 0; i < n; i++) {
				if (vis[i] == 0) {
					// ���ý���������е�����
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
			// ��ʼ��
			HashSet<Character> set = new HashSet();
			nums = new int[10][10];
			ArrayList<Integer> set2 = new ArrayList();
			letters = new int[30];

			if ("#".equals(line)) {
				break;
			}

			// ��������
			String[] temp = line.split(";");
			for (int i = 0; i < temp.length; i++) {
				String s = temp[i];
				// ��������ڽ��ĸ���+1
				int num1 = 0;
				for (int j = 0; j < s.length(); j++) {
					if (j == 1) {
						continue;
					}
					nums[i][j] = (int) s.charAt(j);
					num1++;
					char c = s.charAt(j);
					if (!set.contains(c)) {
						// ��ȡ��������ĸ
						set2.add((int) c);
					}
					// ��ȡ������
					set.add(s.charAt(j));
				}
				// ��������ڽ��ĸ���
				nums[i][1] = num1 - 1;
			}

			int n = set.size();
			set1 = new int[n + 5];
			// �����Ա㰴�ֵ������
			Collections.sort(set2);
			for (int i = 0; i < n; i++) {
				set1[i] = set2.get(i);
			}
			// ��ʼ��
			text = new int[n + 5];
			ans = new int[n + 5];
			vis = new int[n + 5];
			curbd = 10;
			f(n, letters, 0, text, set1);
			// ���
			for (int i = 0; i < n; i++) {
				out.print((char) ans[i] + " ");
			}
			out.println("-> " + curbd);
			out.flush();

		}

	}

}
