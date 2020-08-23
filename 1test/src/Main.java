import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	static int[] p = new int[100];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int n = in.nextInt();
			if(n == 0){
				break;
			}
			print_permutation(n,p,0);

		}
	}

	
	private static void print_permutation(int n, int[] result, int cur) {
        if (cur == n) { // �ݹ�߽�,�ݹ����
            for (int i = 0; i < n; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }
        else 
        {
            for(int i = 1; i <= n; i++){ // ������result[j]�������������i(1~n)
                boolean flag = true;
                for (int j = 0; j < cur; j++) {
                    if (result[j] == i) { // ���i�Ѿ���result[0]~result[j-1]���ֹ� ������ѡ
                        flag = false;
                    }
                }
                if (flag) {
                    result[cur] = i;
                    print_permutation(n, result, cur + 1);
                }
            }
        }
    }
}
