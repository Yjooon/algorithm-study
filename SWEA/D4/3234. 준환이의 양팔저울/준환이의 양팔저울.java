import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N, result;
	static int[] weights;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			weights = new int[N];
			visited = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			result = 0;
			permute(0, new int[N]);
			System.out.println("#" + test_case + " " + result);
		}
	}

	static void permute(int depth, int[] perm) {
		if (depth == N) {
			dfs(0, 0, 0, perm);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				perm[depth] = weights[i];
				permute(depth + 1, perm);
				visited[i] = false;
			}
		}
	}

	static void dfs(int index, int left, int right, int[] perm) {
		if (right > left)
			return;

		if (index == N) {
			result++;
			return;
		}

		// 왼쪽에 올리는 경우
		dfs(index + 1, left + perm[index], right, perm);
		// 오른쪽에 올리는 경우
		dfs(index + 1, left, right + perm[index], perm);
	}
}
