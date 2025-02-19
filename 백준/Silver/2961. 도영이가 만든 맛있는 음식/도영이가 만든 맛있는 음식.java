import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static int[] S;
	static int[] B;
	
	static int result = Integer.MAX_VALUE;
	
	static int resultS;
	static int resultB;
	
	static void func(int index) {
		resultS *= S[index];
		resultB += B[index];
		
		result = Math.min(result, Math.abs(resultS - resultB));
		
		int tempS = resultS;
		int tempB = resultB;
		for(int i = index + 1; i < N; i++) {
			func(i);
			resultS = tempS;
			resultB = tempB;
		}	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		S = new int[N];
		B = new int[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}	
		
		for(int i = 0; i < N; i++) {
			resultS = 1;
			resultB = 0;
			func(i);
		}
		
		System.out.println(result);
	}
}