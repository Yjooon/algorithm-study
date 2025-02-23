import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int A;
	static int B;

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;

		bfs(A);
		if (result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);
	}

	static class Pair {
		int n;
		int depth;

		Pair(int n, int depth) {
			this.n = n;
			this.depth = depth;
		}
	}

	static void bfs(int n) {
		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(n, 0));
		Set<Integer> set = new HashSet<>();
		set.add(n);

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int currN = p.n;
			int currDepth = p.depth;

			if (currN > B)
				continue;
			if (currN == B) {
				result = Math.min(result, currDepth + 1);
			}

			if (!set.contains(currN * 2) && currN <= Integer.MAX_VALUE / 2)
				q.add(new Pair(currN * 2, currDepth + 1));
			if (!set.contains(currN * 10 + 1) && currN <= (Integer.MAX_VALUE - 1) / 10)
				q.add(new Pair(currN * 10 + 1, currDepth + 1));
		}
	}
}
