import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int result = 10001;
	static int N, M;
	static int[][] maze;
	static boolean[][] isVisited;
	
	static void bfs(int startX, int startY) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[] {startX, startY, 1});
        isVisited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            if (x == N - 1 && y == M - 1) {
            	result = distance;
            }
            
            int dx[] = { -1, 0, 0, 1 };
    		int dy[] = { 0, 1, -1, 0 };

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < N && newY >= 0 && newY < M) {
                    if (!isVisited[newX][newY] && maze[newX][newY] == 1) {
                        queue.offer(new int[] {newX, newY, distance + 1});
                        isVisited[newX][newY] = true;
                    }
                }
            }
        }
	}
	
	static void searchMaze(int n, int m, int distance) {
		if (n >= N || m >= M || n < 0 || m < 0 || maze[n][m] == 0 || isVisited[n][m] == true) {
			return;
		}
		if (n == (N - 1) && m == (M - 1)) {
			result = Math.min(distance, result);
			return;
		}

		isVisited[n][m] = true;
		int dx[] = { -1, 0, 0, 1 };
		int dy[] = { 0, 1, -1, 0 };
		
		for (int i = 0; i < 4; i++) {
			int newN = n + dx[i];
			int newM = m + dy[i];
			searchMaze(newN, newM, distance + 1);				
		}	
		isVisited[n][m] = false;
		
		
	} 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		isVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = input.charAt(j) - '0';
			}
		}

//		searchMaze(0, 0, 1);
		bfs(0, 0);

		System.out.println(result);

	}
}
