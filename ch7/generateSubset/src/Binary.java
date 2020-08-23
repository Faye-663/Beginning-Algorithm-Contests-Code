import java.util.Scanner;

/**
 * ���뼯��a�����a�������Ӽ�
 * 
 * �����Ʒ� �ö����Ʊ�ʾ�Ӽ������д��������iλ����0��ʼ��ţ���ʾԪ��i�Ƿ��ڼ����У�1��ʾ���ڡ���0��ʾ�����ڡ�����
 * 
 * �����XOR�����������^�� 
 * ȫ������ΪALL_BITS= (1<<n)-1����A�Ĳ�������ALL_BITS^A
 * �ö����Ʊ�ʾ�Ӽ���λ�����еİ�λ�롢������Ӧ���ϵĽ������ͶԳƲ�
 */
public class Binary {

	// ����ÿ���Ӽ��ı����Ӧ�ڼ���S�е�λ�ã���00100,��Ӧ��S����{3}
	public static void f(int[] S, int i) {
		int N = S.length;
		for (int j = 0; j < N; j++) {
			// 1<<j:2^j,��������ʽΪ�������󣬵�jλ��Ϊ1������Ϊȫ��Ϊ0�����Լ��i�ĵ�jλ�Ƿ�Ϊ1�����Ϊ1���������S�е�ֵ
			//�жϸ��Ӽ��Ķ����Ʊ����еĵ�jλ�Ƿ�Ϊ0
			if ((i & (1 << j)) != 0) {
				System.out.print(S[j] + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			String s = in.nextLine();
			if ("0".equals(s)) {
				break;
			}

			String[] strs = s.split(" ");
			int[] a = new int[strs.length];
			for (int i = 0; i < strs.length; i++) {
				a[i] = Integer.parseInt(strs[i]);
			}
			// 1����ƽ��S.length�����Ⱦ���2^S.length,ö�ٸ��Ӽ�����Ӧ�ı���0,1,2,...2^S.length-1
			//i�������Ӽ���Ӧ�Ķ����Ʊ����ʮ������ʽ
			for (int i = 0; i < (1 << a.length); i++) {
				f(a, i);
			}
			System.out.println();
		}

	}

}
