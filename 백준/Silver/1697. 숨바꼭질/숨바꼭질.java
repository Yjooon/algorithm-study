import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int x;
		int depth;

		Pair(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Deque<Pair> q = new ArrayDeque<>();
		boolean[] isVisited = new boolean[100001];
		q.add(new Pair(N, 0));
		isVisited[N] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int currX = p.x;
			int depth = p.depth;

			if (currX == K) {
				System.out.println(depth);
				return;
			}

			if (currX - 1 >= 0) {
				if (!isVisited[currX - 1]) {
					q.add(new Pair(currX - 1, depth + 1));
					isVisited[currX - 1] = true;
				}
			}
			if (currX + 1 <= 100000)
				if (!isVisited[currX + 1]) {
					q.add(new Pair(currX + 1, depth + 1));
					isVisited[currX + 1] = true;
				}
			if (currX * 2 <= 100000) {
				if (!isVisited[currX * 2]) {
					q.add(new Pair(currX * 2, depth + 1));
					isVisited[currX * 2] = true;
				}
			}
		}
	}

}