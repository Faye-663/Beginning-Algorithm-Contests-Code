import java.util.Scanner;
import java.util.Stack;

public class Main {

	// 需要判断的出站顺序
	private static int[] target;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			// 程序结束
			if (n == 0) {
				break;
			}
			while (true) {
				int m = in.nextInt();
				// 块结束，进入下一块
				if (m == 0) {
					break;
				}
				target = new int[n + 1];
				target[1] = m;
				for (int i = 2; i <= n; i++) {
					int j = in.nextInt();
					target[i] = j;
				}

				// C站的栈
				Stack<Integer> c = new Stack();
				// A站出站的列车序号
				int a = 1;
				// 将要进入B站的第b辆列车，该列车序号即为target[b]
				int b = 1;
				// 是否合法标志
				boolean flag = true;

				while (b <= n) {
					// 出站列车 == 入站列车
					if (a == target[b]) {
						// 出站、入站
						a++;
						b++;
					// C站的栈首 == 入站列车
					} else if (!c.empty() && c.peek() == target[b]) {
						c.pop();
						// 未出站，仅入站
						b++;
					// 放入C站的栈
					} else if (a <= n) {
						// 未入站，仅出站
						c.push(a++);
					// 以上情况都不符合，出站列车序号>n，该顺序不合法
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
