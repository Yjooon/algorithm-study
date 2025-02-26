import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int calc(int n) {
		int ori = n;
		while (n > 0) {
			ori += n % 10;
			n /= 10;
		}
		return ori;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int result = 0;
		for (int i = 0; i < N; i++) {
			int temp = calc(i);
			if (temp == N) {
				result = i;
				break;
			}
		}

		System.out.println(result);
	}
}