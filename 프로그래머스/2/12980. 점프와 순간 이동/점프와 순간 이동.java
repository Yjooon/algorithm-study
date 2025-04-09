import java.util.*;

public class Solution {
    static int result = 0;
    static void bfs(int n) {
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(n);
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            if (curr < 2) {
                result += curr;
                return;
            }
            while(curr % 2 == 0) {
                curr /= 2;
            }
            if (curr - 1 >= 0) {
                result++;
            }
                
            if (curr % 2 == 0) {
                q.offer(curr/2);
            } else if(curr - 1 >= 0) {
                result++;
                q.offer((curr - 1)/2);
            }   
        }
    }
    public int solution(int n) {
        // bfs(n);
        int result = 0;
        while (n >= 2) {
            while(n % 2 == 0) {
                n /= 2;
            }
            result++;
            n -= 1;
        }
        result += n;
    
        
        return result;
    }
}