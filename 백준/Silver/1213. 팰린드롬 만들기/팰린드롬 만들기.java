
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static char itoc(int i) {
		char c = (char) (i + 'A');
		return c;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int[] arr = new int[26];
		char[] result = new char[s.length()];

		for (int i = 0; i < s.length(); i++) {
			arr[s.charAt(i) - 'A']++;
		}

		boolean flag = false;
		int index = 0;
		for (int i = 0; i < 26; i++) {
			if (arr[i] == 0)
				continue;
			
			if (arr[i] % 2 == 1) {
				if (flag) {
					System.out.println("I'm Sorry Hansoo");
					return;
				}
				flag = true;
				result[result.length / 2] = itoc(i);
				for(int j = 0; j < arr[i] / 2; j++) {
					result[result.length - index - 1] = itoc(i);
					result[index] = itoc(i);
					index++;
				}
			} else {
				for(int j = 0; j < arr[i]/2; j++) {
					result[result.length - index - 1] = itoc(i);
					result[index] = itoc(i);	
					index++;
				}
			} 
		}
		System.out.println(result);
	}
}