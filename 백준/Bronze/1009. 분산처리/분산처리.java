import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int result = 1, mod = 10;
			while (b > 0) {
				if (b % 2 == 1) {
					result = result * a % mod;
				}
				a = a * a % mod;
				b /= 2;
			}
			if (result == 0)
				result += 10;
			System.out.println(result);
		}
	}
}
