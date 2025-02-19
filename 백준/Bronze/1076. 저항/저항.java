import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> set = new HashMap<>();

		set.put("black", 0);
		set.put("brown", 1);
		set.put("red", 2);
		set.put("orange", 3);
		set.put("yellow", 4);
		set.put("green", 5);
		set.put("blue", 6);
		set.put("violet", 7);
		set.put("grey", 8);
		set.put("white", 9);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first = br.readLine();
		String second = br.readLine();
		String third = br.readLine();

		long result = set.get(first) * 10 + set.get(second);
		result *= Math.pow(10, set.get(third));

		System.out.println(result);

		br.close();
	}
}