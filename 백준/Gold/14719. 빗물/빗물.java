import java.util.*;
import java.io.*;

public class Main {
	static Deque<Integer> q;
	static int result;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		q = new ArrayDeque<>();
		result = 0;
		for (int i = 0; i < W; i++) {
			int curr = Integer.parseInt(st.nextToken());
			if (q.isEmpty()) {
				q.offer(curr);
				continue;
			}

			if (q.peekFirst() <= curr) {
				calc();
				q.offer(curr);
				continue;
			} else {
				q.offer(curr);
			}
		}
		lastCalc();

		System.out.println(result);
	}

	static void calc() {
		int high = q.poll();
		while (!q.isEmpty()) {
			int curr = q.poll();
			result += high - curr;
		}
	}

	static void lastCalc() {
		if (!q.isEmpty())
			q.poll();

		while (!q.isEmpty()) {
			int maxHeight = 0;
			for (int i : q) {
				maxHeight = Math.max(maxHeight, i);
			}
			while (true) {
				int curr = q.poll();
				if (curr == maxHeight) {
					break;
				}
				result += maxHeight - curr;
			}
		}

	}
}
