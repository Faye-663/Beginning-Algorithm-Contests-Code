import java.util.Scanner;

/**只需要枚举fghij就可以算出abcde，
 * 然后判断是否所有数字都不相同即可
 */

public class Main {
	
	private static boolean isDiff(int n,int m){
		String l = String.valueOf(n);
		String r;
		if(m<10000){
			 r = "0"+m;
		}else{
			 r = String.valueOf(m);
		}
		
		
		
		for(int i = 0;i<5;i++){
			for(int j = 0;j<5;j++){
				if(l.charAt(i) == r.charAt(j)){
					return false;
				}
				for(int z = j+1;z<5;z++){
					if(r.charAt(z) == r.charAt(j)){
						return false;
					}
				}
			}
			for(int j =i+1;j<5;j++){
				if(l.charAt(i) == l.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int q = 0;
		while(in.hasNext()){
			int n = in.nextInt();
			if(n == 0){
				break;
			}
			if(q != 0){
				System.out.println();
			}
			q++;
			int count = 0;
			for(int i =1234;i<50000&&i*n<=98765;i++){
				int temp = i*n;
				if(temp<10000)continue;
				if(isDiff(temp,i)){
					count++;
					System.out.printf("%05d / %05d = %d",temp,i,n);
					System.out.println();
					
				}
			}
			
			if(count == 0){
				System.out.println("There are no solutions for "+n+".");
			}
		}
	}

}
