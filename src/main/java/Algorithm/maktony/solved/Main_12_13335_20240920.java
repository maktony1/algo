/**
 * 2024-09-20 금 12번째문제 // 트럭 // 구현/시뮬레이션
 문제
 강을 가로지르는 하나의 차선으로 된 다리가 하나 있다. 이 다리를 n 개의 트럭이 건너가려고 한다.
 트럭의 순서는 바꿀 수 없으며, 트럭의 무게는 서로 같지 않을 수 있다. 다리 위에는 단지 w 대의 트럭만 동시에 올라갈 수 있다.
 다리의 길이는 w 단위길이(unit distance)이며, 각 트럭들은 하나의 단위시간(unit time)에 하나의 단위길이만큼만 이동할 수 있다고 가정한다.
 동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대하중인 L보다 작거나 같아야 한다.
 참고로, 다리 위에 완전히 올라가지 못한 트럭의 무게는 다리 위의 트럭들의 무게의 합을 계산할 때 포함하지 않는다고 가정한다.

 예를 들어, 다리의 길이 w는 2, 다리의 최대하중 L은 10, 다리를 건너려는 트럭이 트럭의 무게가 [7, 4, 5, 6]인
 순서대로 다리를 오른쪽에서 왼쪽으로 건넌다고 하자. 이 경우 모든 트럭이 다리를 건너는 최단시간은 아래의 그림에서 보는 것과 같이 8 이다.

 Figure 1. 본문의 예에 대해 트럭들이 다리를 건너는 과정.
 다리의 길이와 다리의 최대하중, 그리고 다리를 건너려는 트럭들의 무게가 순서대로 주어졌을 때, 모든 트럭이 다리를 건너는 최단시간을 구하는 프로그램을 작성하라.

 입력
 입력 데이터는 표준입력을 사용한다. 입력은 두 줄로 이루어진다.입력의 첫 번째 줄에는 세 개의 정수
 n (1 ≤ n ≤ 1,000) , w (1 ≤ w ≤ 100) and L (10 ≤ L ≤ 1,000)이 주어지는데, n은 다리를 건너는 트럭의 수,
 w는 다리의 길이, 그리고 L은 다리의 최대하중을 나타낸다. 입력의 두 번째 줄에는 n개의
 정수 a1, a2, ⋯ , an (1 ≤ ai ≤ 10)가 주어지는데, ai는 i번째 트럭의 무게를 나타낸다.

 출력
 출력은 표준출력을 사용한다. 모든 트럭들이 다리를 건너는 최단시간을 출력하라.
*/
package Algorithm.maktony.solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12_13335_20240920 {
    static final int SIZE = 19;
    static int[][] board = new int[SIZE][SIZE];

    // 방향 정의: 오른쪽, 아래, 오른쪽 아래 대각선, 오른쪽 위 대각선
    static int[] dx = {0, 1, 1, -1};
    static int[] dy = {1, 0, 1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭의 수
        int w = Integer.parseInt(st.nextToken()); // 다리의 길이
        int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중

        int[] trucks = new int[n]; // 트럭의 무게 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        // 다리를 버퍼로 처리 (다리 길이만큼의 공간이 필요함)
        int[] bridge = new int[w];
        int time = 0; // 총 걸린 시간
        int currentWeight = 0; // 현재 다리 위 트럭들의 무게 합
        int index = 0; // 현재 다리를 건너려는 트럭 인덱스

        // 다리 시뮬레이션 시작
        while (index < n || currentWeight > 0) {
            time++; // 시간 경과

            // 1. 다리의 맨 앞칸에서 트럭이 나가므로 무게를 감소시킴
            currentWeight -= bridge[0];

            // 2. 다리의 트럭을 한 칸씩 이동시킴
            for (int i = 1; i < w; i++) {
                bridge[i - 1] = bridge[i];
            }
            bridge[w - 1] = 0; // 마지막 칸은 비워줌

            // 3. 새로운 트럭이 다리로 올라갈 수 있는지 확인
            if (index < n && currentWeight + trucks[index] <= L) {
                bridge[w - 1] = trucks[index]; // 다리의 마지막 칸에 새로운 트럭 올림
                currentWeight += trucks[index]; // 다리 위 무게에 추가
                index++; // 다음 트럭으로 이동
            }
        }

        // 결과 출력
        System.out.println(time);
    }
}