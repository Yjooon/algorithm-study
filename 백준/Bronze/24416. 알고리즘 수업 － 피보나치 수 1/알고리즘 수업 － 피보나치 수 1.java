import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int count1 = 0;
  static int count2 = 0;

  static int fib(int n) {
    if (n == 1 || n == 2) {
      count1++;
      return 1;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }

  static int fibonacci(int n) {
    int[] arr = new int[n + 1];
    if (n == 1 || n == 2) {
      return 1;
    } else {
      arr[1] = 1;
      arr[2] = 1;
      for (int i = 3; i <= n; i++) {
        count2++;
        arr[i] = arr[i - 1] + arr[i - 2];
      }
      return arr[n];
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    fib(N);
    fibonacci(N);

    System.out.println(count1 + " " + count2);
  }
}
