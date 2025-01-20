import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	final static int base = 31;
	final static int mod = 1234567891;

	public static long func(int base, int power) {
		long result = 1;
		for (int i = 0; i < power; i++) {
			result = (result * base) % mod;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());

		String s = br.readLine();
		long answer = 0;

		for (int i = 0; i < L; i++) {
			char ch = s.charAt(i);

			answer += ((ch - 'a' + 1) * (func(base, i) % mod)) % mod;

		}
		answer %= mod;
		System.out.println(answer);
	}

}
