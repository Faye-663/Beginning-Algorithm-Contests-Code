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
	// 存变量
	private static HashMap<String, Integer> map;

	private static void run(ArrayList<String> strs, int[] indexs) {
		int endNo = 0;
		while (endNo < n) {
			// 判断是否读到end
			boolean isEnd = false;
			// 取等待队列队首的程序
			int temp = wait.poll();
			// 取该程序在任务数组的索引
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
					// 若已锁，则将当前程序加入stop，index--,再直接break，未用配额浪费
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
					// 防止最后一个程序取到null
					if (stop.peek() != null) {
						wait.addFirst(stop.poll());
					}
					// 解锁
					isLock = false;
				} else {
					// 读到end，直接break，未用配额浪费
					endNo++;
					isEnd = true;
					index--;
					break;
				}
				i -= time;
			}
			// 更新当前程序索引
			indexs[temp] = index;
			// 若未读到lock和end则，加入wait
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
			// 读入
			in.nextLine();
			n = in.nextInt();
			t1 = in.nextInt();
			t2 = in.nextInt();
			t3 = in.nextInt();
			t4 = in.nextInt();
			t5 = in.nextInt();
			Q = in.nextInt();
			in.nextLine();
			// 初始化 任务数组、索引数组（存各程序在任务数组的索引）、等待队列、阻止队列、isLock、map
			ArrayList<String> strs = new ArrayList<String>();
			int[] indexs = new int[n + 1];
			wait = new ArrayDeque(n);
			for (int i = 0; i < n; i++) {
				wait.add(i);
			}
			stop = new LinkedList<Integer>();
			isLock = false;
			map = new HashMap<String, Integer>();
			// 读入，写入任务数组
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
