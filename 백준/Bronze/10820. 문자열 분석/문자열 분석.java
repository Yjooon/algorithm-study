import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s;

    while ((s = br.readLine()) != null) {
      int lowerCount = 0;
      int upperCount = 0;
      int numberCount = 0;
      int whiteCount = 0;


      for (int i = 0; i < s.length(); i++) {
        if ('a' <= s.charAt(i) && 'z' >= s.charAt(i)) {
          lowerCount++;
          continue;
        }
        if ('A' <= s.charAt(i) && 'Z' >= s.charAt(i)) {
          upperCount++;
          continue;
        }
        if ('0' <= s.charAt(i) && '9' >= s.charAt(i)) {
          numberCount++;
          continue;
        }
        if (' ' == s.charAt(i)) {
          whiteCount++;
          continue;
        }
      }

      System.out.println(lowerCount + " " + upperCount + " " + numberCount + " " + whiteCount);
    }
  }
}
