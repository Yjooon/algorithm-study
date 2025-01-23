import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] A;
	static int[][] B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		

		A = new int[N][M];
		B = new int[N][M];

		int result = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				A[i][j] = s.charAt(j) - '0';
			}
		}for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				B[i][j] = s.charAt(j) - '0';
			}
		}
		
		if (N < 3 || M < 3) {
			if (Arrays.deepEquals(A, B)) {
				System.out.println(0);			
			} else {
				System.out.println(-1);
			}
			return;
		}
		
		for(int i = 1; i < N - 1; i++) {
			for(int j = 1; j < M - 1; j++) {
				if (A[i-1][j-1] == B[i-1][j-1]) {
					continue;
				}
				else {
					reverseMatrix(i, j);
					result++;
				}
			}
		}
		
		if (Arrays.deepEquals(A, B)) {
			System.out.println(result);			
		} else {
			System.out.println(-1);
		}
		
	}
	public static void reverseMatrix(int n, int m) {
		int[] dx = {-1, 0, 1};
		int[] dy = {-1, 0, 1};
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(A[n + dx[i]][m + dx[j]] == 1) {
					A[n + dx[i]][m + dx[j]] = 0;
				} else {
					A[n + dx[i]][m + dx[j]] = 1;
				}
			}
		}
	}
}
