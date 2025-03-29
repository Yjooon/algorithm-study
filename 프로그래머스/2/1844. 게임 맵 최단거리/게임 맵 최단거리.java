import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static int M;
    
    static class Pair {
        int x;
        int y;
        int depth;
        Pair(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][] isVisited;
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        // System.out.println("N: " + N + "M: " + M);
        
        int answer = -1;
        Deque<Pair> q = new ArrayDeque<>();
        isVisited = new boolean[N][M];
        q.offer(new Pair(0, 0, 1));
        isVisited[0][0] = true;
        
        while(!q.isEmpty()) {
            Pair curr = q.poll();
            // System.out.println("N: " + curr.x + "M: " + curr.y);
            if (curr.x == N - 1 && curr.y == M - 1) {
                answer = curr.depth;
                break;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || isVisited[nx][ny] || maps[nx][ny] == 0)
                    continue;
                q.offer(new Pair(nx, ny, curr.depth + 1));
                isVisited[nx][ny] = true;
            }
        }
        
        return answer;
    }
}