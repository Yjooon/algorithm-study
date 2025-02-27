import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<Character> set = new HashSet<Character>();

		int N = Integer.parseInt(br.readLine());
		int result = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			set.clear();
			boolean flag = true;
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if (j > 0 && c == s.charAt(j - 1)) {
					continue;
				}
				if (set.contains(c)) {
					flag = false;
					break;
				}
				set.add(c);
			}
			if (flag) {
				result++;
			}
		}
		
		System.out.println(result);
	}
}