import java.util.Scanner;

/**
 * 
 * 如果用C[x]表示第x行皇后的列编号，则问题变成了全排列生成问题。
 * 而0～n的排列一共只有(n+1)!，枚举量不会超过它.
 *
 *格子(x,y)的y-x值标识了主对角线
 *格子(x,y)的x+y值标识了副对角线
 */
public class NQueens {
	
	private static int[] c;
	private static int tot;

	private static void f(int n,int cur){
		//递归边界
		if(n == cur){
			tot++;
		}else for(int i = 0;i<n;i++){
			//尝试把第cur行的皇后放在第i列
			c[cur] = i;
			boolean flag = true;
			
			//检查是否和前面的皇后冲突
			for(int j = 0;j<cur;j++){
				//检查是否纵向和斜向攻击
				if(c[cur]==c[j]||cur-c[cur]==j-c[j]||cur+c[cur]==j+c[j]){
					flag = false;
					break;
				}
			}
			//如果合法，则继续递归
			if(flag){
				f(n,cur+1);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			if(n == 0){
				break;
			}
			
			c = new int[n];
			tot = 0;
			f(n,0);
			System.out.println(tot);
		}

	}

}
