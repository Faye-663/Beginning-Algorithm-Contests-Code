import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int m = 1;
		while(in.hasNext()){
			int n = in.nextInt();
			int[] nums = new int[n];
			for(int i =0;i<n;i++){
				nums[i] = in.nextInt();
			}
			
			long max = 0L;
			for(int i = 0;i<n;i++){
				for(int j =i;j<n;j++){
					long temp = 1L;
					for(int z = i;z<=j;z++){
						temp*=nums[z];
					}
					
					max = Math.max(temp, max);
				}
			}
			
			System.out.println("Case #"+m+": The maximum product is "+max+".");
			System.out.println();
			m++;
		}

	}

}
