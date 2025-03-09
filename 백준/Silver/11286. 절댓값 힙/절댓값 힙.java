import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int[] arr;

	static int result_count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
			int abs1 = Math.abs(a);
			int abs2 = Math.abs(b);
			if (abs1 == abs2)
				return a > 0 ? 1 : -1;
			return abs1 - abs2;

		});
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			if (input == 0) {
				if (pq.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(pq.poll());
				}
				continue;
			}
			pq.add(input);
		}
	}
}