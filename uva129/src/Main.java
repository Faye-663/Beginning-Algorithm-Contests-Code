import java.util.Scanner;

public class Main {

	static int l;
	// ������
	static int tot;
	// �Ƿ���������
	static boolean flag;

	private static void f(int n, int[] a, int cur) {
		if (tot == n) {
			// ��λ������
			int temp = 1;
			// ���м�����
			int num = 1;

			for (int i = 0; i < cur; i++) {
				// �Ƿ��ӡ�ո�
				if (temp == 5) {
					// �Ƿ���
					if (++num == 17) {
						System.out.println();
						System.out.print((char) a[i]);
						temp = 1;
						num = 1;
					} else {
						System.out.print(" " + (char) a[i]);
						temp = 1;
					}
				} else {
					System.out.print((char) a[i]);
				}
				temp++;
			}
			System.out.println();
			System.out.println(cur);

			// ��������ɣ������������
			flag = true;
			return;
		}

		for (int i = 65; i < 65 + l; i++) {
			// ���������ֱ���˳�
			if (flag) {
				return;
			}

			a[cur] = i;
			if (isHard(a, cur)) {
				tot++;
				f(n, a, cur + 1);
			}
		}
	}

	//���ں�׺i����һλ���ȣ����ú�׺i����
	//���ڸô�����һ����׺i��ȣ����ô�easy
	private static boolean isHard(int[] a, int cur) {
		// �Ƿ�Ϊhard��־
		boolean ok = true;
		// ���Գ���Ϊ2*i�ĺ�׺
		for (int i = 1; 2 * i <= cur + 1; i++) {
			// ����i�ĺ�׺�Ƿ�����
			boolean equal = true;
			// ������׺i
			for (int j = 0; j < i; j++) {
				// ��һλ���ȣ�����break��������һ����׺i
				if (a[cur - j] != a[cur - j - i]) {
					equal = false;
					break;
				}
			}
			// ��һ����׺i��ȣ�����Ϊhard
			if (equal) {
				ok = false;
				break;
			}
		}
		return ok;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			l = in.nextInt();
			if (n == 0 && l == 0) {
				break;
			}

			int[] a = new int[81];
			tot = 0;
			flag = false;
			f(n, a, 0);

		}

	}

}
