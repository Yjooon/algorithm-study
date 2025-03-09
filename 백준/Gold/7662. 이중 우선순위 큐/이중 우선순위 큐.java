import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int k = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> minHeap = new PriorityQueue<>();
			PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
			HashMap<Integer, Integer> map = new HashMap<>();

			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				if (op.equals("I")) {
					minHeap.add(n);
					maxHeap.add(n);
					map.put(n, map.getOrDefault(n, 0) + 1);
				} else if (op.equals("D")) {
					if (map.isEmpty())
						continue;

					PriorityQueue<Integer> targetHeap = (n == 1) ? maxHeap : minHeap;
					removeValid(targetHeap, map);
				}
			}

			if (map.isEmpty()) {
				bw.write("EMPTY\n");
			} else {
				int max = removeValid(maxHeap, map);
				int min = (map.isEmpty()) ? max : removeValid(minHeap, map);
				bw.write(max + " " + min + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static int removeValid(PriorityQueue<Integer> heap, HashMap<Integer, Integer> map) {
		while (!heap.isEmpty()) {
			int num = heap.poll();
			if (map.containsKey(num)) {
				if (map.get(num) == 1) {
					map.remove(num);
				} else {
					map.put(num, map.get(num) - 1);
				}
				return num;
			}
		}
		return 0;
	}
}
