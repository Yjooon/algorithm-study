import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Pair house = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Pair[] conv = new Pair[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				conv[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			}
			st = new StringTokenizer(br.readLine());
			Pair rock = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			Deque<Pair> q = new ArrayDeque<>();
			q.offer(house);
			Set<Pair> set = new HashSet<>();
			boolean happy = false;
			while (!q.isEmpty()) {
				Pair curr = q.poll();
				if (distance(curr, rock) <= 1000) {
					happy = true;
					break;
				}
				for (Pair p : conv) {
					if (set.contains(p))
						continue;
					if (distance(p, curr) <= 1000) {
						set.add(p);
						q.offer(p);
					}
				}
			}

			System.out.println(happy ? "happy" : "sad");
		}
	}

	static int distance(Pair p1, Pair p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
}