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
		int X = Integer.parseInt(br.readLine());
		int Y = Integer.parseInt(br.readLine());
		int result;
		if (X > 0 && Y > 0) {
			result = 1;
		} else if (X > 0 && Y < 0) {
			result = 4;
		} else if (X < 0 && Y > 0) {
			result = 2;
		} else {
			result = 3;
		}
		
		
		System.out.println(result);
	}
}