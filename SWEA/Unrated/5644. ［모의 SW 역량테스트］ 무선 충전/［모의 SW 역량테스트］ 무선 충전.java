import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static int M;
  static int A;
  static int[] userA;
  static int[] userB;
  static List<Beacon> beaconList;

  static int result;

  static class Pair {
    int x;
    int y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Beacon {
    int beaconNumber;
    Pair location;
    int coverage;
    int performance;

    Beacon(int beaconNumber, Pair loc, int c, int p) {
      this.beaconNumber = beaconNumber;
      this.location = loc;
      this.coverage = c;
      this.performance = p;
    }
  }

  static void input() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    M = Integer.parseInt(st.nextToken());
    A = Integer.parseInt(st.nextToken());

    userA = new int[M];
    userB = new int[M];

    result = 0;

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      userA[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      userB[i] = Integer.parseInt(st.nextToken());
    }

    beaconList = new ArrayList<Beacon>();
    for (int i = 0; i < A; i++) {
      st = new StringTokenizer(br.readLine());
      beaconList.add(new Beacon(i,
          new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
          Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
  }

  static void solve() {
    Pair userALoc = new Pair(1, 1);
    Pair userBLoc = new Pair(10, 10);

    for (int i = -1; i < M; i++) {
      if (i != -1) {
        if (userA[i] == 0) {
        } else if (userA[i] == 1) { // UP
          userALoc.y--;
        } else if (userA[i] == 2) { // RIGHT
          userALoc.x++;
        } else if (userA[i] == 3) { // DOWN
          userALoc.y++;
        } else if (userA[i] == 4) { // LEFT
          userALoc.x--;
        }
        if (userB[i] == 0) {
        } else if (userB[i] == 1) { // UP
          userBLoc.y--;
        } else if (userB[i] == 2) { // RIGHT
          userBLoc.x++;
        } else if (userB[i] == 3) { // DOWN
          userBLoc.y++;
        } else if (userB[i] == 4) { // LEFT
          userBLoc.x--;
        }
      }

      ArrayList<Beacon> pListA = new ArrayList<>();
      ArrayList<Beacon> pListB = new ArrayList<>();
      for (int j = 0; j < A; j++) {
        // j번째의 비콘 커버리지 안에 있으면.
        if (Math.abs(userALoc.x - beaconList.get(j).location.x)
            + Math.abs(userALoc.y - beaconList.get(j).location.y) <= beaconList.get(j).coverage) {
          pListA.add(beaconList.get(j));
        }
        if (Math.abs(userBLoc.x - beaconList.get(j).location.x)
            + Math.abs(userBLoc.y - beaconList.get(j).location.y) <= beaconList.get(j).coverage) {
          pListB.add(beaconList.get(j));
        }
      }

      pListA.sort(Comparator.comparing((Beacon b) -> b.performance).reversed());
      pListB.sort(Comparator.comparing((Beacon b) -> b.performance).reversed());

      if (pListA.size() > 0 && pListB.size() > 0) {
        if (pListA.get(0).beaconNumber == pListB.get(0).beaconNumber) {
          result += pListA.get(0).performance;
          if (pListA.size() == 1 && pListB.size() == 1) {
            continue;
          } else if (pListA.size() == 1) {
            result += pListB.get(1).performance;
            continue;
          } else if (pListB.size() == 1) {
            result += pListA.get(1).performance;
            continue;
          } else {
            result +=
                pListA.get(1).performance > pListB.get(1).performance ? pListA.get(1).performance
                    : pListB.get(1).performance;
            continue;
          }
        } else {
          result += pListA.get(0).performance + pListB.get(0).performance;
          continue;
        }
      } else if (pListA.size() > 0) {
        result += pListA.get(0).performance;
        continue;
      } else if (pListB.size() > 0) {
        result += pListB.get(0).performance;
        continue;
      }
    }
  }


  static void output(int test_case) {
    System.out.println("#" + test_case + " " + result);
  }

  public static void main(String args[]) throws IOException {
    int T = Integer.parseInt(br.readLine());

    for (int test_case = 1; test_case <= T; test_case++) {
      input();

      solve();

      output(test_case);
    }
  }
}
