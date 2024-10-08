/**
 * 2024-09-15 일 7번째문제 // RGB 거리 // DP
 문제
 RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는
 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 입력
 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을
 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
 칠하는 비용은 1,000보다 작거나 같은 자연수이다.

 출력
 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
*/
package Algorithm.maktony.solved;

import java.util.StringTokenizer;
import java.io.*;

public class Main_7_1149_20240915 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 집의 개수 입력
        int N = Integer.parseInt(br.readLine());

        // 비용 배열
        int[][] cost = new int[N][3];  // 0: 빨강, 1: 초록, 2: 파랑
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        // DP 배열
        int[][] dp = new int[N][3];

        // 초기값: 첫 번째 집은 각 색깔에 해당하는 비용으로 설정
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        // DP 계산
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];  // 빨강을 선택할 때
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];  // 초록을 선택할 때
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];  // 파랑을 선택할 때
        }

        // 마지막 집에서 최소 비용 구하기
        int result = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));

        // 결과 출력
        System.out.println(result);
    }
}
