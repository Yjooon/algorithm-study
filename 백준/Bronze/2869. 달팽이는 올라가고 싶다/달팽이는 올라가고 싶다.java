import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
//		int day = 0;
//		int currentLocation = 0;
//		while(true) {
//			day++;
//			if( A <= B )
//				return;
//			currentLocation += A;
//			if (currentLocation >= V) {				
//				break;
//			}
//			currentLocation -= B;
//		}
		
//		int lastlocation = V - (V % (A - B));
		int day = (V - A) / (A - B) + 1;
		if ((V - A) % (A - B) != 0)
			day++;

		System.out.println(day);
	}
}
