import java.util.Scanner;
import java.util.Stack;

public class Main {

	// ��Ҫ�жϵĳ�վ˳��
	private static int[] target;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			// �������
			if (n == 0) {
				break;
			}
			while (true) {
				int m = in.nextInt();
				// �������������һ��
				if (m == 0) {
					break;
				}
				target = new int[n + 1];
				target[1] = m;
				for (int i = 2; i <= n; i++) {
					int j = in.nextInt();
					target[i] = j;
				}

				// Cվ��ջ
				Stack<Integer> c = new Stack();
				// Aվ��վ���г����
				int a = 1;
				// ��Ҫ����Bվ�ĵ�b���г������г���ż�Ϊtarget[b]
				int b = 1;
				// �Ƿ�Ϸ���־
				boolean flag = true;

				while (b <= n) {
					// ��վ�г� == ��վ�г�
					if (a == target[b]) {
						// ��վ����վ
						a++;
						b++;
					// Cվ��ջ�� == ��վ�г�
					} else if (!c.empty() && c.peek() == target[b]) {
						c.pop();
						// δ��վ������վ
						b++;
					// ����Cվ��ջ
					} else if (a <= n) {
						// δ��վ������վ
						c.push(a++);
					// ��������������ϣ���վ�г����>n����˳�򲻺Ϸ�
					} else {
						flag = false;
						break;
					}
				}
				
				if (flag) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}

			}
			System.out.println();

		}

	}

}
