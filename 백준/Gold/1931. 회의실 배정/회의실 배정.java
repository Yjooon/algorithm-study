import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Pair {
  int first;
  int second;

  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }
}


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());

    StringTokenizer st1;
    List<Pair> pairs = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      st1 = new StringTokenizer(br.readLine());
      pairs.add(new Pair(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
    }
    pairs.sort(Comparator.comparingInt((Pair p) -> p.second).thenComparingInt(p -> p.first));

    int a = -1;
    int result = 0;

    for (Pair p : pairs) {
      if (a == -1) {
        a = pairs.get(0).second;
        result++;
        continue;
      }
      if (p.first == p.second && p.first <= a) {
        result++;
        continue;
      }
      if (p.first >= a) {
        a = p.second;
        result++;
      }
    }

    System.out.println(result);

  }
}
