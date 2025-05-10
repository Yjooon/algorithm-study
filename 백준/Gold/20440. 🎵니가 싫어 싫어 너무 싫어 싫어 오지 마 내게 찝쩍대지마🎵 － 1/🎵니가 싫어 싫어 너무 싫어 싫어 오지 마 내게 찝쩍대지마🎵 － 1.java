import java.io.*;
import java.util.*;

public class Main {
    static class Event implements Comparable<Event> {
        int time;
        int delta; // 입장: +1, 퇴장: -1

        Event(int time, int delta) {
            this.time = time;
            this.delta = delta;
        }

        @Override
        public int compareTo(Event o) {
            if (this.time == o.time) return this.delta - o.delta; // 입장 먼저 처리
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int enter = Integer.parseInt(st.nextToken());
            int exit = Integer.parseInt(st.nextToken());
            
            events.add(new Event(enter, 1));  // 입장
            events.add(new Event(exit, -1));  // 퇴장
        }

        Collections.sort(events);

        int current = 0;
        int maxCount = 0;
        int startTime = 0;
        int endTime = 0;
        boolean tracking = false;

        int i = 0;
        while (i < events.size()) {
            int time = events.get(i).time;

            while (i < events.size() && events.get(i).time == time) {
                current += events.get(i).delta;
                i++;
            }

            if (current > maxCount) {
                maxCount = current;
                startTime = time;
                endTime = time;
                tracking = true;
            } else if (current < maxCount && tracking) {
                endTime = time;
                tracking = false;
            } else if (current == maxCount && tracking) {
                endTime = time;
            }
        }

        System.out.println(maxCount);
        System.out.println(startTime + " " + endTime);
    }
}
