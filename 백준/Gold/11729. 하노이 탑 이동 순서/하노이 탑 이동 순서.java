import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int moveCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		System.out.println((int) (Math.pow(2, n) - 1));
		hanoi(n, 1, 2, 3);
		System.out.print(sb);
	}

	public static void hanoi(int n, int start, int auxiliary, int end) {
		if (n == 1) {
			sb.append(start).append(" ").append(end).append("\n");
			return;
		}

		hanoi(n - 1, start, end, auxiliary);
		sb.append(start).append(" ").append(end).append("\n");
		hanoi(n - 1, auxiliary, start, end);
	}
}
