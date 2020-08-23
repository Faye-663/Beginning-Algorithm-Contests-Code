import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		
		while(in.hasNext()){
			int k = in.nextInt();
			int count = 0;
			
			
			for(int y = k+1;y<=2*k;y++){
				double x = k*y*1.0/(y-k);
				int x1 = (int)x;
				if(x-x1==0){
					count++;
				}
			}
			System.out.println(count);
			for(int y = k+1;y<=2*k;y++){
				double x = k*y*1.0/(y-k);
				int x1 = (int)x;
				if(x-x1==0){
					System.out.printf("1/%d = 1/%d + 1/%d",k,x1,y);
					System.out.println();
				}
			}
		}
		

	}

}
