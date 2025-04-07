import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[][] user;
    static int[] emoticon;
    static ArrayList<Integer> saleList;
    
    static int[] sales = {10, 20, 30, 40};
    
    static int[] answer = {0, 0};
    
    static void comb() {
        if (saleList.size() == m) {
            calc();
            return;
        }
        for(int i = 0; i < 4; i++) {
            saleList.add(sales[i]);
            comb();
            saleList.remove(saleList.size() - 1);
        }
    }
    
    static void calc() {
        int totalCount = 0;
        int totalCost = 0;
        int cost = 0;
        for(int i = 0; i < n; i++) {
            cost = 0;
            for(int j = 0; j < m; j++) {
                if (user[i][0] <= saleList.get(j))
                    cost += emoticon[j] * (100 - saleList.get(j)) * 0.01;
            }
            if (cost < user[i][1]) // 기준액에 못미치면 그냥 구매
                totalCost += cost;
            else { // 기준액에 미치면 구매 안하고 요금제 가입
                totalCount++;
            }
        }
        if (answer[0] < totalCount) {
            answer[0] = totalCount;
            answer[1] = totalCost;
        } else if (answer[0] == totalCount) {
            answer[1] = Math.max(answer[1], totalCost);
        }
        
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        saleList = new ArrayList<Integer>();
        user = users;
        emoticon = emoticons;
        
        comb();
        
        return answer;
    }
}