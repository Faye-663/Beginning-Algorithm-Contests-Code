import java.util.Scanner;

/**
 * ö��{1,2,3,...,n}�������Ӽ�
 * 
 * ����һ��λ����B[i]������B[i]=1�����ҽ���i���Ӽ�A��
 * ������Ԫ���Ƿ�ѡ��ȫ��ȷ����Ϻ����һ���������Ӽ�
 * 2^(n+1)-1
 */

public class bitVector {

	static int[] b = new int[100];

	/**
	 * 
	 * @param n
	 *            n��ֵ
	 * @param b
	 *            λ����
	 * @param cur
	 *            ��Ҫȷ��Ԫ���Ƿ�ѡ���λ��
	 */
	private static void f(int n, int[] b, int cur) {
		//��ӡ��ǰ����
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
		
		//ѡ��cur��Ԫ��
		b[cur]=0;
		f(n,b,cur+1);
		
		//��ѡ��cur��Ԫ��
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
