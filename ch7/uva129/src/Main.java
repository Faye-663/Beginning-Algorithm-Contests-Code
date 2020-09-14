import java.util.Scanner;

public class Main {

	static int l;
	// 计数器
	static int tot;
	// 是否计数器完成
	static boolean flag;

	private static void f(int n, int[] a, int cur) {
		if (tot == n) {
			// 四位计数器
			int temp = 1;
			// 换行计数器
			int num = 1;

			for (int i = 0; i < cur; i++) {
				// 是否打印空格
				if (temp == 5) {
					// 是否换行
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

			// 计数器完成，无须继续搜索
			flag = true;
			return;
		}

		for (int i = 65; i < 65 + l; i++) {
			// 完成搜索，直接退出
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

	//对于后缀i，任一位不等，即该后缀i不等
	//对于该串，任一个后缀i相等，即该串easy
	private static boolean isHard(int[] a, int cur) {
		// 是否为hard标志
		boolean ok = true;
		// 尝试长度为2*i的后缀
		for (int i = 1; 2 * i <= cur + 1; i++) {
			// 长度i的后缀是否满足
			boolean equal = true;
			// 遍历后缀i
			for (int j = 0; j < i; j++) {
				// 任一位不等，即可break，进行下一个后缀i
				if (a[cur - j] != a[cur - j - i]) {
					equal = false;
					break;
				}
			}
			// 任一个后缀i相等，即不为hard
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
