import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int[] up = new int[H + 1];
		int[] down = new int[H + 1];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (i % 2 == 0) {
				down[arr[i]]++;
			} else {
				up[arr[i]]++;
			}
		}
		
		for (int i = H - 1; i >= 1; i--) {
			down[i] += down[i + 1];
			up[i] += up[i + 1];
		}

		int[] prefix = new int[H + 1];
		for (int i = 1; i <= H; i++) {
			prefix[i] = down[i] + up[H - i + 1];
		}

		int result = Integer.MAX_VALUE;
		int count = 0;
		for (int i = 1; i <= H; i++) {
			result = Math.min(result, prefix[i]);
		}
		for (int i = 1; i <= H; i++) {
			if (prefix[i] == result)
				count++;
		}
		System.out.println(result + " " + count);
	}
}
