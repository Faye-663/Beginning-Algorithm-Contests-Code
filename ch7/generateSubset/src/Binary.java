import java.util.Scanner;

/**
 * 输入集合a，输出a的所有子集
 * 
 * 二进制法 用二进制表示子集，其中从右往左第i位（从0开始编号）表示元素i是否在集合中（1表示“在”，0表示“不在”）。
 * 
 * “异或（XOR）”运算符“^” 
 * 全集定义为ALL_BITS= (1<<n)-1，则A的补集就是ALL_BITS^A
 * 用二进制表示子集，位运算中的按位与、或、异或对应集合的交、并和对称差
 */
public class Binary {

	// 根据每个子集的编码对应在集合S中的位置，如00100,对应于S就是{3}
	public static void f(int[] S, int i) {
		int N = S.length;
		for (int j = 0; j < N; j++) {
			// 1<<j:2^j,二进制形式为从右往左，第j位置为1，其余为全部为0，可以检测i的第j位是否为1，如果为1，则输出在S中的值
			//判断该子集的二进制编码中的第j位是否为0
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
			// 1向左平移S.length个长度就是2^S.length,枚举各子集所对应的编码0,1,2,...2^S.length-1
			//i是所有子集对应的二进制编码的十进制形式
			for (int i = 0; i < (1 << a.length); i++) {
				f(a, i);
			}
			System.out.println();
		}

	}

}
