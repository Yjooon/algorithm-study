import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int D;
	static int W;
	static int K;
	static int base[][];
	static int tempBase[][];

	static int drugCount = 0;
	static int result = Integer.MAX_VALUE;

	static void dfs(int index) {
		if (index >= D || drugCount >= result)
			return;

		inputDrug(index, 0);
		test();

		if (drugCount >= result) {
			resetDrug(index);
			return;
		}

		for (int i = index + 1; i < D; i++) {
			dfs(i);
		}
		resetDrug(index);

		inputDrug(index, 1);
		test();

		if (drugCount >= result) {
			resetDrug(index);
			return;
		}

		for (int i = index + 1; i < D; i++) {
			dfs(i);
		}
		resetDrug(index);
	}

	static void inputDrug(int index, int type) {
		for (int i = 0; i < W; i++) {
			tempBase[index][i] = base[index][i];
		}
		for (int i = 0; i < W; i++) {
			base[index][i] = type;
		}
		drugCount++;
	}

	static void resetDrug(int index) {
		for (int i = 0; i < W; i++) {
			base[index][i] = tempBase[index][i];
		}
		drugCount--;
	}

	// 검사기준 k에 대해 테스트를 통과하는지만 체크
	static boolean test() {
		int lenA = 0;
		int lenB = 0;

		boolean pass = false;
		for (int i = 0; i < W; i++) {
			lenA = 0;
			lenB = 0;
			pass = false;
			for (int j = 0; j < D; j++) {
				if (base[j][i] == 0) {
					lenA++;
					lenB = 0;
				} else {
					lenB++;
					lenA = 0;
				}
				if (lenA == K || lenB == K) {
					pass = true;
					break;
				}
			}
			if (!pass) {
//				System.out.println("실패 ㅠ!!! drugCount: " + drugCount + " lenA: " + lenA + " lenB: " + lenB);
//
//				for (int a = 0; a < D; a++) {
//					for (int b = 0; b < W; b++) {
//						System.out.print(base[a][b] + " ");
//					}
//					System.out.println();
//				}
				return false;
			}
		}
//		System.out.println("통과!!! drugCount: " + drugCount);

//		for (int i = 0; i < D; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(base[i][j] + " ");
//			}
//			System.out.println();
//		}
		result = Math.min(drugCount, result);
		return true;
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			result = Integer.MAX_VALUE;
			drugCount = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			base = new int[D][W];
			tempBase = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					base[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			if (test()) {
//				System.out.println("처음에 통과");
				System.out.println("#" + test_case + " " + 0);
				continue;
			}

			for (int i = 0; i < D; i++) {
				dfs(i);
			}

			System.out.println("#" + test_case + " " + result);
		}
	}
}