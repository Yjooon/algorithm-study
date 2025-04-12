import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[] pick;
    static String[] mineral;

    public int solution(int[] picks, String[] minerals) {       
        pick = picks;
        mineral = minerals;
        arr = new ArrayList<>();
        comb();
        return answer;
    }
    
    static ArrayList<Integer> arr;
    static void comb() {
        if (pickEmpty()) {
            answer = Math.min(answer, calc());
        }
        
        for(int i = 0; i < pick.length; i++) {
            if (pick[i] != 0) {
                arr.add(i);
                pick[i]--;
                comb();
                pick[i]++;
                arr.remove(arr.size() - 1);    
            }
        }
    }
    
    static int calc() {
        int temp = 0;
        for(int i = 0; i < mineral.length; i++) {
            if (i < arr.size() * 5) 
                temp += dig(arr.get(i / 5), mineral[i]);
            else {
                break;
            }
        }
        return temp;
    }
    
    static int dig(int type, String jem) {
        if (type == 0) {
            return 1;
        } else if (type == 1) {
            if (jem.equals("diamond")) {
                return 5;
            } else {
                return 1;
            }
        } else if (type == 2) {
            if (jem.equals("diamond")) {
                return 25;
            } else if (jem.equals("iron")) {
                return 5;
            } else {
                return 1;
            }
        }
        return -1;
    }
    
    static boolean pickEmpty() {
        for(int i = 0; i < pick.length; i++) {
            if (pick[i] != 0)
                return false;
        }
        return true;
    }
}