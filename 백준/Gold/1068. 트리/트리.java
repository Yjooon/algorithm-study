import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int result;
	static Map<Integer, Set<Integer>> map;
	static int[] parent;
	static int rootNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new HashMap<>();

		StringTokenizer st = new StringTokenizer(br.readLine());

		parent = new int[N];
		result = 0;
		rootNode = 0;
		for (int i = 0; i < N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			map.put(i, new HashSet<>());
		}
		for (int i = 0; i < N; i++) {

			if (parent[i] != -1)
				map.get(parent[i]).add(i);
			else {
				rootNode = i;
			}
		}
		int removeAt = Integer.parseInt(br.readLine());
		if (removeAt != rootNode) {
			map.get(parent[removeAt]).remove(removeAt);
			map.remove(removeAt);

			dfs(rootNode);
		}

		System.out.println(result);
	}

	static void dfs(int node) {
		if (map.get(node).isEmpty()) {
			result++;
			return;
		}

		for (int i : map.get(node)) {
			dfs(i);
		}
	}

}
