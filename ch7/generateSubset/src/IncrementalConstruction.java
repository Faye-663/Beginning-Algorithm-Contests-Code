import java.util.Scanner;

/**
 * 枚举{1,2,3,...,n}的所有子集
 * 
 * 一次选出一个元素放到集合中
 * 使用定序的技巧，避免同一个集合枚举两次
 * 2^n
 */

public class IncrementalConstruction {
	static int[] a = new int[100];

	/**
	 * 
	 * @param n
	 *            n的值
	 * @param a
	 *            当前集合，已确定的子集
	 * @param cur
	 *            需要添加元素的位置
	 */
	private static void f(int n, int[] a, int cur) {
		// 打印当前集合
		for (int i = 0; i < cur; i++) {
			System.out.print(a[i] + " ");
		}
		if (cur != 0) {
			System.out.println();
		}else{
			System.out.println("null");
		}

		// 确定当前元素的最小可能值
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
