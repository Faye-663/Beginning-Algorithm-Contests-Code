import java.util.Scanner;

/**
 * 枚举{1,2,3,...,n}的所有子集
 * 
 * 构造一个位向量B[i]，其中B[i]=1，当且仅当i在子集A中
 * “所有元素是否选择”全部确定完毕后才是一个完整的子集
 * 2^(n+1)-1
 */

public class bitVector {

	static int[] b = new int[100];

	/**
	 * 
	 * @param n
	 *            n的值
	 * @param b
	 *            位向量
	 * @param cur
	 *            需要确定元素是否选择的位置
	 */
	private static void f(int n, int[] b, int cur) {
		//打印当前集合
		if(n == cur){
			int temp = 0;
			for(int i=0;i<n;i++){
				if(b[i] ==1){
					System.out.print(i+1+" ");
					temp++;
				}
			}
			if(temp==0){
				System.out.println("null");
			}else{
				System.out.println();
			}
			return;
		}
		
		//选第cur个元素
		b[cur]=0;
		f(n,b,cur+1);
		
		//不选第cur个元素
		b[cur]=1;
		f(n,b,cur+1);
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			f(n, b, 0);
			System.out.println();
		}

	}

}
