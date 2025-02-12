import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static class Pair {
    int x;
    int y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];

    for (int i = 0; i < K; i++) {
      st = new StringTokenizer(br.readLine());

      int startX = Integer.parseInt(st.nextToken());
      int startY = Integer.parseInt(st.nextToken());
      int endX = Integer.parseInt(st.nextToken());
      int endY = Integer.parseInt(st.nextToken());

      for (int j = startX; j < endX; j++) {
        for (int k = startY; k < endY; k++) {
          map[j][k] = 1;
        }
      }
    }

    int dx[] = {-1, 0, 1, 0};
    int dy[] = {0, 1, 0, -1};

    int[] section = new int[100];
    Arrays.fill(section, Integer.MAX_VALUE);
    int list = 0;

    Deque<Pair> q = new ArrayDeque<Pair>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (map[i][j] != 0)
          continue;
        int count = 0;
        q.add(new Pair(i, j));
        map[i][j] = 2;

        while (!q.isEmpty()) {
          count++;
          Pair p = q.poll();
          int currX = p.x;
          int currY = p.y;

          for (int k = 0; k < 4; k++) {
            int nextX = currX + dx[k];
            int nextY = currY + dy[k];
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || map[nextX][nextY] != 0) {

              continue;
            }
            // System.out.println("nextX: " + nextX + " nextY: " + nextY + " count: " + count);

            q.add(new Pair(nextX, nextY));
            map[nextX][nextY] = 2;
          }
        }
        // System.out.println("while문 끝!!!");
        section[list++] = count;
      }
    }

    Arrays.sort(section);

    System.out.println(list);
    for (int i = 0; i < list; i++) {
      System.out.print(section[i] + " ");
    }

  }
}
