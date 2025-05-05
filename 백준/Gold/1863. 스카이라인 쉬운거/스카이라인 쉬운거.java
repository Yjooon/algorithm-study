import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int result = 0;
		
		Deque<Integer> q = new ArrayDeque<>();
		q.offer(0); // 기초값 0
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y > q.peekLast()) {
				q.offerLast(y);
			} else {
				while(y < q.peekLast()) {
					q.pollLast();
					result++;
				}
			}
			if (y != q.peekLast())
				q.offerLast(y);
		}
		while (!q.isEmpty() && q.peekLast() != 0) {
			q.pollLast();
			result++;
		}
		
		System.out.println(result);
	}
}