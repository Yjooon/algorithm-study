import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://bowbowbow.tistory.com/6

public class Main {
  public static int kmpSearch(String text, String pattern) {
    int[] lps = computeLPS(pattern);
    int n = text.length();
    int m = pattern.length();
    int i = 0, j = 0;

    while (i < n) {
      if (text.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;
        if (j == m)
          return 1; // 패턴 찾으면 1 반환
      } else {
        if (j != 0)
          j = lps[j - 1];
        else
          i++;
      }
    }

    return 0; // 패턴을 찾지 못하면 0 반환
  }

  public static int[] computeLPS(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int prefixLength = 0; // 현재까지 일치한 접두사의 길이

    for (int i = 1; i < m; i++) { // i는 1부터 시작 (0번째는 항상 0)
      while (prefixLength > 0 && pattern.charAt(i) != pattern.charAt(prefixLength)) {
        prefixLength = lps[prefixLength - 1]; // 이전 일치한 부분으로 돌아가기 (점프)
      }

      if (pattern.charAt(i) == pattern.charAt(prefixLength)) {
        prefixLength++; // 접두사 길이 증가
        lps[i] = prefixLength; // 현재 위치의 LPS 값 저장
      } else {
        lps[i] = 0; // 일치하는 접두사X → 0 저장
      }
    }

    return lps;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    String target = br.readLine();

    System.out.println(kmpSearch(s, target));
  }
}
