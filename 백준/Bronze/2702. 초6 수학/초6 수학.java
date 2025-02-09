import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

	private static int lcm(int a, int b) {
		return (a / gcd(a, b)) * b;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		for (int i = 0; i < T; i++) {
			StringTokenizer s = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(s.nextToken());
			int b = Integer.parseInt(s.nextToken());

			System.out.println(lcm(a, b) + " " + gcd(a, b));

		}

	}
}
