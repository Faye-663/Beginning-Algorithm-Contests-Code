import java.util.Scanner;
import java.util.Stack;

public class Main {

	private static int[][] matrix;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		matrix = new int[1000][3];
		in.nextLine();
		for (int i = 0; i < n; i++) {
			String[] strs = in.nextLine().split(" ");
			matrix[i][0] = (int) strs[0].charAt(0);
			matrix[i][1] = Integer.parseInt(strs[1]);
			matrix[i][2] = Integer.parseInt(strs[2]);
		}

		while (in.hasNext()) {
			String str = in.nextLine();
			if (str.length() == 1) {
				System.out.println(0);
				continue;
			}
			Stack<Integer> s = new Stack();
			boolean flag = true;
			int ans = 0;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if ('(' == c) {
					continue;
				} else if (')' == c) {
					int m2 = s.pop();
					int m1 = s.pop();
					if (matrix[m1 - 65][2] == matrix[m2 - 65][1]) {
						ans = matrix[m1 - 65][1] * matrix[m1 - 65][2]
								* matrix[m2 - 65][2] + ans;
						matrix[m1 + m2 - 65][1] = matrix[m1 - 65][1];
						matrix[m1 + m2 - 65][2] = matrix[m2 - 65][2];
						matrix[m1 + m2 - 65][0] = m1 + m2;
						s.push((m1 + m2));
					} else {
						flag = false;
						break;
					}

				} else {
					s.push((int) c);
				}
			}
			if (flag) {
				System.out.println(ans);
			} else {
				System.out.println("error");
			}
		}

	}

}
