import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 5
// 3 2 1 4 5
public class Main {
	static int[] arr;

	static int result;

	static class Pair {
		int value;
		int index;

		Pair(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Deque<Pair> q = new ArrayDeque<>();
		int[] result = new int[N + 1];
		for (int i = N; i >= 1; i--) {
			if (q.isEmpty()) {
				q.offer(new Pair(arr[i], i));
				continue;
			}

			while (!q.isEmpty() && q.getLast().value < arr[i]) {
				result[q.pollLast().index] = i;
			}
			q.offer(new Pair(arr[i], i));
		}
		for (int i = 1; i <= N; i++)
			System.out.print(result[i] + " ");
	}
}