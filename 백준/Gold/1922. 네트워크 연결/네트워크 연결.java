import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        int a;
        int b;
        int value;

        public Pair(int a, int b, int value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }
    }

    static int[] rootNum;

    // find() 연산을 통한 경로 압축
    static int find(int x) {
        if (rootNum[x] != x) {
            rootNum[x] = find(rootNum[x]); // 경로 압축
        }
        return rootNum[x];
    }

    // union() 연산을 통한 집합 병합
    static boolean isCycle(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) {
            return true; // 사이클 발생
        }

        // 두 집합 병합
        rootNum[rootB] = rootA;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Pair> arr = new ArrayList<Pair>();
        rootNum = new int[N + 1];

        // 각 노드를 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            rootNum[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        arr.sort(Comparator.comparing((Pair p) -> p.value));

        int result = 0;
        for (Pair p : arr) {
            if (!isCycle(p.a, p.b)) {
                result += p.value;
            }
        }

        System.out.println(result);
    }
}
