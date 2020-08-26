import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	//存储输入的text，数据域
	static char[] text = new char[100005];
	//存储指针，指针域，next[i] = text[i]的下一个字符的位置，next[0] = 第一个字符的位置
	static int[] next = new int[100005];
	//光标当前位置
	static int cur;
	//结束指针,最后一个字符后面的位置
	static int last;

	public static void main(String[] args) throws IOException {

		BufferedReader in1 = new BufferedReader(
				new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;

		while ((s = in1.readLine()) != null) {

			for (int i = 0; i < s.length(); i++) {
				text[i + 1] = s.charAt(i);
			}
			//初始化
			cur = last = next[0] = 0;

			for (int i = 1; i <= s.length(); i++) {
				char c = text[i];
				//光标定位至起始位置
				if (c == '[') {
					cur = 0;
				//光标定位至末尾位置
				} else if (c == ']') {
					cur = last;
				//光标顺次后移
				} else {
					next[i] = next[cur];
					next[cur] = i;
					//更新"最后一个字符"编号
					if (cur == last) {
						last = i;
					}
					//移动光标
					cur = i;
				}
			}

			for (int i = next[0]; i != 0; i = next[i]) {
				out.write(text[i]);
			}
			out.write("\n");
			out.flush();

		}

	}

}
