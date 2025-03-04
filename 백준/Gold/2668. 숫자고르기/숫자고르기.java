import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static int[] arr;
	static boolean[] isVisited;
	static int temp;

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		isVisited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		tempArr1 = new ArrayList<>();
		tempArr2 = new ArrayList<>();
		resultArr = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			tempArr1.clear();
			tempArr2.clear();
			dfs(i);
		}

		System.out.println(resultArr.size());
		resultArr.sort(null);
		for (int i : resultArr) {
			System.out.println(i);
		}
	}

	static ArrayList<Integer> tempArr1;
	static ArrayList<Integer> tempArr2;
	static ArrayList<Integer> resultArr;

	static void dfs(int n) {
		if (isVisited[n]) {
			tempArr1.sort(null);
			tempArr2.sort(null);
			if (tempArr1.equals(tempArr2)) {
				resultArr.addAll(tempArr1);
			} else {
				for (int i : tempArr1) {
					isVisited[i] = false;
				}
			}
			return;
		}
		tempArr1.add(n);
		tempArr2.add(arr[n]);
		isVisited[n] = true;
		dfs(arr[n]);
	}
}