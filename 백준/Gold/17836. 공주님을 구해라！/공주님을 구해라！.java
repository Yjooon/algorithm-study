import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int T;
	
	static int[][] maze;
	static boolean[][] isVisited;
	static Pair sword;
	
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };
	
	static class Pair {
		int a;
		int b;
		Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	static int bfs_sword() {
		for(int i = 0; i < N; i++)
			Arrays.fill(isVisited[i], false);
		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] {0, 0, 0});
		isVisited[0][0] = true;
		
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();

			if (maze[curr[0]][curr[1]] == 2) {
				return N - 1 - sword.a + M - 1 - sword.b + curr[2];
			}

			for(int i = 0; i < 4; i++) {
				int nextA = curr[0] + dx[i];
				int nextB = curr[1] + dy[i];
				
				if (nextA < 0 || nextB < 0 || nextA >= N|| nextB >= M)
					continue;
				else if (isVisited[nextA][nextB] || maze[nextA][nextB] == 1)
					continue;
				q.offer(new int[] {nextA, nextB, curr[2] + 1});
				isVisited[nextA][nextB] = true;

			}			
		}
		
		return Integer.MAX_VALUE;
	}
	
	static int bfs_normal() {
		for(int i = 0; i < N; i++)
			Arrays.fill(isVisited[i], false);
		Deque<int[]> q = new ArrayDeque<>();
		q.offerLast(new int[] {0, 0, 0});
		isVisited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			if (curr[0] == N - 1 && curr[1] == M - 1) {
				return curr[2];
			}

			for(int i = 0; i < 4; i++) {
				int nextA = curr[0] + dx[i];
				int nextB = curr[1] + dy[i];
				
				if (nextA < 0 || nextB < 0 || nextA >= N || nextB >= M)
					continue;
				else if (isVisited[nextA][nextB] || maze[nextA][nextB] == 1)
					continue;
				q.offer(new int[] {nextA, nextB, curr[2] + 1});
				isVisited[nextA][nextB] = true;
			}			
		}
		
		return Integer.MAX_VALUE;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        maze = new int[N][M];
        isVisited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine()); 
        	for(int j = 0; j < M; j++) {
        		int block = Integer.parseInt(st.nextToken());
        		maze[i][j] = block;
        		if (block == 2)
        			sword = new Pair(i, j);
        	}
        }
        
        int swordTime = bfs_sword();
        maze[sword.a][sword.b] = 1; //normal 탐색은 sword 없다고 가정하고 탐색 
        int normalTime = bfs_normal();
        
//        System.out.println("swordTime: " + swordTime);
//        System.out.println("normalTime: " + normalTime);
        int result = Math.min(swordTime, normalTime);
        if (result > T)
        	System.out.println("Fail");
        else  
        	System.out.println(result);
    }
}

