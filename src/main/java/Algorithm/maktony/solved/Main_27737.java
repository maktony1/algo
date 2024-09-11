/**
 * Main_27737
문제
농부 해강이는 
N X N 칸으로 이루어진 나무판에서 버섯 농사를 짓는다. 
나무판은 버섯이 자랄 수 있는 칸과 없는 칸으로 이루어져 있다.
해강이는 M 개의 버섯 포자를 가지고 있다. 버섯 포자는 버섯이 자랄 수 있는 칸에만 심을 수 있다.
각 버섯 포자는 포자가 심어진 칸을 포함해 최대 K 개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯을 자라게 한다. 
이때 연결된 칸은 상하좌우로 적어도 한 변을 공유하는 칸들의 집합이라고 정의한다.

또한 한 칸에 버섯 포자를 여러 개 겹쳐서 심을 수 있으며, 만약 x개의 버섯 포자를 겹쳐 
심으면 포자가 심어진 칸을 포함해 최대 x X k개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯이 자란다.

해강이는 버섯 포자를 심을 때 최소 개수로만 심으려고 한다. 해강이가 농사가 가능할지 판단하고,
농사가 가능하다면 남은 버섯 포자의 개수를 출력하시오.
버섯 포자를 하나라도 사용하고 버섯이 자랄 수 있는 모든 칸에 버섯이 전부 자랐을 때 농사가 가능하다고 정의한다.

입력
첫 번째 줄에 N, M, K가 공백으로 구분되어 주어진다.

두 번째 줄부터 
N개의 줄에 나무판의 각 칸의 상태가 공백으로 구분되어 주어진다.

버섯이 자랄 수 있는 칸은 0, 버섯이 자랄 수 없는 칸은 1로 주어진다.

출력
만약 버섯 농사가 불가능하면 IMPOSSIBLE을 출력한다.
버섯 농사가 가능하다면, POSSIBLE을 출력하고 다음 줄에 남은 버섯 포자의 개수를 출력한다.

제한
1 <= N <= 100$
0 <= M <= 1,000,000 
1 <= K <= 10^8 
N, M, K는 모두 정수이다.

서브태스크
번호	배점	제한
1	    10	   K = 1
2	    90	   추가적인 제약 조건이 없다.

 */

 package Algorithm.maktony.solved;

import java.util.*;
import java.io.*;

public class Main_27737 {
    static long N, M, K;
    static long[] dx = {1, 0, -1, 0};
    static long[] dy = {0, 1, 0, -1};
    static long[][] m;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        M *= K; // 버섯 포자의 개수를 갱신
        m = new long[(int) N][(int) N];
        check = new boolean[(int) N][(int) N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                m[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 모든 칸이 버섯이 자랄 수 없는 칸인지 확인
        boolean allBlocked = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] == 0) {
                    allBlocked = false;
                    break;
                }
            }
        }

        // 불가능한 경우
        if (M == 0 || allBlocked) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        // DFS로 영역 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j] && m[i][j] == 0) {
                    if (M == 0) {
                        System.out.println("IMPOSSIBLE");
                        return;
                    }
                    dfs(i, j);
                    M = (M / K) * K; // 남은 M을 K의 배수로 유지
                }
            }
        }

        // 남은 버섯 포자로 가능한지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (m[i][j] == 0 && !check[i][j]) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
            }
        }

        System.out.println("POSSIBLE");
        System.out.println(M / K); // 남은 버섯 포자의 개수 출력
    }

    // DFS로 영역 탐색
    static void dfs(long y, long x) {
        if (M == 0) return;
        check[(int) y][(int) x] = true;
        M--;

        for (int k = 0; k < 4; k++) {
            long xx = x + dx[k];
            long yy = y + dy[k];

            if (xx < 0 || xx >= N || yy < 0 || yy >= N || check[(int) yy][(int) xx] || m[(int) yy][(int) xx] != 0)
                continue;

            dfs(yy, xx);
        }
    }
}