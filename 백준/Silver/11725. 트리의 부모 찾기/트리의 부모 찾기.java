import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static int N;
  static ArrayList<Integer>[] arr;
  static int[] result;
  static boolean[] isVisited;

  public static void find() {
    Deque<Integer> queue = new ArrayDeque<>();
    queue.offer(1);
    isVisited[1] = true;
    while (!queue.isEmpty()) {
      int curr = queue.poll();
      for (int i : arr[curr]) {
        if (isVisited[i])
          continue;
        queue.offer(i);
        isVisited[i] = true;
        result[i] = curr;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new ArrayList[N + 1];
    for (int i = 0; i <= N; i++) {
      arr[i] = new ArrayList<>();
    }
    result = new int[N + 1];
    isVisited = new boolean[N + 1];
    result[1] = 1;

    for (int i = 0; i < N - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      arr[a].add(b);
      arr[b].add(a);
    }

    find();

    for (int i = 2; i <= N; i++)
      System.out.println(result[i]);
  }
}
