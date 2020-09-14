import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 一共只有两层，每层都是n*n的，第一层第i行全是国家i，第 二层第j列全是国家j
 * AAA
 * BBB
 * CCC
 * 
 * ABC
 * ABC
 * ABC
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		String s;
		while ((s = in.readLine()) != null) {
			int n = Integer.parseInt(s);

			out.println("2 " + n + " " + n);
			char c = 65;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					out.print(c);
				}
				out.println();
				if (c == 90) {
					c = 96;
				}
				c += 1;
			}
			out.println();
			for (int i = 0; i < n; i++) {
				c = 65;
				for (int j = 0; j < n; j++) {
					if (c + j == 91) {
						c = (char) (97 - j);
					}
					out.print((char) (c + j));
				}
				out.println();
			}
			out.flush();
		}
	}

}
