import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] maxArr = new int[3];
		int[] minArr = new int[3];
		int[] inputArr = new int[3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				inputArr[j] = Integer.parseInt(st.nextToken());
			}

			if (i == 0) {
				for (int j = 0; j < 3; j++) {
					maxArr[j] = inputArr[j];
					minArr[j] = inputArr[j];
				}
				continue;
			}

			int[] tempMax = new int[3];
			int[] tempMin = new int[3];

			tempMax[0] = inputArr[0] + Math.max(maxArr[0], maxArr[1]);
			tempMin[0] = inputArr[0] + Math.min(minArr[0], minArr[1]);

			tempMax[1] = inputArr[1] + Math.max(Math.max(maxArr[0], maxArr[1]), maxArr[2]);
			tempMin[1] = inputArr[1] + Math.min(Math.min(minArr[0], minArr[1]), minArr[2]);

			tempMax[2] = inputArr[2] + Math.max(maxArr[1], maxArr[2]);
			tempMin[2] = inputArr[2] + Math.min(minArr[1], minArr[2]);

			maxArr[0] = tempMax[0];
			minArr[0] = tempMin[0];

			maxArr[1] = tempMax[1];
			minArr[1] = tempMin[1];

			maxArr[2] = tempMax[2];
			minArr[2] = tempMin[2];
		}

		int maxResult = Math.max(Math.max(maxArr[0], maxArr[1]), maxArr[2]);
		int minResult = Math.min(Math.min(minArr[0], minArr[1]), minArr[2]);

		System.out.println(maxResult + " " + minResult);
	}
}
