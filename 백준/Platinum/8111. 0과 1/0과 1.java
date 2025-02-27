import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
	static int input;
	static StringBuilder result;
	static boolean[] isVisited;

	static class Pair {
		int n;
		StringBuilder result;

		Pair(int n, StringBuilder result) {
			this.n = n;
			this.result = result;
		}
	}

	static void calc(int n) {
		isVisited = new boolean[n];
		Arrays.fill(isVisited, false);

		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(1, new StringBuilder("1")));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int currN = p.n;
			StringBuilder sb = p.result;

			if (currN % n == 0) {
				result.append(sb);
				q.clear();
				return;
			}
			isVisited[currN] = true;

			if (!isVisited[currN * 10 % n]) {
				q.offer(new Pair((currN * 10) % n, new StringBuilder(sb + "0")));
				isVisited[currN * 10 % n] = true;
			}
			if (!isVisited[(currN * 10 + 1) % n]) {
				q.offer(new Pair((currN * 10 + 1) % n, new StringBuilder(sb + "1")));
				isVisited[(currN * 10 + 1) % n] = true;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			result = new StringBuilder();
			calc(input);
			System.out.println(result);
		}
	}
}