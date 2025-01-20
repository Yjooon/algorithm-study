import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());

		String s = br.readLine();
		long answer = 0;
		int base = 31;
		int mod = 1234567891;
		int result = 1;
		for (int i = 0; i < L; i++) {
			char ch = s.charAt(i);

			answer += (ch - 'a' + 1) * (Math.pow(base, i) % mod);
		}
		answer %= mod;
		System.out.println(answer);
	}
}
