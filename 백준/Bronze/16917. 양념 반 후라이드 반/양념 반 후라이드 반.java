import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()); //양념 
		int B = Integer.parseInt(st.nextToken()); //후라이드 
		int C = Integer.parseInt(st.nextToken()); //반반 
		int X = Integer.parseInt(st.nextToken()); //양념치킨 최소 개수 
		int Y = Integer.parseInt(st.nextToken()); //후라이드 최소 개수 
		//반반 치킨을 두 마리 구입해 양념 한 개 와 후라이드 한 개 만들기 가능 
		
		int result = 0;
		
		int case1 = A * X + B * Y;
		int case2 = Math.max(X, Y) * C * 2;
		int case3 = Math.min(X, Y) * C * 2;
		
		if (X > Y) {
			case3 += A * (X - Y);
		} else {
			case3 += B * (Y - X);
		}
		
		result = Math.min(Math.min(case1, case2), case3);
		
		System.out.println(result);
	}
}

