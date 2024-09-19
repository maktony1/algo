/**
 * 2024-09-19 목 11번째문제 // 오목 // 구현/시뮬레이션
 문제
오목은 바둑판에 검은 바둑알과 흰 바둑알을 교대로 놓아서 겨루는 게임이다. 
바둑판에는 19개의 가로줄과 19개의 세로줄이 그려져 있는데 가로줄은 위에서부터 아래로 
1번, 2번, ... ,19번의 번호가 붙고 세로줄은 왼쪽에서부터 오른쪽으로 1번, 2번, ... 19번의 번호가 붙는다.

위의 그림에서와 같이 같은 색의 바둑알이 연속적으로 다섯 알을 놓이면 그 색이 이기게 된다. 
여기서 연속적이란 가로, 세로 또는 대각선 방향 모두를 뜻한다. 즉, 위의 그림은 검은색이 이긴 경우이다. 
하지만 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다.

입력으로 바둑판의 어떤 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지 또는 아직 
승부가 결정되지 않았는지를 판단하는 프로그램을 작성하시오. 단, 검은색과 흰색이 동시에 이기거나 
검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.

입력
19줄에 각 줄마다 19개의 숫자로 표현되는데, 검은 바둑알은 1, 흰 바둑알은 2, 
알이 놓이지 않는 자리는 0으로 표시되며, 숫자는 한 칸씩 띄어서 표시된다.

출력
첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2를, 아직 승부가 결정되지 않았을 경우에는 0을 출력한다. 
검은색 또는 흰색이 이겼을 경우에는 둘째 줄에 연속된 다섯 개의 바둑알 중에서 
가장 왼쪽에 있는 바둑알(연속된 다섯 개의 바둑알이 세로로 놓인 경우, 
그 중 가장 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.
*/
package Algorithm.maktony.solved;

import java.io.*;

public class Main_2615_11_20240919 {
    static final int SIZE = 19;
    static int[][] board = new int[SIZE][SIZE];

    // 방향 정의: 오른쪽, 아래, 오른쪽 아래 대각선, 오른쪽 위 대각선
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, 0, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 바둑판 입력
        for (int i = 0; i < SIZE; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 바둑판 탐색
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] != 0) {
                    int currentStone = board[x][y];

                    // 4가지 방향 탐색
                    for (int i = 0; i < 4; i++) {
                        int count = 1;
                        int nx = x + dx[i];
                        int ny = y + dy[i];

                        // 해당 방향으로 5개 바둑알이 있는지 확인
                        while (0 <= nx && nx < SIZE && 0 <= ny && ny < SIZE && board[nx][ny] == currentStone) {
                            count++;

                            if (count == 5) {
                                // 육목 체크: 앞쪽과 뒤쪽을 확인하여 육목 여부 검사
                                if (0 <= x - dx[i] && x - dx[i] < SIZE && 0 <= y - dy[i] && y - dy[i] < SIZE
                                        && board[x - dx[i]][y - dy[i]] == currentStone) {
                                    break;
                                }
                                if (0 <= nx + dx[i] && nx + dx[i] < SIZE && 0 <= ny + dy[i] && ny + dy[i] < SIZE
                                        && board[nx + dx[i]][ny + dy[i]] == currentStone) {
                                    break;
                                }
                                // 육목이 아니면 승리 조건 충족
                                System.out.println(currentStone);
                                System.out.println((x + 1) + " " + (y + 1));
                                return;
                            }

                            // 다음 좌표로 이동
                            nx += dx[i];
                            ny += dy[i];
                        }
                    }
                }
            }
        }

        // 승부가 나지 않았을 경우
        System.out.println(0);
    }
}