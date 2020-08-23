import java.util.Scanner;

/**
 * 
 * �����C[x]��ʾ��x�лʺ���б�ţ�����������ȫ�����������⡣
 * ��0��n������һ��ֻ��(n+1)!��ö�������ᳬ����.
 *
 *����(x,y)��y-xֵ��ʶ�����Խ���
 *����(x,y)��x+yֵ��ʶ�˸��Խ���
 */
public class NQueens {
	
	private static int[] c;
	private static int tot;

	private static void f(int n,int cur){
		//�ݹ�߽�
		if(n == cur){
			tot++;
		}else for(int i = 0;i<n;i++){
			//���԰ѵ�cur�еĻʺ���ڵ�i��
			c[cur] = i;
			boolean flag = true;
			
			//����Ƿ��ǰ��Ļʺ��ͻ
			for(int j = 0;j<cur;j++){
				//����Ƿ������б�򹥻�
				if(c[cur]==c[j]||cur-c[cur]==j-c[j]||cur+c[cur]==j+c[j]){
					flag = false;
					break;
				}
			}
			//����Ϸ���������ݹ�
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
