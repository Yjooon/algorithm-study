import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean isPrimeNum(int n) {
		if (n == 2)
			return true;
		if (n % 2 == 0 || n == 1) {
			return false;
		}
		for (int i = 3; i <= n / 2; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int result = 0;
		int firstPrime = 0;

		for (int i = M; i <= N; i++) {
			if (isPrimeNum(i)) {
				if (result == 0) {
					firstPrime = i;
				}
				result += i;
			}
		}

		if (result == 0)
			System.out.println(-1);
		else {
			System.out.println(result);
			System.out.println(firstPrime);
		}
	}
}