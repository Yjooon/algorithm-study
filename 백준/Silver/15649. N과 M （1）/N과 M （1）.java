import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offerLast(i);
            backtrack(queue);
        }
    }

    static void backtrack(Deque<Integer> q) {
        if (q.size() == M) { 
            for (Integer num : q) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!q.contains(i)) {
                q.offerLast(i);
                backtrack(q);
                q.pollLast();
            }
        }
    }
}
