import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] baseArr, tempArr;
    static ArrayList<CctvInfo> cctvLists;

    static class CctvInfo {
        int cctvNum, x, y;

        CctvInfo(int cctvNum, int x, int y) {
            this.cctvNum = cctvNum;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        baseArr = new int[N][M];
        tempArr = new int[N][M];
        cctvLists = new ArrayList<>();
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                baseArr[i][j] = Integer.parseInt(st.nextToken());
                tempArr[i][j] = baseArr[i][j];
                if (baseArr[i][j] >= 1 && baseArr[i][j] <= 5) {
                    cctvLists.add(new CctvInfo(baseArr[i][j], i, j));
                }
            }
        }

        backtrack(0);
        System.out.println(result);
    }

    static void backtrack(int index) {
        if (index == cctvLists.size()) {
            updateResult();
            return;
        }

        CctvInfo cctv = cctvLists.get(index);
        int x = cctv.x, y = cctv.y;
        int cctvNum = cctv.cctvNum;

        int[][] directions = getDirections(cctvNum);
        
        for (int[] dirs : directions) {
            ArrayList<int[]> modified = new ArrayList<>();
            for (int dir : dirs) {
                see(x, y, dir, modified);
            }
            backtrack(index + 1);
            recover(modified);
        }
    }

    static int[][] getDirections(int cctvNum) {
        if (cctvNum == 1) return new int[][]{{0}, {1}, {2}, {3}};
        if (cctvNum == 2) return new int[][]{{0, 2}, {1, 3}};
        if (cctvNum == 3) return new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        if (cctvNum == 4) return new int[][]{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}};
        if (cctvNum == 5) return new int[][]{{0, 1, 2, 3}};
        return new int[0][];
    }

    static void see(int x, int y, int dir, ArrayList<int[]> modified) {
        int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dy = {0, 1, 0, -1};

        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M || baseArr[nx][ny] == 6) break;
            if (baseArr[nx][ny] >= 1 && baseArr[nx][ny] <= 5) continue; // 다른 CCTV는 무시

            if (tempArr[nx][ny] == 0) {
                tempArr[nx][ny] = -1; // 감시 구역 표시
                modified.add(new int[]{nx, ny});
            }
        }
    }

    static void recover(ArrayList<int[]> modified) {
        for (int[] pos : modified) {
            tempArr[pos[0]][pos[1]] = 0; // 원래 상태로 복구
        }
    }

    static void updateResult() {
        int blindSpots = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempArr[i][j] == 0) blindSpots++;
            }
        }
        result = Math.min(result, blindSpots);
    }
}