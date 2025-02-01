import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int inputNum = Integer.parseInt(br.readLine());
		int nextCycle = 0;
		int result = 1;
		int newA = inputNum / 10;
		int newB = inputNum % 10;

		nextCycle = newB * 10 + (newA + newB) % 10;

		while (inputNum != nextCycle) {
			result++;

			int a = nextCycle / 10;
			int b = nextCycle % 10;


			nextCycle = b * 10 + (a + b) % 10;
		}

		System.out.println(result);
	}
}