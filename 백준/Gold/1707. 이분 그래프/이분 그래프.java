import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= TC; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			ArrayList<Integer>[] graphs = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				graphs[i] = new ArrayList<>();
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graphs[a].add(b);
				graphs[b].add(a);
			}
			int[] isVisited = new int[V + 1];
			Arrays.fill(isVisited, 0);
			boolean flag = false;
			for (int i = 1; i <= V; i++) {
				if (isVisited[i] != 0)
					continue;
				Deque<int[]> q = new ArrayDeque<>();
				q.offer(new int[] { i, 1 });
				isVisited[i] = 1;

				while (!q.isEmpty()) {
					int[] curr = q.poll();

					for (int node : graphs[curr[0]]) {
						if (isVisited[node] == 0) {
							isVisited[node] = curr[1] * -1;
							q.offer(new int[] { node, curr[1] * -1 });
						} else {
							if (isVisited[node] != curr[1] * -1) {
								flag = true;
								break;
							}
						}
					}
					if (flag)
						break;
				}
				if (flag)
					break;
			}

			System.out.println(flag ? "NO" : "YES");

		}
	}
}