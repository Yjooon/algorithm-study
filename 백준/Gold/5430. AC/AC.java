import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < TC; i++) {
			String order = br.readLine();
			int n = Integer.parseInt(br.readLine());
			Deque<Integer> deque = new ArrayDeque<>();

			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s.substring(1, s.length() - 1), ",");
			for (int j = 0; j < n; j++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}

			boolean reversed = false;
			boolean error = false;

			for (char cmd : order.toCharArray()) {
				if (cmd == 'R') {
					reversed = !reversed;
				} else {
					if (deque.isEmpty()) {
						error = true;
						break;
					}
					if (!reversed) {
						deque.pollFirst();
					} else {
						deque.pollLast();
					}
				}
			}

			if (error) {
				result.append("error\n");
			} else {
				result.append("[");
				if (!reversed) {
					while (!deque.isEmpty()) {
						result.append(deque.pollFirst());
						if (!deque.isEmpty())
							result.append(",");
					}
				} else {
					while (!deque.isEmpty()) {
						result.append(deque.pollLast());
						if (!deque.isEmpty())
							result.append(",");
					}
				}
				result.append("]\n");
			}
		}
		System.out.print(result);
	}
}
