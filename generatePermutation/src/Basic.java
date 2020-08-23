import java.util.Scanner;

/**
 * ���ֵ����С�����˳�����1~n����������
 * 
 * �����������1��ͷ�����У���һ���ǵݹ���ã���Ȼ�������2��ͷ�����У����ǵݹ���ã�������...���...
 */
public class Basic {

	static int[] p = new int[100];

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			print_permutation(n, p, 0);

		}
	}

	/**
	 * 
	 * @param n
	 *            n��ֵ
	 * @param result
	 *            �Ѿ�ȷ���ġ�ǰ׺������
	 * @param cur
	 *            ��ǰ��Ҫȷ����Ԫ��λ��
	 */
	private static void print_permutation(int n, int[] result, int cur) {
		// �ݹ�߽�,�ݹ����
		if (cur == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
		} else {
			// ������result[j]�������������i(1~n)
			for (int i = 1; i <= n; i++) {
				boolean flag = true;
				for (int j = 0; j < cur; j++) {
					// ���i�Ѿ���result[0]~result[j-1]���ֹ� ������ѡ
					if (result[j] == i) {
						flag = false;
					}
				}
				if (flag) {
					result[cur] = i;
					print_permutation(n, result, cur + 1);
				}
			}
		}
	}

}
