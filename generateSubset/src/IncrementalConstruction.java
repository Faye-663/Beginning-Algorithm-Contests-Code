import java.util.Scanner;

/**
 * ö��{1,2,3,...,n}�������Ӽ�
 * 
 * һ��ѡ��һ��Ԫ�طŵ�������
 * ʹ�ö���ļ��ɣ�����ͬһ������ö������
 * 2^n
 */

public class IncrementalConstruction {
	static int[] a = new int[100];

	/**
	 * 
	 * @param n
	 *            n��ֵ
	 * @param a
	 *            ��ǰ���ϣ���ȷ�����Ӽ�
	 * @param cur
	 *            ��Ҫ���Ԫ�ص�λ��
	 */
	private static void f(int n, int[] a, int cur) {
		// ��ӡ��ǰ����
		for (int i = 0; i < cur; i++) {
			System.out.print(a[i] + " ");
		}
		if (cur != 0) {
			System.out.println();
		}else{
			System.out.println("null");
		}

		// ȷ����ǰԪ�ص���С����ֵ
		int temp;
		if (cur == 0) {
			temp = 1;
		} else {
			temp = a[cur - 1] + 1;
		}

		
		for (int i = temp; i <= n; i++) {
			a[cur] = i;
			f(n, a, cur + 1);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			f(n, a, 0);
			System.out.println();
		}

	}

}
