import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int n, t1, t2, t3, t4, t5, Q;
	private static ArrayDeque<Integer> wait;
	private static Queue<Integer> stop;
	private static boolean isLock;
	// �����
	private static HashMap<String, Integer> map;

	private static void run(ArrayList<String> strs, int[] indexs) {
		int endNo = 0;
		while (endNo < n) {
			// �ж��Ƿ����end
			boolean isEnd = false;
			// ȡ�ȴ����ж��׵ĳ���
			int temp = wait.poll();
			// ȡ�ó������������������
			int index = indexs[temp];
			for (int i = Q; i > 0;) {
				String s = strs.get(index++);
				int time;
				if (s.contains("=")) {
					time = t1;
					String[] strings = s.split(" ");
					map.put(strings[0], Integer.parseInt(strings[2]));
				} else if (s.contains("print")) {
					time = t2;
					String[] strings = s.split(" ");
					if (map.get(strings[1]) == null) {
						System.out.println(temp + 1 + ": " + 0);
					} else {
						System.out.println(temp + 1 + ": "
								+ map.get(strings[1]));
					}
				} else if ("lock".equals(s)) {
					// ���������򽫵�ǰ�������stop��index--,��ֱ��break��δ������˷�
					if (!isLock) {
						time = t3;
						isLock = true;
					} else {
						stop.add(temp);
						index--;
						break;
					}
				} else if ("unlock".equals(s)) {
					time = t4;
					// ��ֹ���һ������ȡ��null
					if (stop.peek() != null) {
						wait.addFirst(stop.poll());
					}
					// ����
					isLock = false;
				} else {
					// ����end��ֱ��break��δ������˷�
					endNo++;
					isEnd = true;
					index--;
					break;
				}
				i -= time;
			}
			// ���µ�ǰ��������
			indexs[temp] = index;
			// ��δ����lock��end�򣬼���wait
			if (!stop.contains(temp) && !isEnd) {
				wait.add(temp);
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		in.nextLine();

		while (num > 0) {
			// ����
			in.nextLine();
			n = in.nextInt();
			t1 = in.nextInt();
			t2 = in.nextInt();
			t3 = in.nextInt();
			t4 = in.nextInt();
			t5 = in.nextInt();
			Q = in.nextInt();
			in.nextLine();
			// ��ʼ�� �������顢�������飨���������������������������ȴ����С���ֹ���С�isLock��map
			ArrayList<String> strs = new ArrayList<String>();
			int[] indexs = new int[n + 1];
			wait = new ArrayDeque(n);
			for (int i = 0; i < n; i++) {
				wait.add(i);
			}
			stop = new LinkedList<Integer>();
			isLock = false;
			map = new HashMap<String, Integer>();
			// ���룬д����������
			int endNo = 0;
			while (endNo < n) {
				String line = in.nextLine();
				if ("end".equals(line)) {
					endNo++;
					indexs[endNo] = strs.size() + 1;
				}
				strs.add(line);
			}

			run(strs, indexs);
			num--;
			if (num > 0) {
				System.out.println();
			}
		}

	}

}
