/**
 * Main_27737
문제
농부 해강이는 
$N \times N$ 칸으로 이루어진 나무판에서 버섯 농사를 짓는다. 
나무판은 버섯이 자랄 수 있는 칸과 없는 칸으로 이루어져 있다.
해강이는 $M$개의 버섯 포자를 가지고 있다. 버섯 포자는 버섯이 자랄 수 있는 칸에만 심을 수 있다.
각 버섯 포자는 포자가 심어진 칸을 포함해 최대 $K$개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯을 자라게 한다. 
이때 연결된 칸은 상하좌우로 적어도 한 변을 공유하는 칸들의 집합이라고 정의한다.

또한 한 칸에 버섯 포자를 여러 개 겹쳐서 심을 수 있으며, 만약 $x$개의 버섯 포자를 겹쳐 
심으면 포자가 심어진 칸을 포함해 최대 $x \times K$개의 연결된 (버섯이 자랄 수 있는) 칸에 버섯이 자란다.

해강이는 버섯 포자를 심을 때 최소 개수로만 심으려고 한다. 해강이가 농사가 가능할지 판단하고,
농사가 가능하다면 남은 버섯 포자의 개수를 출력하시오.
버섯 포자를 하나라도 사용하고 버섯이 자랄 수 있는 모든 칸에 버섯이 전부 자랐을 때 농사가 가능하다고 정의한다.

입력
첫 번째 줄에 
$N$, 
$M$, 
$K$가 공백으로 구분되어 주어진다.

두 번째 줄부터 
$N$개의 줄에 나무판의 각 칸의 상태가 공백으로 구분되어 주어진다.

버섯이 자랄 수 있는 칸은 0, 버섯이 자랄 수 없는 칸은 1로 주어진다.

출력
만약 버섯 농사가 불가능하면 IMPOSSIBLE을 출력한다.
버섯 농사가 가능하다면, POSSIBLE을 출력하고 다음 줄에 남은 버섯 포자의 개수를 출력한다.

제한
$1 \leq N \leq 100$ 
$0 \leq M \leq 1\,000\,000$ 
$1 \leq K \leq 10^8$ 
$N, M, K$는 모두 정수이다.
서브태스크
번호	배점	제한
1	10	
 
$K = 1$ 

2	90	
추가적인 제약 조건이 없다.

 */

 package Algorithm.maktony.solved;

import java.util.*;
import java.io.*;

public class Main_27737 {
    static int N, M, K;
    static int[][] board;
    static boolean[][] visited;
    
    // 방향 벡터 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 나무판의 크기 (NxN)
        M = Integer.parseInt(st.nextToken());  // 버섯 포자의 수
        K = Integer.parseInt(st.nextToken());  // 한 포자가 영향을 미칠 수 있는 최대 칸 수
        
        // 나무판 상태 입력 받기
        board = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS를 이용해 각 연결된 0들의 집합을 탐색하여 필요한 포자 수 계산
        int neededSpores = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    int connectedSize = bfs(i, j);  // 연결된 0들의 집합 크기
                    neededSpores += (connectedSize + K - 1) / K;  // ceil(connectedSize / K)
                }
            }
        }

        // 결과 출력
        if (neededSpores > M) {
            bw.write("IMPOSSIBLE\n");
        } else {
            bw.write("POSSIBLE\n");
            bw.write((M - neededSpores) + "\n");  // 남은 포자의 수
        }

        bw.flush();
        bw.close();
    }

    // BFS 탐색을 이용해 연결된 0들의 집합 크기를 반환
    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        int size = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            size++;  // 연결된 칸의 수 증가

            // 상하좌우로 연결된 칸 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (!visited[nx][ny] && board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return size;  // 연결된 칸의 크기 반환
    }
}