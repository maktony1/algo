/**
 * 2024-09-16 월 8번째문제 // 내려가기 // DP
 문제
 N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다. 내려가기 게임을 하고 있는데,
 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.

 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다.
 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다.
 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.
 이 제약 조건을 그림으로 나타내어 보면 다음과 같다.

 별표는 현재 위치이고, 그 아랫 줄의 파란 동그라미는 원룡이가 다음 줄로 내려갈 수 있는 위치이며,
 빨간 가위표는 원룡이가 내려갈 수 없는 위치가 된다. 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수,
 최소 점수를 구하는 프로그램을 작성하시오. 점수는 원룡이가 위치한 곳의 수의 합이다.

 입력
 첫째 줄에 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에는 숫자가 세 개씩 주어진다.
 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.

 출력
 첫째 줄에 얻을 수 있는 최대 점수와 최소 점수를 띄어서 출력한다.
*/
package Algorithm.maktony.solved;

import java.io.*;
import java.util.Arrays;

public class Main_2096_8_20240916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] scores = new int[N][3];
        for (int i = 0; i < N; i++) {
            scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dpMax = new int[N][3];
        int[][] dpMin = new int[N][3];
        System.arraycopy(scores[0], 0, dpMax[0], 0, 3);
        System.arraycopy(scores[0], 0, dpMin[0], 0, 3);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dpMax[i][j] = scores[i][j] + Math.max(dpMax[i-1][j], Math.max(j > 0 ? dpMax[i-1][j-1] : 0, j < 2 ? dpMax[i-1][j+1] : 0));
                dpMin[i][j] = scores[i][j] + Math.min(dpMin[i-1][j], Math.min(j > 0 ? dpMin[i-1][j-1] : Integer.MAX_VALUE, j < 2 ? dpMin[i-1][j+1] : Integer.MAX_VALUE));
            }
        }

        int maxScore = Math.max(dpMax[N-1][0], Math.max(dpMax[N-1][1], dpMax[N-1][2]));
        int minScore = Math.min(dpMin[N-1][0], Math.min(dpMin[N-1][1], dpMin[N-1][2]));

        System.out.println(maxScore + " " + minScore);
    }
}