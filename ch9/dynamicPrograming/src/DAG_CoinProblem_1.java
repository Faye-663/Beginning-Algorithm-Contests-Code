import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 本题要求最小、最大两个值，记忆化搜索就必须写两个。 在这种情况下，用递推更加方便
 * 
 * @author 11495
 * 
 */
public class DAG_CoinProblem_1 {

	static int[] v, maxd, mind;
	static String[] temp;

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String str;
		while (!"0".equals(str = in.readLine())) {
			int n = Integer.parseInt(str);
			temp = in.readLine().split(" ");
			v = new int[n + 2];
			for (int i = 1; i <= n; i++) {
				v[i] = Integer.parseInt(temp[i - 1]);
			}

			int s = Integer.parseInt(in.readLine());
			maxd = new int[s + 2];
			mind = new int[s + 2];

			maxd[0] = mind[0] = 0;
			for (int i = 1; i < maxd.length; i++) {
				maxd[i] = Integer.MIN_VALUE;
				mind[i] = Integer.MAX_VALUE / 2;
			}

			for (int i = 1; i <= s; i++) {
				for (int j = 1; j <= n; j++) {
					if (i >= v[j]) {
						mind[i] = Math.min(mind[i], mind[i - v[j]] + 1);
						maxd[i] = Math.max(maxd[i], maxd[i - v[j]] + 1);
					}
				}
			}
			out.println(mind[s] + " " + maxd[s]);
			out.flush();

		}

	}
}
