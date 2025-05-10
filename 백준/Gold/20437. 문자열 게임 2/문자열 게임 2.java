import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			int K = Integer.parseInt(br.readLine());

			int minArr = 10001;
			int maxArr = 0;
			boolean flag = false;

			Deque<Integer>[] arr = new ArrayDeque[26];
			for (int i = 0; i < 26; i++) {
			    arr[i] = new ArrayDeque<>();
			}
			
			for (int j = 0; j < s.length(); j++) {
				int curr = s.charAt(j) - 'a';
				arr[curr].offer(j);
				if (arr[curr].size() > K) {
					arr[curr].pollFirst();
				}
				if (arr[curr].size() == K) {
					flag = true;
					int diff = arr[curr].peekLast() - arr[curr].peekFirst() + 1;
					minArr = Math.min(minArr, diff);
					maxArr = Math.max(maxArr, diff);
				}
			}

			if (flag) {
				System.out.println(minArr + " " + maxArr);
			} else {
				System.out.println(-1);
			}

		}
	}
}
