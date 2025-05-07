import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		int[][] arr = new int[R + 1][C + 1];
		int[][] prefix = new int[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				prefix[i][j] = prefix[i][j - 1] + arr[i][j];
			}
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int sum = 0;
			for (int r = r1; r <= r2; r++) {
				sum += prefix[r][c2] - prefix[r][c1 - 1];
			}
			bw.write((sum / ((r2 - r1 + 1) * (c2 - c1 + 1))) + "\n");
		}
		bw.close();
	}
}