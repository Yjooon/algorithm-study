import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// 5
// 3 2 1 4 5
public class Main {
	static int[] arr;

	static int result_count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(N, 0));
		int[] isVisited = new int[100001];
		Arrays.fill(isVisited, Integer.MAX_VALUE);

		int minTime = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int currN = p.n;
			int currTime = p.time;

			if (K == currN) {
				if (minTime > currTime) {
					minTime = Math.min(minTime, currTime);
					result_count = 0;
				}
				result_count++;
				continue;
			}

			if (currN - 1 >= 0) {
				if (isVisited[currN - 1] >= currTime + 1) {
					isVisited[currN - 1] = currTime + 1;
					q.offer(new Pair(currN - 1, currTime + 1));
				}
			}
			if (currN + 1 <= 100000) {
				if (isVisited[currN + 1] >= currTime + 1) {
					isVisited[currN + 1] = currTime + 1;
					q.offer(new Pair(currN + 1, currTime + 1));
				}
			}
			if (currN * 2 <= 100000) {
				if (isVisited[currN * 2] >= currTime + 1) {
					isVisited[currN * 2] = currTime + 1;
					q.offer(new Pair(currN * 2, currTime + 1));
				}
			}
		}
		System.out.println(minTime);
		System.out.println(result_count);
	}

	static class Pair {
		int n;
		int time;

		Pair(int n, int time) {
			this.n = n;
			this.time = time;
		}
	}
}