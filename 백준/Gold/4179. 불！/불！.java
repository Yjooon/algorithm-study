import java.util.*;
import java.io.*;

public class Main {

	static class Pair {
		int x;
		int y;
		int turn;
		Pair(int x, int y, int turn) {
			this.x = x;
			this.y = y;
			this.turn = turn;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[n][m];
		int[][] fMap = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				fMap[i][j] = Integer.MAX_VALUE;
			}
		}
		
		Deque<Pair> q = new ArrayDeque<>();
		Deque<Pair> fq = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			for(int j = 0; j < m; j++) {
				char curr = input.charAt(j);
				map[i][j] = curr;
				if (curr == 'J') {
					q.offer(new Pair(i, j, 0));
				} else if (curr == 'F') {
					fq.offer(new Pair(i, j, 0));
				}
			}
		}
		
		int dx[] = {-1, 1, 0, 0};
		int dy[] = {0, 0, -1, 1};
		boolean[][] isVisited = new boolean[n][m];
		boolean[][] isFireVisited = new boolean[n][m];

		int result = 0;
		while(!fq.isEmpty()) {
			Pair f = fq.poll();
			for(int i = 0; i < 4; i++) {
				int nFx = f.x + dx[i];
				int nFy = f.y + dy[i];
				int nFt = f.turn + 1;
				
				if (nFx < 0 || nFy < 0 || nFx >= n || nFy >= m || map[nFx][nFy] == '#' || isFireVisited[nFx][nFy] || nFt >= fMap[nFx][nFy]) {
					continue;
				}
				fMap[nFx][nFy] = nFt;
				fq.offer(new Pair(nFx, nFy, nFt));
				isFireVisited[nFx][nFy] = true;
			}
		}		
		
		boolean flag = false;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int nt = p.turn + 1;
				if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
					flag = true;
//					System.out.println("nx: " + nx + " ny: " + ny);
					result = nt;
					break;
				}

				if (nt >= fMap[nx][ny] || map[nx][ny] != '.' || isVisited[nx][ny]) {
//					System.out.println("nx: " + nx + " ny: " + ny + " nt: " + nt + " fmap: " + fMap[nx][ny]);
					continue;
				}
				q.offer(new Pair(nx, ny, nt));
				isVisited[nx][ny] = true;
			}
			if (flag)
				break;
		}
		
		if (flag) {
			System.out.println(result);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
}