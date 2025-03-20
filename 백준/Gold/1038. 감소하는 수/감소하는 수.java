import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		System.out.println(findDecreasingNumber(N));
	}

	public static long findDecreasingNumber(int n) {
		Queue<Long> queue = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			queue.offer((long) i);
		}

		int count = 0;

		while (!queue.isEmpty()) {
			long num = queue.poll();

			if (count == n) {
				return num;
			}

			count++;

			// 마지막 자릿수를 추출하여 그보다 작은 자리를 추가
			int lastDigit = (int) (num % 10);

			// 마지막 자리가 0이면 더 이상 자릿수를 만들 수 없으므로 종료
			for (int i = 0; i < lastDigit; i++) {
				queue.offer(num * 10 + i);
			}
		}

		// N번째 감소하는 수가 없을 경우
		return -1;
	}
}
