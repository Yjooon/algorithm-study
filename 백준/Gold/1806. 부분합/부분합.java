import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		int[] prefix = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i-1] + arr[i];
		}
		
		int left = 0;
		int right = 1;
		int result = Integer.MAX_VALUE;
		boolean flag = false;
		while(right <= N) {
			if (prefix[right] - prefix[left] >= S) {
				flag = true;
				result = Math.min(result, right - left);
				left++;
				continue;
			}
			right++;
		}
		System.out.println(flag ? result : 0);
	}
}