import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        char[] charArr = skill.toCharArray();
        
        for(String s : skill_trees) {
            Deque<Character> q = new ArrayDeque<>();
            for(char c : charArr) {
                q.add(c);
            }
            boolean flag = false;
            for(int i = 0; i < s.length(); i++) {
                if (q.isEmpty())
                    break;
                if (q.getFirst() == s.charAt(i)) {
                    q.poll();
                    continue;
                }
                if (q.contains(s.charAt(i))) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                answer++;
        }
        
        return answer;
    }
}