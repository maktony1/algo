/**
 * 2024-09-25 화 17번째문제 // 정수 a를 k로 만들기 // 미공개
 문제
입력으로 양의 정수 A와 K가 주어지면, 아래 연산을 이용하여 A를 K로 변경하려고 한다. 
정수 A를 변경할 때 사용할 수 있는 연산 종류는 다음과 같다.

연산 1: 정수 A에 1을 더한다.
연산 2: 정수 A에 2를 곱한다.
정수 A를 정수 K로 만들기 위해 필요한 최소 연산 횟수를 출력하자.

입력
첫 번째 줄에 양의 정수 A와 K가 빈칸을 사이에 두고 순서대로 주어진다.

출력
첫 번째 줄에 양의 정수 A를 양의 정수 K로 만들기 위해 필요한 최소 연산 횟수를 출력한다.

제한
1 ≤ A < K ≤ 1,000,000
*/
package Algorithm.maktony.solved;

import java.io.*;
import java.util.*;

public class Main_17_25418_20240925 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visited = new int[K + 1]; // 방문 체크 배열
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {A, 0}); // A에서 시작, 연산 횟수 0
        visited[A] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int value = current[0];
            int steps = current[1];

            if (value == K) {
                System.out.println(steps);
                return;
            }

            // 연산 1: 1을 더한다.
            if (value + 1 <= K && visited[value + 1] == 0) {
                visited[value + 1] = 1;
                queue.add(new int[] {value + 1, steps + 1});
            }

            // 연산 2: 2를 곱한다.
            if (value * 2 <= K && visited[value * 2] == 0) {
                visited[value * 2] = 1;
                queue.add(new int[] {value * 2, steps + 1});
            }
        }
    }
}