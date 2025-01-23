import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static char[][] arr;
  static int R;
  static int C;
  static boolean flag;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    arr = new char[R][C];
    for (int i = 0; i < R; i++) {
      String s = br.readLine();
      for (int j = 0; j < C; j++) {
        arr[i][j] = s.charAt(j);
      }
    }

    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (arr[i][j] == '.' || arr[i][j] == 'S' || arr[i][j] == 'D') {
          continue;
        } else if (arr[i][j] == 'W') {
          setD(i, j);
          if (flag) {
            System.out.println(0);
            return;
          }
        }
      }
    }

    System.out.println(1);
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }

  }

  public static void setD(int n, int m) {
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};

    for (int i = 0; i < 4; i++) {
      int nx = n + dx[i];
      int ny = m + dy[i];

      if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
        continue;
      } else {
        if (arr[nx][ny] == 'S') {
          flag = true;
          return;
        } else if (arr[nx][ny] == '.') {
          arr[nx][ny] = 'D';
        } else {
          continue;
        }
      }
    }
  }
}
