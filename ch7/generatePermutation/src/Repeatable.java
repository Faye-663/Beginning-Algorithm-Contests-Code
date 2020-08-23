import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * ��������P�������ֵ����������A��Ԫ�ص�����ȫ����
 * 
 */

public class Repeatable {

	static int[] a = new int[100];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String s = in.nextLine();
			if ("0".equals(s)) {
				break;
			}

			String[] strs = s.split(" ");
			int[] p = new int[strs.length];
			for (int i = 0; i < strs.length; i++) {
				p[i] = Integer.parseInt(strs[i]);
			}

			// ��p����
			Arrays.sort(p);
			print_permutation(p.length, p, a, 0);

		}
	}

	/**
	 * 
	 * @param n
	 *            p�ĳ���
	 * @param p
	 *            p����
	 * @param result
	 *            �Ѿ�ȷ���ġ�ǰ׺������
	 * @param cur
	 *            ��ǰ��Ҫȷ����Ԫ��λ��
	 */
	private static void print_permutation(int n, int[] p, int[] result, int cur) {
		// �ݹ�߽�,�ݹ����
		if (cur == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		} else {
			//��result������p�����е���
			//ȷ��P[i]��֮ǰ��һԪ�ز���ͬ
			for (int i = 0; i < n && (i == 0 || p[i] != p[i - 1]); i++) {
				//cp��p��ֵΪp[i]�ĸ���
				//cp��a��ֵΪp[i]�ĸ���
				int cp = 0;
				int ca = 0;

				for (int j = 0; j < n; j++) {
					if (p[i] == p[j]) {
						cp++;
					}
				}
				for (int j = 0; j < cur; j++) {
					if (p[i] == result[j]) {
						ca++;
					}
				}

				if (cp > ca) {
					result[cur] = p[i];
					print_permutation(n, p, result, cur + 1);
				}
			}
		}
	}

}
