import java.util.Scanner;

/**
 * ���ö�ά����vis[3][ ]ֱ���жϵ�ǰ���ԵĻʺ����ڵ��к������Խ����Ƿ����������ʺ�.
 * ע�⵽���Խ��߱�ʶy-x�� ��Ϊ������ȡʱҪ����n.
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
				//������ô�ӡ�⣬����C���鶼����ʡ��
				//c[cur] = i;
				
				//�޸�ȫ�ֱ���
				vis[0][i] = vis[1][cur-i+n] = vis[2][cur+i] = 1;
				f(n,cur+1);
				
				//�мǣ�һ��Ҫ�Ļ���
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
