import java.util.*;
import java.io.*;

class Solution {
    static class Pair implements Comparable {
        int start;
        int end;
        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Object o) {
            Pair p = (Pair) o;
            return this.start - p.start;
        }
    }
    
    // 시간을 int 분으로 변환하기
    static int convertTime(String time) {
        int result = 0;
        char c1 = time.charAt(0);
        char c2 = time.charAt(1);
        char c3 = time.charAt(3);
        char c4 = time.charAt(4);
        
        result += (c1 - '0') * 10 * 60;
        result += (c2 - '0') * 60;
        result += (c3 - '0') * 10;
        result += (c4 - '0');
        
        return result;
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        int n = book_time.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            int start = convertTime(book_time[i][0]);
            int end = convertTime(book_time[i][1]);
            Pair p = new Pair(start, end);
            pq.offer(p);
        }
        
        ArrayList<ArrayList<Pair>> rooms = new ArrayList<>();
        Pair room = pq.poll();
        rooms.add(new ArrayList<>());
        rooms.get(0).add(room);
        
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            boolean flag = false;
            for(ArrayList<Pair> arr : rooms) {
                if (arr.get(arr.size() - 1).end + 10 <= p.start) {
                    arr.add(p);
                    flag = true;
                    break;
                } 
            }
            if (!flag) {
                rooms.add(new ArrayList<>());
                rooms.get(rooms.size() - 1).add(p);
            }
        }
        
        return rooms.size();
    }
}