import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws IOException {
    int C = Integer.parseInt(br.readLine());

    for (int i = 0; i < C; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int[] arr = new int[N];
      int total = 0;
      for (int j = 0; j < N; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
        total += arr[j];
      }
      float average = total / N;
      int good = 0;
      for (int j = 0; j < N; j++) {
        if (average < arr[j]) {
          good++;
        }
      }

      float result = ((float) good / (float) N) * 100;
      System.out.printf("%.3f", result);
      System.out.print("%");
      System.out.println();
    }

  }
}