import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * ��������ߵĴ�ׯ�������Ҫ��ƣ���a1>0����һ�����Ͷ����Ӵ�ׯ2�����˸���ׯ1��
 * ��������Щ���Ǵ��������ģ����ܾ��Ǵ�ׯ2���ģ�Ҳ�����Ǹ��ұߵĴ�ׯ�˵���ׯ2�ģ��� 
 * ����������͵ȼ���ֻ�д�ׯ2��n���ҵ�2����ׯ������Ϊa1+a2�����Ρ�
 * ���ѷ��֣�ai<0ʱ �������Ҳ�������Ͷ���ͬ����Ҫ|ai|����λ��
 * @author 11495
 */
public class Main {

	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		String s;
		while (!"0".equals(s = in.readLine())) {
			int n = Integer.parseInt(s);
			nums = new int[n];
			String[] strs = in.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(strs[i]);
			}

			long count = 0L;
			for (int i = 0; i < n - 1; i++) {
				count += Math.abs(nums[i]);
				nums[i + 1] = nums[i + 1] + nums[i];
			}
			out.println(count);
			out.flush();
		}
	}

}
