import java.util.*;
import java.io.*;

class Solution {
    static HashMap<String, HashMap<String, Integer>> map;
    static HashMap<String, Integer> giftPoint;
    static HashMap<String, Integer> resultMap;
    
    public int solution(String[] friends, String[] gifts) {
        map = new HashMap<>();
        giftPoint = new HashMap<>();
        resultMap = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], new HashMap<>());
            giftPoint.put(friends[i], 0);
            resultMap.put(friends[i], 0);
        }
        
        StringTokenizer st;
        for (String s : gifts) {
            st = new StringTokenizer(s);
            String key = st.nextToken();
            String value = st.nextToken();
            map.get(key).computeIfAbsent(value, k -> 0);
            map.get(key).computeIfPresent(value, (k, v) -> v + 1);  
            giftPoint.put(key, giftPoint.get(key) + 1);
            giftPoint.put(value, giftPoint.get(value) - 1);
        }
        HashSet<String> isVisited = new HashSet<>();
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                String a = friends[i];
                String b = friends[j];
                int result = giveGift(a, b);
                if (result == 1) {
                    resultMap.put(a, resultMap.get(a) + 1);
                } else if (result == 2) {
                    resultMap.put(b, resultMap.get(b) + 1);
                }
            }
        }

        
        return Collections.max(resultMap.values());
    }
    
    
    // 1: a가 선물 받기 / 2: b가 선물 받기 / 0: 안주기
    public static int giveGift(String s1, String s2) {
        int a = map.get(s1).getOrDefault(s2, 0);
        int b = map.get(s2).getOrDefault(s1, 0);
        System.out.println("s1: " + s1 + " s2: " + s2);
        System.out.println("a: " + a + " b: " + b);
        if (a == b) {
            int gp1 = giftPoint.get(s1);
            int gp2 = giftPoint.get(s2);
            if (gp1 == gp2) return 0;
            return gp1 > gp2 ? 1 : 2; // 선물 지수 높은 사람이 선물 받음
        }
        return a > b ? 1 : 2; // 더 많이 준 사람이 선물 받음
    }

}

