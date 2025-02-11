import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static class Pair {
    int end;
    int shortcut;

    Pair(int end, int shortcut) {
      this.end = end;
      this.shortcut = shortcut;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int D = Integer.parseInt(st.nextToken());
    int Start;
    int End;
    int Shortcut;

    ArrayList<Pair>[] arr = new ArrayList[10001];
    for (int i = 0; i < 10001; i++) {
      arr[i] = new ArrayList<Pair>();
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      Start = Integer.parseInt(st.nextToken());
      End = Integer.parseInt(st.nextToken());
      Shortcut = Integer.parseInt(st.nextToken());

      // 역주행 불가하므로 넘어가면 continue;
      if (End > D)
        continue;

      // 지름길이 더 느리면 continue;
      if (End - Start <= Shortcut)
        continue;

      arr[Start].add(new Pair(End, Shortcut));
    }

    int[] result = new int[D + 1];
    Arrays.fill(result, Integer.MAX_VALUE);
    result[0] = 0;
    for (int j = 0; j <= D; j++) {
      int end;
      int shortcut;

      if (j > 0)
        result[j] = Math.min(result[j], result[j - 1] + 1);

      for (int i = 0; i < arr[j].size(); i++) {
        Pair p = arr[j].get(i);
        end = p.end;
        shortcut = p.shortcut;

        result[end] = Math.min(result[end], result[j] + shortcut);
      }
    }
    System.out.println(result[D]);
  }
}
