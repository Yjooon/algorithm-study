import java.util.*;
import java.io.*;

public class Main {
    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 1; // 사과 위치 보정
        }

        Deque<Integer> timeq = new ArrayDeque<>();
        Deque<Character> dirq = new ArrayDeque<>();

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            timeq.offer(t);
            dirq.offer(d);
        }

        int currentDir = 1; // 0: up, 1: right, 2: down, 3: left
        int[][] dir = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };

        int turn = 0;
        Deque<Pair> snake = new ArrayDeque<>();
        snake.offer(new Pair(0, 0));
        map[0][0] = 2;

        while (true) {
            Pair head = snake.peekLast();
            int nx = head.x + dir[currentDir][0];
            int ny = head.y + dir[currentDir][1];

            // 충돌 검사
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
                turn++;
                break;
            }

            if (map[nx][ny] == 1) { // 사과
                map[nx][ny] = 2;
                snake.offer(new Pair(nx, ny));
            } else { // 그냥 이동
                map[nx][ny] = 2;
                snake.offer(new Pair(nx, ny));
                Pair tail = snake.poll();
                map[tail.x][tail.y] = 0;
            }

            turn++;

            // 회전 시점 확인 (이동한 후 회전)
            if (!timeq.isEmpty() && turn == timeq.peek()) {
                timeq.poll();
                char dirTurn = dirq.poll();
                if (dirTurn == 'D') {
                    currentDir = (currentDir + 1) % 4;
                } else {
                    currentDir = (currentDir + 3) % 4;
                }
            }
        }

        System.out.println(turn);
    }
}
