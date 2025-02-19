import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static ArrayList<Integer>[] arr;
	static boolean[] isVisited;
	static boolean result = false;

	static void dfs(int node) {
		if (isVisited[node] || result)
			return;
		if (node == 99) {
			result = true;
			return;
		}

		isVisited[node] = true;

		for (int i : arr[node]) {
			dfs(i);
		}
	}

	static void solve() {
		isVisited[0] = true;
		for (int i : arr[0]) {
			dfs(i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int test_caseNum = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken()); // 길의 총 개수
			result = false;

			arr = new ArrayList[100];
			isVisited = new boolean[100];
			Arrays.fill(isVisited, false);
			for (int i = 0; i < 100; i++) {
				arr[i] = new ArrayList<>();
			}

			int a, b;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				arr[a].add(b);
			}

			solve();

			System.out.println("#" + test_case + " " + (result ? 1 : 0));
		}
	}
}