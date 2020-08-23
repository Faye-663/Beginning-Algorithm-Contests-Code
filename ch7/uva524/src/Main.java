import java.util.Scanner;

public class Main {

	private static boolean[] no_prime;
	private static int[] vis;

	private static void f(int n, int[] a, int cur) {
		if (n == cur&&!no_prime[a[0]+a[n-1]]) {
			for (int i= 0;i<n-1;i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println(a[n-1]);
		} else
			for (int i = 2; i <= n; i++) {
				if (vis[i]==0&&!no_prime[i+a[cur-1]]) {
					a[cur] = i;
					vis[i]=1;
					f(n, a, cur + 1);
					vis[i] = 0;
				}
			}

	}

	private static void Eratosthenes(int n) {
		no_prime = new boolean[n + 5];

		for (int i = 2; i * i <= n; i++) {
			if (no_prime[i]) {
				continue;
			}

			for (int j = i * i; j <= n; j += i) {
				no_prime[j] = true;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int count = 1;
		Eratosthenes(32);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] a = new int[n];
			vis = new int[n+1];
			a[0] = 1;
			vis[1] =1;

			if (count != 1) {
				System.out.println();
			}
			System.out.println("Case " + count + ":");
			f(n, a, 1);
			count++;
		}

	}

}
