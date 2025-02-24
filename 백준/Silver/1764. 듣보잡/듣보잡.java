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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> hs = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
			hs.add(br.readLine());			
		}
		
		ArrayList<String> arr = new ArrayList<>();
		for(int j = 0; j < M; j++) {
			String s = br.readLine();
			if (hs.contains(s)) {
				arr.add(s);
			}
		}
		System.out.println(arr.size());
		arr.sort(null);
		for(String s : arr) {
			System.out.println(s);
		}
	}
}