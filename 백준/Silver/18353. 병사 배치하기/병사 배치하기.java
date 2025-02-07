import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                int N = Integer.parseInt(br.readLine());
        int[] power = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        int[] reversedPower = new int[N];
        for (int i = 0; i < N; i++) {
            reversedPower[i] = power[N - i - 1];
        }

        List<Integer> lis = new ArrayList<>();
        for (int num : reversedPower) {
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) {
                pos = -(pos + 1); 
            }
            if (pos == lis.size()) {
                lis.add(num);
            } else {
                lis.set(pos, num);
            }
        }

        int result = N - lis.size();
        System.out.println(result);
    }
}
