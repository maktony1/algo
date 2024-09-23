/**
 * 2024-09-22 일 14번째문제 // 선분 위의 점 // 이분탐색
 문제
일차원 좌표상의 점 N개와 선분 M개가 주어진다. 이때, 각각의 선분 위에 입력으로 주어진 점이 몇 개 있는지 구하는 프로그램을 작성하시오.

입력
첫째 줄에 점의 개수 N과 선분의 개수 M이 주어진다. (1 ≤ N, M ≤ 100,000) 둘째 줄에는 점의 좌표가 주어진다. 
두 점이 같은 좌표를 가지는 경우는 없다. 셋째 줄부터 M개의 줄에는 선분의 시작점과 끝점이 주어진다. 
입력으로 주어지는 모든 좌표는 1,000,000,000보다 작거나 같은 자연수이다.

출력
입력으로 주어진 각각의 선분 마다, 선분 위에 입력으로 주어진 점이 몇 개 있는지 출력한다.
*/
package Algorithm.maktony.solved;

import java.io.*;
import java.util.*;

public class Main_11663_14_20240922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 점의 개수 N과 선분의 개수 M 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 점의 좌표 입력
        int[] points = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        // 점을 오름차순으로 정렬
        Arrays.sort(points);

        // 각 선분에 대해 점이 몇 개 있는지 계산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 이분 탐색을 통해 해당 구간에 있는 점들의 개수를 찾는다.
            int count = countPointsInRange(points, start, end);

            // 결과 출력
            bw.write(count + "\n");
        }

        // 출력 버퍼 비우기
        bw.flush();
        bw.close();
        br.close();
    }

    // 주어진 구간 [start, end] 내의 점 개수를 구하는 함수
    public static int countPointsInRange(int[] points, int start, int end) {
        // start 이상의 첫 번째 점의 위치를 찾음 (lower bound)
        int left = lowerBound(points, start);
        // end보다 큰 첫 번째 점의 위치를 찾음 (upper bound)
        int right = upperBound(points, end);

        // right - left가 해당 구간에 포함된 점의 개수
        return right - left;
    }

    // lower bound: target 이상이 처음 나타나는 위치 찾기
    public static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // upper bound: target보다 큰 값이 처음 나타나는 위치 찾기
    public static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}