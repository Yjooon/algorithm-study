import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int M;
	static int K;

	static int currMaxX, currMaxY;
	static int result;
	static Pair[][] arr;

	static class Pair {
		int life;
		int time;

		Pair(int life, int time) {
			this.life = life;
			this.time = time;
		}
	}

	static void bfs() {
		for (int i = 0; i < currMaxX; i++) {
			for (int j = 0; j < currMaxY; j++) {
				if (arr[i][j].life <= 0)
					continue;

				if (arr[i][j].life == 100 && arr[i][j].time == 1) { // 활성상태인 식물의 time값이 1이 되면 죽이기
					arr[i][j].life = -1;
					continue;
				}

				if (arr[i][j].time > 0) {
					arr[i][j].time--;
					continue;
				}

				if (arr[i][j].life > 0 && arr[i][j].time == 0) { // time 값이 0이면 활성화 된 것
//					System.out.println("i: " + i + " j: " + j);
					breeding(i, j);
					arr[i][j].time = arr[i][j].life - 1; // 활성 상태가 되면 life 시간동안 살아있다가 죽음.
					arr[i][j].life = 100;

					if (arr[i][j].life == 100 && arr[i][j].time == 0) { // 활성상태인 식물의 time값이 0이 되면 죽이기
						arr[i][j].life = -1;
					}
//					System.out.println(arr[i][j].life);
				}

			}
		}
		for (int i = 0; i < currMaxX; i++) {
			for (int j = 0; j < currMaxY; j++) {
				if (arr[i][j].time < 0) {
					arr[i][j].time *= -1; // 이번에 추가된 것 time값 양수로 바꾸기
				}
			}
		}
	}

	static void breeding(int x, int y) {
		int dx[] = { 1, 0, -1, 0 };
		int dy[] = { 0, -1, 0, 1 };

		for (int k = 0; k < 4; k++) {
			int nextX = x + dx[k];
			int nextY = y + dy[k];
			if (arr[nextX][nextY].life < 0)
				continue;
			if ((arr[nextX][nextY].life == 0 && arr[nextX][nextY].time == 0)
					|| ((arr[nextX][nextY].life == arr[nextX][nextY].time * -1)
							&& (arr[nextX][nextY].life < arr[x][y].life))) {
				// 새로운 공간 이거나,
				// 이미 채워져 있는 공간일 경우 이번 time과 life가 동일한, 즉 이번에 새로 추가된 경우에, 이번에 덮으려는 값이 더 큰
				// 경우만 진행함.
				arr[nextX][nextY].life = arr[x][y].life;
				arr[nextX][nextY].time = arr[x][y].life * -1;
			}
		}

	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			currMaxX = N + K;
			currMaxY = M + K;

			arr = new Pair[currMaxX][currMaxY];
			for (int i = 0; i < currMaxX; i++) {
				for (int j = 0; j < currMaxY; j++) {
					arr[i][j] = new Pair(0, 0);
				}
			}

			for (int i = K / 2; i < K / 2 + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = K / 2; j < K / 2 + M; j++) {
					int life = Integer.parseInt(st.nextToken());
					arr[i][j].life = life;
					arr[i][j].time = life;
				}
			}

			int day = 0;
			while (day < K) {
				day++;
				bfs();

//				System.out.println("day: " + day);
//
//				for (int i = 0; i < currMaxX; i++) {
//					for (int j = 0; j < currMaxY; j++) {
//						System.out.print(arr[i][j].life + " ");
//					}
//					System.out.println();
//				}
			}

			result = 0;
			for (int i = 0; i < currMaxX; i++) {
				for (int j = 0; j < currMaxY; j++) {
					if (arr[i][j].life > 0) {
						result++;
					}
				}
			}

//			for (int i = 0; i < currMaxX; i++) {
//				for (int j = 0; j < currMaxY; j++) {
//					System.out.print(arr[i][j].life + " ");
//				}
//				System.out.println();
//			}
			System.out.println("#" + test_case + " " + result);
		}
	}
}
