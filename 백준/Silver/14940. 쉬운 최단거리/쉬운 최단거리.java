import java.io.*;
import java.util.*;

public class Main {
	
	static class Pair {
		int x;
		int y;
		int distance;
		Pair(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		boolean[][] isVisited = new boolean[n][m];
		
		Pair start = new Pair(0, 0, 0);
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1)
					input *= -1;
				map[i][j] = input;
				if (map[i][j] == 2) {
					start.x = i;
					start.y = j;
					start.distance = 0;
				}
			}
		}
		
		map[start.x][start.y] = 0;
		Deque<Pair> q = new ArrayDeque<>();
		q.offer(start);
		isVisited[start.x][start.y] = false;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				
				if (nx < 0 || ny < 0 || nx >= n || ny >= m || isVisited[nx][ny]) {
					continue;
				}
				if (map[nx][ny] == 0) {
					isVisited[nx][ny] = true;
					continue;
				}
				q.offer(new Pair(nx, ny, p.distance + 1));
				map[nx][ny] = p.distance + 1;
				isVisited[nx][ny] = true;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}