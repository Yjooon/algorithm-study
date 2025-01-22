import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int result = 1;
		
		int digit = 10;
		int alpha = 26;
		for(int i = 0; i < s.length(); i++) {			
			if(i > 0) {
				if (s.charAt(i - 1) == s.charAt(i)) {
					if (s.charAt(i) == 'd') { 
						result *= digit - 1;
					} else {
						result *= alpha - 1;
					}
				} else {
					if (s.charAt(i) == 'd') { 
						result *= digit;
					} else {
						result *= alpha;
					}
				}
			} else {
				if (s.charAt(i) == 'd') { 
					result *= digit;
				} else {
					result *= alpha;
				}
			}
		}
		
		System.out.println(result);
	}
}
