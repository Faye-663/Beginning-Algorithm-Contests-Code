import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Main {

	//�洢�����text��������
	static char[] text = new char[100005];
	//�洢ָ�룬ָ����next[i] = text[i]����һ���ַ���λ�ã�next[0] = ��һ���ַ���λ��
	static int[] next = new int[100005];
	//��굱ǰλ��
	static int cur;
	//����ָ��,���һ���ַ������λ��
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
			//��ʼ��
			cur = last = next[0] = 0;

			for (int i = 1; i <= s.length(); i++) {
				char c = text[i];
				//��궨λ����ʼλ��
				if (c == '[') {
					cur = 0;
				//��궨λ��ĩβλ��
				} else if (c == ']') {
					cur = last;
				//���˳�κ���
				} else {
					next[i] = next[cur];
					next[cur] = i;
					//����"���һ���ַ�"���
					if (cur == last) {
						last = i;
					}
					//�ƶ����
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
