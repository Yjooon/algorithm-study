
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {
	public static class Tree {
		String name;
		int num;

		Tree(String t, int num) {
			this.name = t;
			this.num = num;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> trees = new HashMap<String, Integer>();

		String tree;
		int count = 0;
		while ((tree = br.readLine()) != null && !tree.isEmpty()) {
			if (trees.containsKey(tree)) {
				trees.put(tree, trees.get(tree) + 1);
			} else {
				trees.put(tree, 1);
			}
			count++;
		}

		List<Tree> t = new ArrayList<>();
		while (!trees.isEmpty()) {
			trees.entrySet().stream().findFirst().ifPresent(entry -> {
				String key = entry.getKey();
				Integer value = trees.remove(key);
				t.add(new Tree(key, value));
			});
		}
		t.sort(Comparator.comparing((Tree temp) -> temp.name));

		for (Tree tr : t) {
			System.out.print(tr.name + " ");
			System.out.printf("%.4f", (double) tr.num * 100 / (double) count);
			System.out.println();
		}
	}
}
