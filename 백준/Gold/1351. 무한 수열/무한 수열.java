import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static long N;
	static long P;
	static long Q;

	static long find(long n) {
		if (n >= 1000000) {
			return find(n / P) + find(n / Q);
		}
		if (arr[(int) n] != 0) {
			return arr[(int) n];
		}
		int p = (int) (n / P);
		int q = (int) (n / Q);

		arr[(int) n] = find(p) + find(q);

		return arr[(int) n];
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());

		arr = new long[1000000];
		arr[0] = 1;

		System.out.println(find(N));
	}
}
