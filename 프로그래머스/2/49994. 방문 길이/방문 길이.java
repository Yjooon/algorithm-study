import java.util.*;
import java.io.*;

class Solution {
    static int n;
    static HashMap<Pair, HashSet<Character>> isVisited;
    static Pair currL;

    static class Pair {
        int x;
        int y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Pair)) return false;
            Pair o = (Pair) other;
            return this.x == o.x && this.y == o.y;
        }
    }

    public int solution(String dirs) {
        n = dirs.length();
        isVisited = new HashMap<>();
        currL = new Pair(0, 0);
        answer = 0;

        for (int i = 0; i < n; i++) {
            char c = dirs.charAt(i);
            move(c);
        }

        return answer;
    }

    static int answer = 0;

    static void move(char c) {
        int nx = currL.x;
        int ny = currL.y;

        // 방향에 따른 좌표 계산
        if (c == 'U') ny++;
        else if (c == 'D') ny--;
        else if (c == 'R') nx++;
        else if (c == 'L') nx--;

        // 경계 검사
        if (nx < -5 || nx > 5 || ny < -5 || ny > 5) return;

        Pair from = new Pair(currL.x, currL.y);  // 이동 전 위치
        Pair to = new Pair(nx, ny);              // 이동 후 위치

        if (!isVisited.containsKey(from)) {
            isVisited.put(from, new HashSet<>());
        }
        if (!isVisited.containsKey(to)) {
            isVisited.put(to, new HashSet<>());
        }

        boolean already = isVisited.get(from).contains(c);
        if (!already) {
            isVisited.get(from).add(c);
            isVisited.get(to).add(reverse(c));
            answer++;
        }

        currL = to;  // 현재 위치 갱신 (불변 객체로)
    }

    static char reverse(char c) {
        if (c == 'U') return 'D';
        else if (c == 'D') return 'U';
        else if (c == 'R') return 'L';
        else return 'R';
    }
}
