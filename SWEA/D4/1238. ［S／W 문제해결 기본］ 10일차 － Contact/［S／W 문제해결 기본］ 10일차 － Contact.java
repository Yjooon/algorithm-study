import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	static class Pair {
		int node;
		int depth;

		Pair(int node, int depth) {
			this.node = node;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int startAt = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer>[] arr = new ArrayList[101];
			boolean[] isVisited = new boolean[101];
			int[] nodeDepth = new int[101];
			for(int i = 0; i < 101; i++) {
				arr[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < length / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				arr[from].add(to);
			}
			
			//bfs
			Deque<Pair> q = new ArrayDeque<>();
			q.add(new Pair(startAt, 0));
			
			while(!q.isEmpty()) {
				Pair p = q.poll();
				int currNode = p.node;
				int currDepth = p.depth;
				nodeDepth[currNode] = currDepth;

				isVisited[currNode] = true;

				for(int i : arr[currNode]) {
					if (isVisited[i])
						continue;
					
					q.add(new Pair(i, currDepth + 1));
					isVisited[i] = true;
				}
			}
			
			int resultNode = 0;
			int resultDepth = 0;
			for(int i = 1; i <= 100; i++) {
				if (resultDepth <= nodeDepth[i]) {
					resultNode = i;
					resultDepth = nodeDepth[i];
				}
					
			}
			
			System.out.println("#" + test_case + " " + resultNode);
		}
	}
}