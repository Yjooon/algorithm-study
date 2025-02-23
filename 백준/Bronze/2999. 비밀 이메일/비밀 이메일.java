import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String input;
	static int N;
	static int R;
	static int C;

	static void calc() {
		R = 1;
		C = N;

		while (R <= C) {
			R++;
			if (N % R != 0) {
				continue;
			}
			C = N / R;
		}
		R = N / C;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();

		N = input.length();

		calc();
//		System.out.println("R: " + R + " C: " + C);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				sb.append(input.charAt(i + j * C));
			}
		}

		System.out.println(sb);
	}
}
