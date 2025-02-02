import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
	int a;
	int b;

	public Pair(int x, int y) {
		this.a = x;
		this.b = y;
	}
}

public class Main {
	static int result = 100000000;
	static Deque<Pair> queue = new ArrayDeque<>();
	static int[] board = new int[101];
	static int[] isVisited = new int[101];
	static List<Pair> ladder;
	static List<Pair> snake;

	static void bfs() {
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			int num = current.a;
			int distance = current.b;
			
			if (isVisited[num] <= distance)
				continue;

			isVisited[num] = distance;

			if (num == 100) {
				result = Math.min(result, distance);
				continue;
			}
			for (int i = 1; i <= 6; i++) {
				int next = num + i;
				for (Pair p : ladder) {
					if (next == p.a) {
						next = p.b;
						break;
					}
				}
				for (Pair p : snake) {
					if (next == p.a) {
						next = p.b;
						break;
					}
				}
				if (next <= 100 && isVisited[next] > distance)
					queue.offer(new Pair(next, distance + 1));
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ladder = new ArrayList<>();
		snake = new ArrayList<>();

		for (int i = 0; i < 101; i++)
			isVisited[i] = 100000000;

		StringTokenizer temp;
		for (int i = 0; i < N; i++) {
			temp = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(temp.nextToken());
			int b = Integer.parseInt(temp.nextToken());
			ladder.add(new Pair(a, b));
		}
		for (int i = 0; i < M; i++) {
			temp = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(temp.nextToken());
			int b = Integer.parseInt(temp.nextToken());
			snake.add(new Pair(a, b));
		}

		queue.offer(new Pair(1, 0));
		bfs();

		System.out.println(result);
	}
}