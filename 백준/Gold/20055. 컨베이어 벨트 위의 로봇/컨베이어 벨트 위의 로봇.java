import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] belt;
    static boolean[] robot;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2 * N];
        robot = new boolean[N];
        result = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            result++;

            rotate();
            moveRobot();
            loadRobot();

            if (!check()) break;
        }

        System.out.println(result);
    }

    static void rotate() {
        int last = belt[2 * N - 1];
        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = last;

        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = false; 
        robot[N - 1] = false;
    }

    static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (robot[i] && !robot[i + 1] && belt[i + 1] > 0) {
                robot[i] = false;
                robot[i + 1] = true;
                belt[i + 1]--;
            }
        }
        robot[N - 1] = false;
    }

    static void loadRobot() {
        if (belt[0] > 0) {
            robot[0] = true;
            belt[0]--;
        }
    }

    static boolean check() {
        int count = 0;
        for (int i = 0; i < 2 * N; i++) {
            if (belt[i] == 0) count++;
        }
        return count < K;
    }
}
