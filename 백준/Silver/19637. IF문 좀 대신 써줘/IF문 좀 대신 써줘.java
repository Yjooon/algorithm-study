import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer> arr;
    static ArrayList<String> title;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        title = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            if (arr.isEmpty() || arr.get(arr.size() - 1) != value) {
                arr.add(value);
                title.add(t);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int input = Integer.parseInt(br.readLine());
            sb.append(title.get(solve(input))).append("\n");
        }
        System.out.print(sb);
    }

    static int solve(int n) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
