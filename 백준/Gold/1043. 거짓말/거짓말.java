import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int truthPersonNum = Integer.parseInt(st.nextToken());
		Set<Integer> truthSet = new HashSet<>();
		for (int i = 0; i < truthPersonNum; i++) {
			truthSet.add(Integer.parseInt(st.nextToken()));
		}

		List<List<Integer>> parties = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partySize = Integer.parseInt(st.nextToken());

			List<Integer> party = new ArrayList<>();
			int firstPerson = Integer.parseInt(st.nextToken());
			party.add(firstPerson);

			for (int j = 1; j < partySize; j++) {
				int person = Integer.parseInt(st.nextToken());
				party.add(person);
				union(firstPerson, person);
			}
			parties.add(party);
		}

		Set<Integer> trueRoots = new HashSet<>();
		for (int person : truthSet) {
			trueRoots.add(find(person));
		}

		int count = 0;
		for (List<Integer> party : parties) {
			int partyRoot = find(party.get(0));
			if (!trueRoots.contains(partyRoot)) {
				count++;
			}
		}

		System.out.println(count);
	}

	static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) {
			parent[rootB] = rootA;
		}
	}
}
