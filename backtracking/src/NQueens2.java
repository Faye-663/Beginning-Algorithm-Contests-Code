import java.util.Scanner;

/**
 * 利用二维数组vis[3][ ]直接判断当前尝试的皇后所在的列和两个对角线是否已有其他皇后.
 * 注意到主对角线标识y-x可 能为负，存取时要加上n.
 *
 */
public class NQueens2 {

	private static int[] c;
	private static int[][] vis;
	private static int tot;
	
	private static void f(int n,int cur){
		if(n == cur){
			tot++;
		}else for(int i =0;i<n;i++){
			if(vis[0][i]==0&&vis[1][cur-i+n]==0&&vis[2][cur+i]==0){
				//如果不用打印解，整个C数组都可以省略
				//c[cur] = i;
				
				//修改全局变量
				vis[0][i] = vis[1][cur-i+n] = vis[2][cur+i] = 1;
				f(n,cur+1);
				
				//切记！一定要改回来
				vis[0][i] = vis[1][cur-i+n] = vis[2][cur+i] = 0;
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
			vis = new int[3][2*n];
			tot = 0;
			f(n,0);
			System.out.println(tot);
		}

	}

}
